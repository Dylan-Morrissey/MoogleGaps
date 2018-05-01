import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class StartScreenController {

    private ArrayList<Integer> speed = new ArrayList<>();

    @FXML
    private Button shortestRouteButton;

    @FXML
    private Button quickestRouteButton;

    @FXML
    private ChoiceBox<String> endDest;

    @FXML
    private MenuItem exitButton;

    @FXML
    private ListView<String> routeListView;

    @FXML
    private ChoiceBox<String> startDest;

    @FXML
    private Label distanceLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label avgSpeedLabel;


    private static ObservableList<String> destinations = FXCollections.observableArrayList(Main.graphNodeNames);


    @FXML
    void closeProgramme(ActionEvent event) {
        Main.ps.close();
    }

    @FXML
    void findShortestRoute (ActionEvent event) {
        ArrayList<String> nameOnRoute = new ArrayList<>();
        ArrayList<GraphNode> nodesOnRoute = new ArrayList<>();
        for (String start : Main.graphNodeNames) {
            if (start.equals(startDest.getSelectionModel().getSelectedItem())) {
                for (GraphNode thisOne : Main.graphNodes) {
                    if (thisOne.getName().equals(start)) {
                        GraphNode.findShortestPathDijkstra(thisOne, endDest.getSelectionModel().getSelectedItem());
                        for (GraphNode h : GraphNode.getCp().pathList) {
                            nameOnRoute.add(h.getName());
                            nodesOnRoute.add(h);
                        }
                        ObservableList<String> routeList = FXCollections.observableArrayList(nameOnRoute);
                        routeListView.getItems().clear();
                        routeListView.setItems(routeList);
                        System.out.println(nameOnRoute);
                    }
                }
            }
        }
        for (GraphNode k : nodesOnRoute) {
            for (String j : k.getRoadTypes()) {
                if (j.equals("R")) {
                    speed.add(80);
                } else if (j.equals("N")) {
                    speed.add(100);
                } else if (j.equals("M")) {
                    speed.add(120);
                }
            }
        }
        nodesOnRoute.clear();
        float divSpeed = 0;
        System.out.println(speed);
        for (int d : speed) {
            divSpeed = divSpeed +d;
        }
        System.out.println(divSpeed);
        divSpeed = divSpeed /speed.size();
        System.out.println(divSpeed);
        float time = GraphNode.getCp().pathCost / divSpeed;
        System.out.println(GraphNode.getCp().pathCost);
        System.out.println(divSpeed);
        System.out.println(time);
        timeLabel.setVisible(true);
        timeLabel.setText("Time = " + String.valueOf(new DecimalFormat("##.##").format(time)) + " Hours / " + String.valueOf(new DecimalFormat("##").format(time*60)) + " Minutes");
        distanceLabel.setVisible(true);
        distanceLabel.setText("Distance = " + String.valueOf(GraphNode.getCp().pathCost) + "km / " + String.valueOf(new DecimalFormat("##.#").format(GraphNode.getCp().pathCost / 1.6) + "Miles"));
        avgSpeedLabel.setVisible(true);
        avgSpeedLabel.setText("Average Speed = " + String.valueOf(divSpeed) + "Km/h");
    }

    @FXML
    private void findQuickestRoute (ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert shortestRouteButton != null : "fx:id=\"ShortestRouteButton\" was not injected: check your FXML file 'startScreen.fxml'.";
        assert quickestRouteButton != null : "fx:id=\"QuickestRouteButton\" was not injected: check your FXML file 'startScreen.fxml'.";
        assert endDest != null : "fx:id=\"endDest\" was not injected: check your FXML file 'startScreen.fxml'.";
        assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'startScreen.fxml'.";
        assert distanceLabel != null : "fx:id=\"distanceLabel\" was not injected: check your FXML file 'startScreen.fxml'.";
        assert timeLabel != null : "fx:id=\"timeLabel\" was not injected: check your FXML file 'startScreen.fxml'.";
        assert avgSpeedLabel != null : "fx:id=\"avgSpeedLabel\" was not injected: check your FXML file 'startScreen.fxml'.";
        assert startDest != null : "fx:id=\"startDest\" was not injected: check your FXML file 'startScreen.fxml'.";
        startDest.setItems(destinations);
        endDest.setItems(destinations);
    }

}
