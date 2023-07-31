import Conectivity.Database;
import Domain.Questions;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
public class UserController {
	public Text actionTarget;
	public Button panelBtn;
	public Button manufacturerBtn;
	public Button measurementBtn;
	public Button signInBtn;
	public Button signOutBtn;
	public Button registerBtn;
	public Button hintBtn;
	Database database = new Database();
	Questions popup = new Questions();
	public BorderPane borderPane;
	public TextField usernameField;
	public TextField passwordField;
	public void signIn() throws IOException
	{
			if(!database.authenticateUser(usernameField.getText().trim(), passwordField.getText().trim()) )
			{
				Alert msg = new Alert(Alert.AlertType.ERROR);
				msg.setTitle("Login Failed");
				msg.setHeaderText(null);
				msg.setContentText("Your username or password is incorrect! Try again.");
				msg.show();
			}
			else
			{
				Stage stage = (Stage) signInBtn.getScene().getWindow();
				stage.close();
				Parent root = FXMLLoader.load(
						Objects.requireNonNull(getClass().getResource("View/User.fxml")));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
		}
	public void save () {
		Stage stage = (Stage) borderPane.getScene().getWindow();
		PrinterJob printerJob = PrinterJob.createPrinterJob();
		if (printerJob.showPrintDialog(stage) && printerJob.printPage(borderPane))
		{
			printerJob.endJob();
		}
	}
		public void openManufacturers() {
    	Loader object = new Loader();
    	Pane view = object.pane("View/Manufacturer.fxml");
			borderPane.setCenter(view);

    }
		public void openPanels() {
    	Loader object = new Loader();
    	Pane view = object.pane("View/Panel.fxml");
			borderPane.setCenter(view);
    }
	public void openMeasurements() {
    	Loader object = new Loader();
    	Pane view = object.pane("View/Measurement.fxml");
		borderPane.setCenter(view);
    }
	public void signOut() throws IOException
	{
		Stage stage = (Stage) signOutBtn.getScene().getWindow();
		stage.close();
		Parent root = FXMLLoader.load(
				Objects.requireNonNull(getClass().getResource("View/SignIn.fxml")));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void register()
	{
		informAlert("Register", "Contact your administrator to get added into the database");
	}
	public void informAlert(String title, String msg) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION,msg, ButtonType.CLOSE);
		alert.setTitle(title);
		alert.showAndWait();
		alert.getResult();
	}
	public void hint(){
		informAlert("Hint", "Both username and password are SEP4");
	}
}