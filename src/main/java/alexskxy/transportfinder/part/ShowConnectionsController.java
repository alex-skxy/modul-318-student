package alexskxy.transportfinder.part;

import alexskxy.swisstransport.entity.Connection;
import alexskxy.transportfinder.view.ConnectionView;
import alexskxy.transportfinder.view.ConnectionViewMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShowConnectionsController extends TitledPane implements Initializable {
    @FXML public TableView<ConnectionView> connectionView;
    public ObservableList<ConnectionView> connections = FXCollections.observableArrayList();

    public ShowConnectionsController() throws IOException {
        var fxmlLoader = new FXMLLoader(getClass().getResource("showconnections.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void show(List<Connection> connections) {
        this.connections = FXCollections.observableArrayList(ConnectionViewMapper.mapToViews(connections));
        connectionView.setItems(this.connections);
        this.setCollapsible(true);
        this.setExpanded(true);
    }
}
