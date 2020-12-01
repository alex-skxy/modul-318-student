package alexskxy.transportfinder.part;

import alexskxy.swisstransport.TransportService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class SearchConnectionsController extends TitledPane implements Initializable {
    private final TransportService transportService;
    @FXML public Button showStartPoint;
    @FXML public TextField fromInput;
    @FXML public TextField toInput;
    @FXML public Button showEndPoint;
    @FXML public TimeSpinner timePicker;
    @FXML public DatePicker datePicker;
    @FXML public ToggleGroup departureOrArrival;
    @FXML public ToggleButton departure;
    @FXML public ToggleButton arrival;
    public ShowConnectionsController showConnectionsController;

    public SearchConnectionsController() {
        this.transportService = new TransportService();
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
        String from = fromInput.getText();
        String to = toInput.getText();
        LocalDate date = datePicker.getValue();
        LocalTime time = timePicker.getValue();
        boolean isArrival = departureOrArrival.getSelectedToggle().equals(arrival);

        showConnectionsController.show(transportService.getConnections(from, to, date, time, isArrival).getConnections());
    }
}
