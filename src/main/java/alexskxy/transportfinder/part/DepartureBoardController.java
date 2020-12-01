package alexskxy.transportfinder.part;

import alexskxy.swisstransport.entity.StationBoardRoot;
import alexskxy.transportfinder.view.DepartureView;
import alexskxy.transportfinder.view.DepartureViewMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class DepartureBoardController extends TitledPane implements Initializable {
    @FXML public TableView<DepartureView> departureView;
    public ObservableList<DepartureView> departures = FXCollections.observableArrayList();

    public DepartureBoardController() {
        try {
            URL url = Paths.get("./src/main/resources/fxml/departureboard.fxml").toUri().toURL();
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

    public void show(StationBoardRoot stationBoardRoot) {
        this.departures = FXCollections.observableArrayList(DepartureViewMapper.mapToViews(stationBoardRoot.stationBoard));
        departureView.setItems(this.departures);
        this.setCollapsible(true);
        this.setExpanded(true);
    }
}