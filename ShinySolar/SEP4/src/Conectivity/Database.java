package Conectivity;

import Domain.*;
import Domain.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
public class Database
{
	String url = "jdbc:postgresql://localhost:5432/postgres";
	String user = "postgres";
	String password = "gbevia";
	Connection con = null;
	Statement stmt= null;
	ResultSet rs = null;
	private final Questions popup = new Questions();
	public Database() {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean authenticateUser(String username, String password)
	{
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT username FROM solar_softies.Register "
					+ " where username ='"+ username +
					"' AND password = '" + password + "' ;");
			if (rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} 		return false;
	}
	public void measurement() {
		String SQL = "SELECT timestamp FROM solar_softies.PVMeasurement ORDER BY timestamp DESC LIMIT 1;;";
		Timestamp lastTime = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
			rs.next();
			lastTime = rs.getTimestamp(1);
		}catch (SQLException e) {
			restart();
			e.printStackTrace();
		}
		assert lastTime != null;
		if(lastTime.getHours()>17) {
			lastTime.setTime(lastTime.getTime()+(11*3600*1000));
		}
		newMeasurement(lastTime);
	}
	public void newMeasurement(Timestamp startingTime) {
		Domain.Date date =  new Date(startingTime);
		SolarGain generator = new SolarGain();
		String SQL = "SELECT id, type FROM solar_softies.Panel WHERE row >=1;";
		ArrayList<String> panelTypes = new ArrayList<>();
		ArrayList<Integer> panelIDs = new ArrayList<>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
			while(rs.next()) {
				panelIDs.add(rs.getInt(1));
				panelTypes.add(rs.getString(2));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		for(int k = 0; k< 1; k++) {
			int solarGain = generator.solarGain();
			Timestamp time = date.date(3600);
			for (int i = 0; i < panelTypes.size(); i++) {
				if(panelTypes.get(i).equals("PV")){
					storeMeasurement(new Measurement(panelIDs.get(i), solarGain, time));
				}else {
					storeMeasurement(new Measurement(panelIDs.get(i), solarGain, time,true));
				}
			}
		}
	}
	public void storeMeasurement(Measurement measurement) {
		String SQL;
		if(measurement.getTempIn() > 0) {
			SQL = "INSERT INTO solar_softies.THMeasurement (panel_id, timestamp, solar_gain, temp_in, temp_out, "
					+ "time_in_sec, water_flow, output_power) "
					+ "VALUES ('" + measurement.getId() + "','" + measurement.getDate() + "','" + measurement.getSolarGain() + "','" + measurement.getTempIn()
					+"','" + measurement.getTempOut() + "','"
					+ measurement.getTimeInSec() + "','" + measurement.getWaterFlow() +"','" +measurement.getOutputPower()+ "')";
		}else {
			SQL = "INSERT INTO solar_softies.PVMeasurement (panel_id, timestamp, solar_gain, voltage, current, output_power) "
					+ "VALUES ('" + measurement.getId()  + "','" + measurement.getDate() + "','" + measurement.getSolarGain() + "','"
					+ measurement.getVoltage() + "','" + measurement.getCurrent() + "','" +measurement.getOutputPower()+ "')";
		}
		try {
			stmt = con.createStatement();
			stmt.execute(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int powerOutput(String panelType) {
		String SQL = "SELECT date_trunc('minute', timestamp), SUM(output_power) from solar_softies."+panelType+"Measurement group BY date_trunc('minute', timestamp) ORDER BY date_trunc DESC LIMIT 1;";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
			if(rs.next()){
				return (int)rs.getDouble(2);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}
	public String medianDayPowerOutput(String panelType) {
		final DecimalFormat df = new DecimalFormat("0");
		String SQL = "SELECT date_trunc('day', timestamp), AVG(output_power) from solar_softies."+panelType+"Measurement group BY date_trunc('day', timestamp) ORDER BY date_trunc DESC LIMIT 1;";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
			if(rs.next()){
				return df.format(rs.getDouble(2));
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
		return "";
	}
	public int medianPowerOutput() {
		int output = 0;
		try {
			output = (int)(Double.parseDouble(medianDayPowerOutput("PV")) + Double.parseDouble(medianDayPowerOutput("TH")));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return output;
	}
	public String medianHourPowerOutput() {
		String SQL = "SELECT date_trunc('minute', timestamp) from solar_softies.PVMeasurement group BY date_trunc('minute', timestamp) ORDER BY date_trunc DESC LIMIT 1;";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
			rs.next();
			return String.valueOf(rs.getTimestamp(1).getHours());
		}catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	}
	public String medianPowerOutputDate() {
		String SQL = "SELECT date_trunc('minute', timestamp) from solar_softies.PVMeasurement group BY date_trunc('minute', timestamp) ORDER BY date_trunc DESC LIMIT 1;";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
			rs.next();
			return String.valueOf(rs.getTimestamp(1));
		}catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	}
	public void addManufacturer(Manufacturer manufacturer) {
		if (manufacturer(manufacturer.getName(), "Manufacturer"))
		{
			if (manufacturer(manufacturer.getName(), "History"))
			{
				storeManufacturer (manufacturer);
			}
		}
	}
	public void storeManufacturer (Manufacturer manufacturer) {
		String SQL = "INSERT INTO solar_softies.Manufacturer VALUES "
					+ "(DEFAULT, '" + manufacturer.getName() + "', '" + manufacturer.getAddress() + "', '" + manufacturer.getPhone() +"', '"
					+ manufacturer.getEmail()+"', '"+manufacturer.getWebsite()+"');";
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateManufacturer(String name, String attribute, String newValue) {
		String SQL = "UPDATE solar_softies.Manufacturer SET " + attribute + " = '" + newValue + "' WHERE name = '" + name + "';";
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean manufacturer(String newName, String tableName){
		ArrayList<String> names = new ArrayList<>();
		String SQLNameAttribute = "name";
		if(tableName.equals("History")) {
			SQLNameAttribute = "old_name";
		}
		String SQL = "SELECT "+ SQLNameAttribute +" FROM solar_softies." + tableName
					+ " WHERE " + SQLNameAttribute + " = '" + newName + "';";
		try {
			stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while(rs.next()) {
            	names.add(rs.getString(SQLNameAttribute));
            }
        } catch (SQLException e) {
			e.printStackTrace();
        }
		return names.size() == 0;
	}
	public int manufacturerId(String name) {
		String SQL = "SELECT id FROM solar_softies.Manufacturer WHERE name = '"+name+"';";
		int result = 1;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
			rs.next();
			result = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return result;	
	}
	public void storePanel(Panel panel) {
		String sql = "INSERT INTO solar_softies.Panel (type, colum, row, manufacturer_id, surface_area, installation_date) "
				+ "VALUES ('" + panel.getType() + "','"+ panel.getCol()+ "','"+ panel.getRow()+ "','" + panel.getManufacturerId()  + "','" + panel.getSurfaceArea()
				+ "','" + panel.getInstallationTimestamp() + "')";
		try {
			stmt = con.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ObservableList<Manufacturer> manufacturerList(){
		ObservableList<Manufacturer> manufacturerList = FXCollections.observableArrayList();
		String SQL = "SELECT * FROM solar_softies.Manufacturer ORDER BY id ASC;";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
        	while (rs.next()) {
        		manufacturerList.add(new Manufacturer(rs.getString(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6)));
        	}
        } catch (SQLException e) {
			e.printStackTrace();
        }
		return manufacturerList;
	}
	public ObservableList<PanelInfo> pvPanelInfo(LocalDate from, LocalDate to, String manufacturer){
		ObservableList<PanelInfo> panel = FXCollections.observableArrayList();
		final DecimalFormat df = new DecimalFormat("0");
		String SQL = "SELECT * FROM solar_softies.PV WHERE time >= '"+from.toString()+"' AND time <= '"+to.toString()+"' ";
		if(manufacturer != null) {
			SQL += " AND name = '"+manufacturer+"';";
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				String installationDate = rs.getDate(1).toString();
				String type = rs.getString(2);
				String date = rs.getString(3);
				String outputPower = df.format(rs.getDouble(4));
				String by = rs.getString(5);
				String solarGain = df.format(rs.getDouble(6));
				String voltage = df.format(rs.getDouble(7));
				String current = df.format(rs.getDouble(8));
				String surfaceArea = df.format(rs.getDouble(9));
				String row = rs.getString(10);
				String column = rs.getString(11);
				panel.add(new PanelInfo(installationDate, type, date, outputPower, by, solarGain, voltage, current, surfaceArea, row, column));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return panel;
	}
	public ObservableList<PanelInfo> thPanelInfo(LocalDate from, LocalDate to, String manufacturer){
		ObservableList<PanelInfo> panel = FXCollections.observableArrayList();
		final DecimalFormat df = new DecimalFormat("0");
		String SQL = "SELECT * FROM solar_softies.TH WHERE time >= '" +from+"' AND time <= '"+to+"' ";
		if(manufacturer != null) {
			SQL += " AND name = '"+manufacturer+"';";
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				String installationDate = rs.getDate(1).toString();
				String type = rs.getString(2);
				String date = rs.getString(3);
				String outputPower = df.format(rs.getDouble(4));
				String by = rs.getString(5);
				String solarGain = df.format(rs.getDouble(6));
				String tempIn= df.format(rs.getDouble(7));
				String tempOut = df.format(rs.getDouble(8));
				String timeInSec= df.format(rs.getInt(9));
				String waterFlow = df.format(rs.getInt(10));
				String surfaceArea = df.format(rs.getDouble(11));
				String row = rs.getString(12);
				String column = rs.getString(13);
				panel.add(new PanelInfo(installationDate, type, date, outputPower, by, solarGain, tempIn, tempOut, timeInSec, waterFlow, surfaceArea, row, column));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return panel;
	}
	public ObservableList<PanelInfo> pvPanelInfo(){
		ObservableList<PanelInfo> panel = FXCollections.observableArrayList();
		final DecimalFormat df = new DecimalFormat("0");
		String SQL = "SELECT * FROM solar_softies.PV;";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				String installationDate = rs.getDate(1).toString();
				String type = rs.getString(2);
				String date = rs.getString(3);
				String outputPower = df.format(rs.getDouble(4));
				String by = rs.getString(5);
				String solarGain = df.format(rs.getDouble(6));
				String voltage = df.format(rs.getDouble(7));
				String current = df.format(rs.getDouble(8));
				String surfaceArea = df.format(rs.getDouble(9));
				String row = rs.getString(10);
				String column = rs.getString(11);
				panel.add(new PanelInfo(installationDate, type, date, outputPower, by, solarGain, voltage, current, surfaceArea, row, column));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return panel;
	}
	public ObservableList<PanelInfo> thPanelInfo(){
		ObservableList<PanelInfo> panel = FXCollections.observableArrayList();
		final DecimalFormat df = new DecimalFormat("0");
		String SQL = "SELECT * FROM solar_softies.TH;";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				String installationDate = rs.getDate(1).toString();
				String type = rs.getString(2);
				String date = rs.getString(3);
				String outputPower = df.format(rs.getDouble(4));
				String by = rs.getString(5);
				String solarGain = df.format(rs.getDouble(6));
				String tempIn= df.format(rs.getDouble(7));
				String tempOut = df.format(rs.getDouble(8));
				String timeInSec= df.format(rs.getInt(9));
				String waterFlow = df.format(rs.getInt(10));
				String surfaceArea = df.format(rs.getDouble(11));
				String row = rs.getString(12);
				String column = rs.getString(13);
				panel.add(new PanelInfo(installationDate, type, date, outputPower, by, solarGain, tempIn, tempOut, timeInSec, waterFlow, surfaceArea, row, column));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return panel;
	}
	public ArrayList<String> manufacturersNames(){
		String SQL = "SELECT name FROM solar_softies.Manufacturer;";
		ArrayList<String> names = new ArrayList<>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
            
        	while (rs.next()) {
        		names.add(rs.getString(1));
        	}
        } catch (SQLException e) {
			e.printStackTrace();
        }
		return names;
	}
	public void delete() {
		String SQL = "TRUNCATE solar_softies.THMeasurement RESTART IDENTITY CASCADE;"
		+ " TRUNCATE solar_softies.PVMeasurement RESTART IDENTITY CASCADE;"
		+ " TRUNCATE solar_softies.Panel RESTART IDENTITY CASCADE;"
		+ " TRUNCATE solar_softies.Manufacturer RESTART IDENTITY CASCADE;"
		+ " TRUNCATE solar_softies.History RESTART IDENTITY CASCADE;";
		try {
			stmt = con.createStatement();
			stmt.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean confirmAlert(String title, String msg) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION,msg, ButtonType.OK, ButtonType.CANCEL);
		alert.setTitle(title);
		alert.showAndWait();
		if(alert.getResult() == ButtonType.OK){
			return true;
		}
		alert.getResult();
		return false;
	}
	public void restart() {
		do {
			addManufacturer(popup.addManufacturer());
		}while(confirmAlert("Add manufacturer", "Do you want to add another manufacturer?"));
		do {
			storePanel(popup.addPanel(manufacturersNames()));
		}while(confirmAlert("Add panel", "Do you want to add another panel?"));
		newMeasurement(new Timestamp(122,5,15,5,0,0,0));
	}
}