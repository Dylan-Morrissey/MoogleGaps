import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GraphNodeTest {

    private GraphNode q = new GraphNode("Q");
    private GraphNode w = new GraphNode("W");
    private GraphNode e = new GraphNode("E");
    private GraphNode r = new GraphNode("R");
    private GraphNode t = new GraphNode("T");
    private GraphNode y = new GraphNode("Y");

    @Before
    public void setUp() {
        q.connectToNodeUndirected(w, 5);
        w.connectToNodeUndirected(y, 20);
        w.connectToNodeUndirected(e, 2);
        e.connectToNodeUndirected(t, 7);
        t.connectToNodeUndirected(r, 4);
        t.connectToNodeUndirected(y, 7);
        r.connectToNodeUndirected(y, 6);
    }

    //Regular recursive depth-first graph traversal
    public void traverseGraphDepthFirst(GraphNode from, List<GraphNode> encountered){
        System.out.println(from.getName());
        if(encountered==null) encountered=new ArrayList<>(); //First node so create new (empty) encountered list
        encountered.add(from);
        for(GraphLink adjLink : from.getAdjList())
            if(!encountered.contains(adjLink.getDestNode())) traverseGraphDepthFirst(adjLink.getDestNode(),encountered);
    }

    @Test
    public void testNodesCreated(){
        traverseGraphDepthFirst(q, null);
        traverseGraphDepthFirst(w, null);
        traverseGraphDepthFirst(e, null);
        traverseGraphDepthFirst(r, null);
        traverseGraphDepthFirst(t, null);
        traverseGraphDepthFirst(y, null);

    }
}