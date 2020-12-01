package alexskxy.transportfinder;

import alexskxy.transportfinder.part.SearchConnectionsController;
import alexskxy.transportfinder.part.ShowConnectionsController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public SearchConnectionsController searchConnectionsComponent;
    public ShowConnectionsController showConnectionsComponent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
