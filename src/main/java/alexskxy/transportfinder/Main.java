package alexskxy.transportfinder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        var url = Paths.get("./src/main/resources/fxml/main.fxml").toUri().toURL();
        var fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.load();

        Parent root = fxmlLoader.getRoot();
        MainController mc = fxmlLoader.getController();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
