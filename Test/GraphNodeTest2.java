import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GraphNodeTest2 {

    private GraphNode q = new GraphNode("Q");
    private GraphNode w = new GraphNode("W");
    private GraphNode e = new GraphNode("E");
    private GraphNode r = new GraphNode("R");
    private GraphNode t = new GraphNode("T");
    private GraphNode y = new GraphNode("Y");

    @Before
    public void setUp() {
        q.connectToNodeUndirected(q, w, 5, 'M');
        w.connectToNodeUndirected(w, y, 20, 'N');
        w.connectToNodeUndirected(w, e, 2, 'R');
        e.connectToNodeUndirected(e, t, 7, 'N');
        t.connectToNodeUndirected(t, r, 4, 'R');
        t.connectToNodeUndirected(t, y, 7, 'M');
        r.connectToNodeUndirected(r, y, 6, 'R');
    }

    //Regular recursive depth-first graph traversal
    public void traverseGraphDepthFirst(GraphNode from, List<GraphNode> encountered){
        System.out.println(from.getName());
        if(encountered==null) encountered=new ArrayList<>(); //First node so create new (empty) encountered list
        encountered.add(from);
        for(GraphLink adjLink : from.getGraphLinkList())
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