package alexskxy.transportfinder.part;

import alexskxy.swisstransport.TransportService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
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

    public SearchConnectionsController() throws IOException {
        this.transportService = new TransportService();
        var fxmlLoader = new FXMLLoader(getClass().getResource("searchconnections.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datePicker.setValue(LocalDate.now());
        departure.setSelected(true);
    }

    public void showConnections(ActionEvent actionEvent) {
        String from = fromInput.getText();
        String to = toInput.getText();
        LocalDate date = datePicker.getValue();
        LocalTime time = timePicker.getValue();
        boolean isArrival = departureOrArrival.getSelectedToggle().equals(arrival);

        showConnectionsController.show(transportService.getConnections(from, to, date, time, isArrival).getConnections());
    }

    public void showDepartures(ActionEvent actionEvent) {

    }
}
