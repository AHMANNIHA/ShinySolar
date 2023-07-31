import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import java.net.URL;
public class Loader
{
	private Pane scene;
	public Pane pane(String fName) {
		try {
			URL fileUrl = AppInitializer.class.getResource("/"+fName);
			assert fileUrl != null;
			scene = FXMLLoader.load(fileUrl);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return scene;
	}
}