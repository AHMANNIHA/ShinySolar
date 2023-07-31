import Conectivity.Database;
import Domain.PanelInfo;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class PanelController extends UserController implements Initializable  {
	public Button searchPanelsBtn;
	public Button addPanelBtn;
	public Button helpPanelBtn;
	public DatePicker from;
	public DatePicker to;
	public TableColumn<PanelInfo, String> pvCol1;
	public TableColumn<PanelInfo, String> pvCol2;
	public TableColumn<PanelInfo, String> pvCol3;
	public TableColumn<PanelInfo, String> pvCol4;
	public TableColumn<PanelInfo, String> pvCol5;
	public TableColumn<PanelInfo, String> pvCol6;
	public TableColumn<PanelInfo, String> pvCol7;
	public TableColumn<PanelInfo, String> pvCol8;
	public TableColumn<PanelInfo, String> pvCol9;
	public TableColumn<PanelInfo, String> pvCol10;
	public TableColumn<PanelInfo, String> pvCol11;
	public TableColumn<PanelInfo, String> thCol1;
	public TableColumn<PanelInfo, String> thCol2;
	public TableColumn<PanelInfo, String> thCol3;
	public TableColumn<PanelInfo, String> thCol4;
	public TableColumn<PanelInfo, String> thCol5;
	public TableColumn<PanelInfo, String> thCol6;
	public TableColumn<PanelInfo, String> thCol7;
	public TableColumn<PanelInfo, String> thCol8;
	public TableColumn<PanelInfo, String> thCol9;
	public TableColumn<PanelInfo, String> thCol10;
	public TableColumn<PanelInfo, String> thCol11;
	public TableColumn<PanelInfo, String> thCol12;
	public TableColumn<PanelInfo, String> thCol13;
	public TableView<PanelInfo> pvTable;
	public TableView<PanelInfo> thTable;
	public ComboBox<String> manufacturersBox;
	public Button refreshBtn;

	public void searchPanels() {
		new Database();
		pvCol1.setCellValueFactory(new PropertyValueFactory<>("InstallationDate"));
		pvCol2.setCellValueFactory(new PropertyValueFactory<>("Type"));
		pvCol3.setCellValueFactory(new PropertyValueFactory<>("Date"));
		pvCol4.setCellValueFactory(new PropertyValueFactory<>("OutputPower"));
		pvCol5.setCellValueFactory(new PropertyValueFactory<>("Manufacturer"));
		pvCol6.setCellValueFactory(new PropertyValueFactory<>("SolarGain"));
		pvCol7.setCellValueFactory(new PropertyValueFactory<>("Voltage"));
		pvCol8.setCellValueFactory(new PropertyValueFactory<>("Current"));
		pvCol9.setCellValueFactory(new PropertyValueFactory<>("SurfaceArea"));
		pvCol10.setCellValueFactory(new PropertyValueFactory<>("Row"));
		pvCol11.setCellValueFactory(new PropertyValueFactory<>("Column"));
		thCol1.setCellValueFactory(new PropertyValueFactory<>("InstallationDate"));
		thCol2.setCellValueFactory(new PropertyValueFactory<>("Type"));
		thCol3.setCellValueFactory(new PropertyValueFactory<>("Date"));
		thCol4.setCellValueFactory(new PropertyValueFactory<>("OutputPower"));
		thCol5.setCellValueFactory(new PropertyValueFactory<>("Manufacturer"));
		thCol6.setCellValueFactory(new PropertyValueFactory<>("SolarGain"));
		thCol7.setCellValueFactory(new PropertyValueFactory<>("TempIn"));
		thCol8.setCellValueFactory(new PropertyValueFactory<>("TempOut"));
		thCol9.setCellValueFactory(new PropertyValueFactory<>("TimeInSec"));
		thCol10.setCellValueFactory(new PropertyValueFactory<>("WaterFlow"));
		thCol11.setCellValueFactory(new PropertyValueFactory<>("SurfaceArea"));
		thCol12.setCellValueFactory(new PropertyValueFactory<>("Row"));
		thCol13.setCellValueFactory(new PropertyValueFactory<>("Column"));
		pvTable.setItems(database.pvPanelInfo(from.getValue(), to.getValue(), manufacturersBox.getValue()));
		thTable.setItems(database.thPanelInfo(from.getValue(), to.getValue(), manufacturersBox.getValue()));
	}

	public boolean panelAlert() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION,"Do you want to add new panel?", ButtonType.OK, ButtonType.CANCEL);
		alert.setTitle("Add panel");
		alert.showAndWait();
		if(alert.getResult() == ButtonType.OK){
			return true;
		}
		alert.getResult();
		return false;
	}
	public void addPanels() {
				if (panelAlert()) {new Database();
				database.storePanel(popup.addPanel(database.manufacturersNames()));
				}
			}
	public void refreshPanel() {
		new Database();
		refreshTable();
	}
	private void refreshTable() {
		pvCol1.setCellValueFactory(new PropertyValueFactory<>("InstallationDate"));
		pvCol2.setCellValueFactory(new PropertyValueFactory<>("Type"));
		pvCol3.setCellValueFactory(new PropertyValueFactory<>("Date"));
		pvCol4.setCellValueFactory(new PropertyValueFactory<>("OutputPower"));
		pvCol5.setCellValueFactory(new PropertyValueFactory<>("Manufacturer"));
		pvCol6.setCellValueFactory(new PropertyValueFactory<>("SolarGain"));
		pvCol7.setCellValueFactory(new PropertyValueFactory<>("Voltage"));
		pvCol8.setCellValueFactory(new PropertyValueFactory<>("Current"));
		pvCol9.setCellValueFactory(new PropertyValueFactory<>("SurfaceArea"));
		pvCol10.setCellValueFactory(new PropertyValueFactory<>("Row"));
		pvCol11.setCellValueFactory(new PropertyValueFactory<>("Column"));
		thCol1.setCellValueFactory(new PropertyValueFactory<>("InstallationDate"));
		thCol2.setCellValueFactory(new PropertyValueFactory<>("Type"));
		thCol3.setCellValueFactory(new PropertyValueFactory<>("Date"));
		thCol4.setCellValueFactory(new PropertyValueFactory<>("OutputPower"));
		thCol5.setCellValueFactory(new PropertyValueFactory<>("Manufacturer"));
		thCol6.setCellValueFactory(new PropertyValueFactory<>("SolarGain"));
		thCol7.setCellValueFactory(new PropertyValueFactory<>("TempIn"));
		thCol8.setCellValueFactory(new PropertyValueFactory<>("TempOut"));
		thCol9.setCellValueFactory(new PropertyValueFactory<>("TimeInSec"));
		thCol10.setCellValueFactory(new PropertyValueFactory<>("WaterFlow"));
		thCol11.setCellValueFactory(new PropertyValueFactory<>("SurfaceArea"));
		thCol12.setCellValueFactory(new PropertyValueFactory<>("Row"));
		thCol13.setCellValueFactory(new PropertyValueFactory<>("Column"));
		pvTable.setItems(database.pvPanelInfo());
		thTable.setItems(database.thPanelInfo());
	}
	public void informAlert(String title, String msg) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION,msg, ButtonType.CLOSE);
		alert.setTitle(title);
		alert.showAndWait();
		alert.getResult();
	}
			public void helpPanel() {
				informAlert("Help", "Allows adding new panels and searching for existing panels in the database in case you wish to add more panels or search for a certain panel.");
			}
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			new Database();
			ArrayList<String> names = database.manufacturersNames();
			manufacturersBox.getItems().addAll(names);
			pvCol1.setCellValueFactory(new PropertyValueFactory<>("InstallationDate"));
			pvCol2.setCellValueFactory(new PropertyValueFactory<>("Type"));
			pvCol3.setCellValueFactory(new PropertyValueFactory<>("Date"));
			pvCol4.setCellValueFactory(new PropertyValueFactory<>("OutputPower"));
			pvCol5.setCellValueFactory(new PropertyValueFactory<>("Manufacturer"));
			pvCol6.setCellValueFactory(new PropertyValueFactory<>("SolarGain"));
			pvCol7.setCellValueFactory(new PropertyValueFactory<>("Voltage"));
			pvCol8.setCellValueFactory(new PropertyValueFactory<>("Current"));
			pvCol9.setCellValueFactory(new PropertyValueFactory<>("SurfaceArea"));
			pvCol10.setCellValueFactory(new PropertyValueFactory<>("Row"));
			pvCol11.setCellValueFactory(new PropertyValueFactory<>("Column"));
			thCol1.setCellValueFactory(new PropertyValueFactory<>("InstallationDate"));
			thCol2.setCellValueFactory(new PropertyValueFactory<>("Type"));
			thCol3.setCellValueFactory(new PropertyValueFactory<>("Date"));
			thCol4.setCellValueFactory(new PropertyValueFactory<>("OutputPower"));
			thCol5.setCellValueFactory(new PropertyValueFactory<>("Manufacturer"));
			thCol6.setCellValueFactory(new PropertyValueFactory<>("SolarGain"));
			thCol7.setCellValueFactory(new PropertyValueFactory<>("TempIn"));
			thCol8.setCellValueFactory(new PropertyValueFactory<>("TempOut"));
			thCol9.setCellValueFactory(new PropertyValueFactory<>("TimeInSec"));
			thCol10.setCellValueFactory(new PropertyValueFactory<>("WaterFlow"));
			thCol11.setCellValueFactory(new PropertyValueFactory<>("SurfaceArea"));
			thCol12.setCellValueFactory(new PropertyValueFactory<>("Row"));
			thCol13.setCellValueFactory(new PropertyValueFactory<>("Column"));
			pvTable.setItems(database.pvPanelInfo());
			thTable.setItems(database.thPanelInfo());
		}
}