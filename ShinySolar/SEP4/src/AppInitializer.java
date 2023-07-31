import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;
public class AppInitializer extends Application {
	public void start(Stage stg) {
		 try {
			 Parent root = FXMLLoader.load(
					 Objects.requireNonNull(getClass().getResource("View/SignIn.fxml")));
	 		Scene scene = new Scene(root);
	 		stg.setScene(scene);
	 		stg.show();
	 	}catch(Exception e) {
	 		e.printStackTrace();
	 	}
 	}
 	public static void main(String[] args) {
 		launch(args);
	}
}