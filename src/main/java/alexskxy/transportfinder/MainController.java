package alexskxy.transportfinder;

import alexskxy.transportfinder.part.DepartureBoardController;
import alexskxy.transportfinder.part.SearchConnectionsController;
import alexskxy.transportfinder.part.ShowConnectionsController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public SearchConnectionsController searchConnectionsComponent;
    public ShowConnectionsController showConnectionsComponent;
    public DepartureBoardController departureBoardController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchConnectionsComponent.showConnectionsController = showConnectionsComponent;
        searchConnectionsComponent.departureBoardController = departureBoardController;
    }
}
