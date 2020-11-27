package alexskxy.transportfinder.parts;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class ShowConnectionsController extends TitledPane implements Initializable {
    public ShowConnectionsController() {
        try {
            URL url = Paths.get("./src/main/resources/fxml/showconnections.fxml").toUri().toURL();
            var fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(url);
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
