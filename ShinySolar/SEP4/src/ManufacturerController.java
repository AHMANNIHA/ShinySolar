import Conectivity.Database;
import Domain.Manufacturer;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ManufacturerController extends UserController implements Initializable {
    public Button updateManufacturerBtn;
    public Button addManufacturerBtn;
    public Button refreshManufacturerBtn;
    public Button helpManufacturerBtn;
    public ComboBox<String> attributeBox;
    public TextField attributeField;
    public ComboBox<String> manufacturerBox;
    public TableColumn<Manufacturer, String> colId;
    public TableColumn<Manufacturer, String> colAddress;
    public TableColumn<Manufacturer, String> colEmail;
    public TableColumn<Manufacturer, String> colName;
    public TableColumn<Manufacturer, String> colPhone;
    public TableColumn<Manufacturer, String> colWebsite;
    public TableView<Manufacturer> manufacturerTable;
    public Button deleteManufacturerBtn;
    public boolean manufacturerAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Do you want to add new Manufacturer?", ButtonType.OK, ButtonType.CANCEL);
        alert.setTitle("Add manufacturer");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            return true;
        }
        alert.getResult();
        return false;
    }
    public void addManufacturer() {
            if (manufacturerAlert()) {
                new Database();
                database.addManufacturer(popup.addManufacturer());
            }
    }
    public void updateManufacturers() {
        new Database();
        try {
            database.updateManufacturer(manufacturerBox.getValue(), attributeBox.getValue(), attributeField.getText());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        manufacturerBox.getItems().removeAll(manufacturerBox.getItems());
        manufacturerBox.getItems().addAll(database.manufacturersNames());
        manufacturerBox.setValue(null);
        attributeBox.setValue(null);
        attributeField.clear();
        refreshTable();
    }

    public void informAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.CLOSE);
        alert.setTitle(title);
        alert.showAndWait();
        alert.getResult();
    }

    public void refreshManufacturers() {
        new Database();
        refreshTable();
    }

    public void helpManufacturer() {
        informAlert("Help", "Allows adding manufacturers and updating existing manufacturers in the database in case you wish to add more manufacturers or change the information.");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Database();
        refreshTable();
        manufacturerBox.getItems().addAll(database.manufacturersNames());
        attributeBox.getItems().addAll("name", "address", "phone", "email", "website");
    }

    private void refreshTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("IdInfo"));
        colName.setCellValueFactory(new PropertyValueFactory<>("NameInfo"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("AddressInfo"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("PhoneInfo"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("EmailInfo"));
        colWebsite.setCellValueFactory(new PropertyValueFactory<>("WebsiteInfo"));
        manufacturerTable.setItems(database.manufacturerList());
    }

    public void deleteManufacturer() {
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Not Authorized");
        msg.setHeaderText(null);
        msg.setContentText("If you delete manufacturer you also delete all the panels associated with that manufacturer and those panels measurements are being used");
        msg.show();
    }
}