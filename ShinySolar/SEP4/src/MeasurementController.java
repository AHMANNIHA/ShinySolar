import java.net.URL;
import java.util.ResourceBundle;

import Conectivity.Database;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
public class MeasurementController  extends UserController implements Initializable {
	public Button helpMeasurementBtn;
	public CategoryAxis x;
	public NumberAxis y;
	public Button resetBtn;
	public TextField totalTextField;
	public TextField medianHourTextField;
	public TextField measurementDate;
	public TextField medianTextField;
	public TextField pvMedianTextField;
	public TextField pvTextField;
	public TextField thMedianTextField;
	public TextField thTextField;
	public AreaChart<String,Integer> AreaChart;
	private XYChart.Series s;
	private final String[] hour = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"};
	private final int[] output = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	public boolean deleteAlert(){
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete everything?", ButtonType.OK, ButtonType.CLOSE);
		alert.setTitle("Delete data");
		alert.showAndWait();
		if(alert.getResult() == ButtonType.OK){
			return true;
		}
		alert.getResult();
		return false;
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
	public void reset() {
		if(deleteAlert()) {
			new Database();
			database.delete();
			confirmAlert("Confirmation", "DELETE SUCCESS");
			database.restart();
		}
	}
	public void informAlert(String title, String msg) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION,msg, ButtonType.CLOSE);
		alert.setTitle(title);
		alert.showAndWait();
		alert.getResult();
	}
	public void helpMeasurement() {
		informAlert("Help", "Deletes all data and resets the database in case the data is wrong or outdated. \nIMPORTANT: Make sure the database is backed or you don't need it anymore. ");
	}
	public void initialize(URL arg0, ResourceBundle arg1) {
		s = new XYChart.Series<>();
		refresh();
	}
	private void refresh() {
		new Database();
		database.measurement();
		measurementDate.setText(database.medianPowerOutputDate());
		switch (database.medianHourPowerOutput())
		{
			case "1" ->
					output[0] = database.powerOutput("PV") + database.powerOutput("TH");
			case "2" ->
					output[1] = database.powerOutput("PV") + database.powerOutput("TH");
			case "3" ->
					output[2] = database.powerOutput("PV") + database.powerOutput("TH");
			case "4" ->
					output[3] = database.powerOutput("PV") + database.powerOutput("TH");
			case "5" ->
					output[4] = database.powerOutput("PV") + database.powerOutput("TH");
			case "6" ->
					output[5] = database.powerOutput("PV") + database.powerOutput("TH");
			case "7" ->
					output[6] = database.powerOutput("PV") + database.powerOutput("TH");
			case "8" ->
					output[7] = database.powerOutput("PV") + database.powerOutput("TH");
			case "9" ->
					output[8] = database.powerOutput("PV") + database.powerOutput("TH");
			case "10" ->
					output[9] = database.powerOutput("PV") + database.powerOutput("TH");
			case "11" ->
					output[10] = database.powerOutput("PV") + database.powerOutput("TH");
			case "12" ->
					output[11] = database.powerOutput("PV") + database.powerOutput("TH");
			case "13" ->
					output[12] = database.powerOutput("PV") + database.powerOutput("TH");
			case "14" ->
					output[13] = database.powerOutput("PV") + database.powerOutput("TH");
			case "15" ->
					output[14] = database.powerOutput("PV") + database.powerOutput("TH");
			case "16" ->
					output[15] = database.powerOutput("PV") + database.powerOutput("TH");
			case "17" ->
					output[16] = database.powerOutput("PV") + database.powerOutput("TH");
			case "18" ->
					output[17] = database.powerOutput("PV") + database.powerOutput("TH");
			case "19" ->
					output[18] = database.powerOutput("PV") + database.powerOutput("TH");
			case "20" ->
					output[19] = database.powerOutput("PV") + database.powerOutput("TH");
			case "21" ->
					output[20] = database.powerOutput("PV") + database.powerOutput("TH");
			case "22" ->
					output[21] = database.powerOutput("PV") + database.powerOutput("TH");
			case "23" ->
				  output[22] = database.powerOutput("PV") + database.powerOutput("TH");
			case "24" ->
					output[23] = database.powerOutput("PV") + database.powerOutput("TH");
		}
		medianTextField.setText(String.valueOf(database.medianPowerOutput()));
		pvMedianTextField.setText(database.medianDayPowerOutput("PV"));
		pvTextField.setText(String.valueOf(database.powerOutput("PV")));
		thMedianTextField.setText(database.medianDayPowerOutput("TH"));
		thTextField.setText(String.valueOf(database.powerOutput("TH")));
		totalTextField.setText(String.valueOf((database.powerOutput("TH"))+database.powerOutput("PV")));
		medianHourTextField.setText(database.medianHourPowerOutput());
		if(!AreaChart.getData().isEmpty()) {
			AreaChart.getData().remove(s);
			s = new XYChart.Series<String,Integer>();
		}
		s.getData().add(new XYChart.Data<>(hour[0], output[0]));
		s.getData().add(new XYChart.Data<>(hour[1], output[1]));
		s.getData().add(new XYChart.Data<>(hour[2], output[2]));
		s.getData().add(new XYChart.Data<>(hour[3], output[3]));
		s.getData().add(new XYChart.Data<>(hour[4], output[4]));
		s.getData().add(new XYChart.Data<>(hour[5], output[5]));
		s.getData().add(new XYChart.Data<>(hour[6], output[6]));
		s.getData().add(new XYChart.Data<>(hour[7], output[7]));
		s.getData().add(new XYChart.Data<>(hour[8], output[8]));
		s.getData().add(new XYChart.Data<>(hour[9], output[9]));
		s.getData().add(new XYChart.Data<>(hour[10], output[10]));
		s.getData().add(new XYChart.Data<>(hour[11], output[11]));
		s.getData().add(new XYChart.Data<>(hour[12], output[12]));
		s.getData().add(new XYChart.Data<>(hour[13], output[13]));
		s.getData().add(new XYChart.Data<>(hour[14], output[14]));
		s.getData().add(new XYChart.Data<>(hour[15], output[15]));
		s.getData().add(new XYChart.Data<>(hour[16], output[16]));
		s.getData().add(new XYChart.Data<>(hour[17], output[17]));
		s.getData().add(new XYChart.Data<>(hour[18], output[18]));
		s.getData().add(new XYChart.Data<>(hour[19], output[19]));
		s.getData().add(new XYChart.Data<>(hour[20], output[20]));
		s.getData().add(new XYChart.Data<>(hour[21], output[21]));
		s.getData().add(new XYChart.Data<>(hour[22], output[22]));
		s.getData().add(new XYChart.Data<>(hour[23], output[23]));
		AreaChart.getData().addAll(s);
	}
}