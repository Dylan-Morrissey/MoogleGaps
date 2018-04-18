import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;


public class StartScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox endDest;

    @FXML
    private MenuItem exitButton;

    @FXML
    private ListView<?> routeList;

    @FXML
    private ChoiceBox startDest;

    static ObservableList<String> destinations = FXCollections.observableArrayList(Main.graphNodeNames);

    @FXML
    void closeProgramme(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert endDest != null : "fx:id=\"endDest\" was not injected: check your FXML file 'startScreen.fxml'.";
        assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'startScreen.fxml'.";
        assert routeList != null : "fx:id=\"routeList\" was not injected: check your FXML file 'startScreen.fxml'.";
        assert startDest != null : "fx:id=\"startDest\" was not injected: check your FXML file 'startScreen.fxml'.";
        startDest.setItems(destinations);
        endDest.setItems(destinations);
    }

}
