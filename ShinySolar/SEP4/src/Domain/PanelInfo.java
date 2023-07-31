package Domain;

import javafx.beans.property.SimpleStringProperty;
public class PanelInfo
{
	private SimpleStringProperty installationDate;
	private SimpleStringProperty type;
	private SimpleStringProperty date;
	private SimpleStringProperty outputPower;
	private SimpleStringProperty manufacturer;
	private SimpleStringProperty solarGain;
	private SimpleStringProperty voltage;
	private SimpleStringProperty current;
	private SimpleStringProperty tempIn;
	private SimpleStringProperty tempOut;
	private SimpleStringProperty timeInSec;
	private SimpleStringProperty surfaceArea;
	private SimpleStringProperty row;
	private SimpleStringProperty column;
	private SimpleStringProperty waterFlow;
	public PanelInfo(String installationDate, String type, String date, String outputPower, String manufacturer, String solarGain, String voltage, String current, String surfaceArea,String row, String column) {
		this.installationDate = new SimpleStringProperty(installationDate);
		this.type = new SimpleStringProperty(type);
		this.date = new SimpleStringProperty(date);
		this.outputPower = new SimpleStringProperty(outputPower);
		this.manufacturer = new SimpleStringProperty(manufacturer);
		this.solarGain = new SimpleStringProperty(solarGain);
		this.voltage = new SimpleStringProperty(voltage);
		this.current = new SimpleStringProperty(current);
		this.surfaceArea = new SimpleStringProperty(surfaceArea);
		this.row = new SimpleStringProperty(row);
		this.column = new SimpleStringProperty(column);
	}
	public PanelInfo(String installationDate, String type, String date, String outputPower, String manufacturer, String solarGain, String tempIn, String tempOut, String timeInSec, String waterFlow,String surfaceArea,String row, String column) {
		this.installationDate = new SimpleStringProperty(installationDate);
		this.type = new SimpleStringProperty(type);
		this.date = new SimpleStringProperty(date);
		this.outputPower = new SimpleStringProperty(outputPower);
		this.manufacturer = new SimpleStringProperty(manufacturer);
		this.solarGain = new SimpleStringProperty(solarGain);
		this.tempIn = new SimpleStringProperty(tempIn);
		this.tempOut = new SimpleStringProperty(tempOut);
		this.timeInSec = new SimpleStringProperty(timeInSec);
		this.waterFlow = new SimpleStringProperty(waterFlow);
		this.surfaceArea = new SimpleStringProperty(surfaceArea);
		this.row = new SimpleStringProperty(row);
		this.column = new SimpleStringProperty(column);
	}
	public String getInstallationDate() {
		return installationDate.get();
	}
	public void setInstallationDate(SimpleStringProperty installationDate) {
		this.installationDate = installationDate;
	}
	public String getType() {
		return type.get();
	}
	public void setType(SimpleStringProperty type) {
		this.type = type;
	}
	public String getDate() {
		return date.get();
	}
	public void setDate(SimpleStringProperty date) {
		this.date = date;
	}
	public String getOutputPower() {
		return outputPower.get();
	}
	public void setOutputPower(SimpleStringProperty outputPower) {
		this.outputPower = outputPower;
	}
	public String getManufacturer() {
		return manufacturer.get();
	}
	public void setManufacturer(SimpleStringProperty manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getSolarGain() {
		return solarGain.get();
	}
	public void setSolarGain(SimpleStringProperty solarGain) {
		this.solarGain = solarGain;
	}
	public String getVoltage() {
		return voltage.get();
	}
	public void setVoltage(SimpleStringProperty voltage) {
		this.voltage = voltage;
	}
	public String getCurrent() {
		return current.get();
	}
	public void setCurrent(SimpleStringProperty current) {
		this.current = current;
	}
	public String getTempIn() {
		return tempIn.get();
	}
	public void setTempIn(SimpleStringProperty tempIn) {
		this.tempIn = tempIn;
	}
	public String getTempOut() {
		return tempOut.get();
	}
	public void setTempOut(SimpleStringProperty tempOut) {
		this.tempOut = tempOut;
	}
	public String getTimeInSec() {
		return timeInSec.get();
	}
	public void setTimeInSec(SimpleStringProperty timeInSec) {
		this.timeInSec = timeInSec;
	}
	public String getWaterFlow() {
		return waterFlow.get();
	}
	public void setWaterFlow(SimpleStringProperty waterFlow) {
		this.waterFlow = waterFlow;
	}
	public String getSurfaceArea() {
		return surfaceArea.get();
	}
	public void setSurfaceArea(SimpleStringProperty surfaceArea) {
		this.surfaceArea = surfaceArea;
	}
	public String getRow() {
		return row.get();
	}
	public void setRow(SimpleStringProperty row) {
		this.row = row;
	}
	public String getColumn() {
		return column.get();
	}
	public void setColumn(SimpleStringProperty column) {
		this.column = column;
	}
}