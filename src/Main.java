import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {

    static ArrayList<GraphNode> graphNodes = new ArrayList<>();
    static ArrayList<String> graphNodeNames = new ArrayList<>();
    static Stage ps;
    static AnchorPane startScreen;

    @Override
    public void start(Stage stage) {
        try {
            ps = stage;
            startScreen = FXMLLoader.load(getClass().getResource("StartScreen.fxml"));

            ps.setScene(new Scene(startScreen, 1000, 600));
            ps.setTitle("Welcome to MoogleGaps!");
            ps.show();
            System.out.println("I got here");
            ps.setOnCloseRequest(e -> {
                try {
                    closeProgramme();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(new File("MoogleGaps.txt"));
        input.useDelimiter("\r\n");

        while (input.hasNextLine()) {
            String in= input.nextLine();
            if (in.contains("graphNode")) {
                String[] splitNodeStrings = in.split(" ");
                String name = splitNodeStrings[2];

                GraphNode graphNode = new GraphNode(name );
                if (!graphNodeNames.contains(graphNode.getName())) {
                    graphNodes.add(graphNode);
                    graphNodeNames.add(graphNode.getName());
                    System.out.println(graphNode.getName() + " added!");
                } else {
                    System.out.println("Error, City already Exists!");
                }
            } else if (in.contains("graphLink")) {
                Boolean sourceNodeExists = false;
                Boolean destNodeExists = false;
                String[] splitLinkStrings = in.split(" ");
                String sourceNode = splitLinkStrings[2];
                String destNode = splitLinkStrings[4];
                char roadType = splitLinkStrings[8].charAt(0);
                int distance = Integer.parseInt(splitLinkStrings[6]);
                GraphNode realSourceNode = null;
                GraphNode realDestNode = null;

                for (GraphNode graphNode : graphNodes) {
                    if ((graphNode.getName().equals(sourceNode)) && (!sourceNodeExists)) {
                        sourceNodeExists = true;
                        realSourceNode = graphNode;
                    }
                    if ((graphNode.getName().equals(destNode)) && (!destNodeExists)) {
                        destNodeExists = true;
                        realDestNode = graphNode;
                    }
                }
                if (sourceNodeExists && destNodeExists) {
                    GraphLink graphLink = new GraphLink(realSourceNode, realDestNode, distance, roadType);
                    for (GraphNode graphNode : graphNodes) {
                        if (graphNode.getName().equals(realSourceNode.getName())) {
                            realSourceNode.getGraphLinkList().add(graphLink);
                            graphNode.connectToNodeUndirected(realSourceNode, realDestNode, distance, roadType);
                        }
                    }
                    System.out.println("Link from " + realSourceNode.getName() + " to " + realDestNode.getName() + " created!");
                }
            }
        }
        launch(args);
    }




    private void closeProgramme() {
        ps.close();
    }
}