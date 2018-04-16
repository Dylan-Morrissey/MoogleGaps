import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {

    private static ArrayList<GraphNode> graphNodes = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(new File("MoogleGaps.txt"));
        input.useDelimiter("\r\n");

        while (input.hasNextLine()) {
            if (input.findInLine("graphNode").equals("graphNode")) {
                String n = input.nextLine();
                String[] splitNodeStrings = n.split(" ");
                String name = splitNodeStrings[2];

                GraphNode graphNode = new GraphNode(name);
                if (!graphNodes.contains(graphNode.getName())) {
                    graphNodes.add(graphNode);
                    System.out.println(graphNode.getName() + " added!");
                } else {
                    System.out.println("Error, City already Exists!");
                }
            } else if (input.findInLine("graphLink").equals("graphLink")) {
                Boolean sourceNodeExists = false;
                Boolean destNodeExists = false;
                String l = input.nextLine();
                String[] splitLinkStrings = l.split(" ");
                String sourceNode = splitLinkStrings[3];
                String destNode = splitLinkStrings[5];
                int cost = Integer.parseInt(splitLinkStrings[7]);
                GraphNode realSourceNode = null;
                GraphNode realDestNode = null;

                for (int i = 0; i < graphNodes.size(); i++) {
                    if ((graphNodes.get(i).getName().equals(sourceNode)) && (!sourceNodeExists)) {
                        sourceNodeExists = true;
                        realSourceNode = graphNodes.get(i);
                    }
                    if ((graphNodes.get(i).getName().equals(destNode)) && (!destNodeExists)) {
                        destNodeExists = true;
                        realDestNode = graphNodes.get(i);
                    }
                }
                if (sourceNodeExists && destNodeExists) {
                    GraphLink graphLink = new GraphLink(realSourceNode, realDestNode, cost);
                    System.out.println("Link from " + realSourceNode.getName() + " to " + realDestNode.getName() + " created!");
                }
            }
        }
        launch(args);
    }
}