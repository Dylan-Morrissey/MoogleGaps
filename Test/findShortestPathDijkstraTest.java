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
        List<GraphNode> encountered=new ArrayList<>(), unencountered=new ArrayList<>();
        startNode.nodeValue=0;
        unencountered.add(startNode);
        GraphNode currentNode;
        do{
            currentNode=unencountered.remove(0);
            encountered.add(currentNode);
            if(currentNode.getName().equals(lookingfor)){
                cp.pathList.add(currentNode);
                cp.pathCost=currentNode.nodeValue;
                while(currentNode!=startNode) {
                    boolean foundPrevPathNode=false;
                    for(GraphNode n : encountered) {
                        for(GraphLink e : n.graphLinkList)
                            if(e.getDestNode()==currentNode && currentNode.nodeValue-e.getDistance()==n.nodeValue){
                                cp.pathList.add(0, n);

                                currentNode=n;
                                foundPrevPathNode=true;
                                break;
                            }
                        if(foundPrevPathNode) break;
                    }
                }

                for(GraphNode n : encountered) n.nodeValue=Integer.MAX_VALUE;
                for(GraphNode n : unencountered) n.nodeValue=Integer.MAX_VALUE;
                System.out.println(cp.pathCost);
                System.out.println(cp.pathList.toString());
                return cp;
            }

            for(GraphLink e : currentNode.graphLinkList)
                if(!encountered.contains(e.getDestNode())) {
                    e.getDestNode().nodeValue=Integer.min(e.getDestNode().nodeValue, currentNode.nodeValue+e.getDistance());

                    unencountered.add(e.getDestNode());
                }
            Collections.sort(unencountered,(n1, n2)->n1.nodeValue-n2.nodeValue);

        }while(!unencountered.isEmpty());
        return null;
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
