package Domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.sql.Date;
import java.sql.Timestamp;
public class Panel
{
	private final int id;
	private final double surfaceArea;
	private final Date installationDate;
	private final SimpleStringProperty type;
	private final SimpleIntegerProperty column;
	private final SimpleIntegerProperty row;
	private Timestamp convertToSqlTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	public Panel(String type, int column, int row, int id, double surfaceArea, String stringDate) {
		this.type = new SimpleStringProperty(type);
		this.column = new SimpleIntegerProperty(column);
		this.row = new SimpleIntegerProperty(row);
		this.id = id;
		this.surfaceArea = surfaceArea;
		this.installationDate = Date.valueOf(stringDate);
	}
	public Timestamp getInstallationTimestamp() {
		return convertToSqlTimestamp(installationDate);
	}
	public int getCol() {
		return column.get();
	}
	public int getRow() {
		return row.get();
	}
	public String getType() {
		return type.get();
	}
	public int getManufacturerId() {
		return id;
	}
	public double getSurfaceArea() {
		return surfaceArea;
	}
}