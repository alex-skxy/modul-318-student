package alexskxy.transportfinder.part;

import com.google.api.client.util.DateTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class SearchConnectionsController extends TitledPane implements Initializable {
    @FXML public Button showStartPoint;
    @FXML public TextField startLookup;
    @FXML public TextField endLookup;
    @FXML public Button showEndPoint;
    @FXML public Spinner<DateTime> timePicker;
    @FXML public DatePicker datePicker;
    @FXML public ToggleGroup departureOrArrival;
    @FXML public ToggleButton departure;
    @FXML public ToggleButton arrival;

    public SearchConnectionsController() {
        try {
            URL url = Paths.get("./src/main/resources/fxml/searchconnections.fxml").toUri().toURL();
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

    public void showConnections(ActionEvent actionEvent) {

    }
}
