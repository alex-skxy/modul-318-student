package alexskxy.transportfinder.part;

import alexskxy.swisstransport.TransportService;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
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
import java.util.stream.Collectors;

public class SearchConnectionsController extends TitledPane implements Initializable {
    private final TransportService transportService;
    @FXML public Button showStartPoint;
    @FXML public AutoComplete fromInput;
    @FXML public AutoComplete toInput;
    @FXML public Button showEndPoint;
    @FXML public TimeSpinner timePicker;
    @FXML public DatePicker datePicker;
    @FXML public ToggleGroup departureOrArrival;
    @FXML public ToggleButton departure;
    @FXML public ToggleButton arrival;
    @FXML public Button showConnections;
    @FXML public Button showDepartures;
    public ShowConnectionsController showConnectionsController;
    public DepartureBoardController departureBoardController;

    public SearchConnectionsController() throws IOException {
        this.transportService = new TransportService();
        var fxmlLoader = new FXMLLoader(getClass().getResource("searchconnections.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fromInput.setEntrySource(stationName ->
                transportService.getStations(stationName).stations
                        .stream().map(station -> station.name)
                        .collect(Collectors.toList())
        );
        toInput.setEntrySource(stationName ->
                transportService.getStations(stationName).stations
                        .stream().map(station -> station.name)
                        .collect(Collectors.toList()));

        datePicker.setValue(LocalDate.now());
        departure.setSelected(true);

        BooleanBinding fromFieldEmpty = Bindings.createBooleanBinding(
                () -> fromInput.getText().isEmpty(),
                fromInput.textProperty());
        BooleanBinding toFieldEmpty = Bindings.createBooleanBinding(
                () -> toInput.getText().isEmpty(),
                toInput.textProperty());

        showConnections.disableProperty().bind(fromFieldEmpty.or(toFieldEmpty));
        showDepartures.disableProperty().bind(fromFieldEmpty);
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
        String from = fromInput.getText();
        departureBoardController.show(transportService.getStationBoard(from));
    }
}
