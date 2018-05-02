import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class findShortestPathDijkstraTest {
    private GraphNode q = new GraphNode("Q");
    private GraphNode w = new GraphNode("Test");
    private GraphNode e = new GraphNode("E");
    private GraphNode r = new GraphNode("R");

    @Before
    public void setUp() {
        q.connectToNodeUndirected(q, w, 5, 'M');
        w.connectToNodeUndirected(q, e, 1, 'N');
        w.connectToNodeUndirected(e, r, 2, 'R');
        e.connectToNodeUndirected(r, w, 7, 'N');
    }

    private static CostedPath cp = new CostedPath();
    public static CostedPath findShortestPathDijkstra(GraphNode startNode, String lookingfor){

        cp.pathList.clear();
        List<GraphNode> encountered=new ArrayList<>(), unencountered=new ArrayList<>(); //Create encountered/unencountered lists
        startNode.nodeValue=0; //Set the starting node value to zero
        unencountered.add(startNode); //Add the start node as the only value in the unencountered list to start
        GraphNode currentNode;
        do{ //Loop until unencountered list is empty
            currentNode=unencountered.remove(0); //Get the first unencountered node (sorted list, so will have lowest value)
            encountered.add(currentNode); //Record current node in encountered list
            if(currentNode.getName().equals(lookingfor)){ //Found goal - assemble path list back to start and return it
                cp.pathList.add(currentNode); //Add the current (goal) node to the result list (only element)
                cp.pathCost=currentNode.nodeValue; //The total cheapest path cost is the node value of the current/goal node
                while(currentNode!=startNode) { //While we're not back to the start node...
                    boolean foundPrevPathNode=false; //Use a flag to identify when the previous path node is identified
                    for(GraphNode n : encountered) { //For each node in the encountered list...
                        for(GraphLink e : n.graphLinkList) //For each edge from that node...
                            if(e.getDestNode()==currentNode && currentNode.nodeValue-e.getDistance()==n.nodeValue){ //If that edge links to the
//current node and the difference in node values is the cost of the edge -> found path node!
                                cp.pathList.add(0, n); //Add the identified path node to the front of the result list

                                currentNode=n; //Move the currentNode reference back to the identified path node
                                foundPrevPathNode=true; //Set the flag to break the outer loop
                                break; //We've found the correct previous path node and moved the currentNode reference
//back to it so break the inner loop
                            }
                        if(foundPrevPathNode) break; //We've identified the previous path node, so break the inner loop to continue
                    }
                }
//Reset the node values for all nodes to (effectively) infinity so we can search again (leave no footprint!)
                for(GraphNode n : encountered) n.nodeValue=Integer.MAX_VALUE;
                for(GraphNode n : unencountered) n.nodeValue=Integer.MAX_VALUE;
                System.out.println(cp.pathCost);
                System.out.println(cp.pathList.toString());
                return cp; //The costed (cheapest) path has been assembled, so return it!
            }
//We're not at the goal node yet, so...
            for(GraphLink e : currentNode.graphLinkList) //For each edge/link from the current node...
                if(!encountered.contains(e.getDestNode())) { //If the node it leads to has not yet been encountered (i.e. processed)
                    e.getDestNode().nodeValue=Integer.min(e.getDestNode().nodeValue, currentNode.nodeValue+e.getDistance()); //Update the node value at the end
                    //of the edge to the minimum of its current value or the total of the current node's value plus the cost of the edge
                    unencountered.add(e.getDestNode());
                }
            Collections.sort(unencountered,(n1, n2)->n1.nodeValue-n2.nodeValue); //Sort in ascending node value order
            // ;
        }while(!unencountered.isEmpty());
        return null; //No path found, so return null
    }


    @Test
    public void testNodesCreated(){
        ArrayList<String> nameOnRoute = new ArrayList<>();
        ArrayList<GraphNode> nodesOnRoute = new ArrayList<>();
        GraphNode.findShortestPathDijkstra(q, "Test");
        for (GraphNode h : GraphNode.getCp().pathList) {
            nameOnRoute.add(h.getName());
            nodesOnRoute.add(h);
        }
        ArrayList<String> equals = new ArrayList<>();
        equals.add("Q");
        equals.add("Test");
        assertEquals(nameOnRoute,equals);

    }
}
