import java.util.ArrayList;
import java.util.List;

public class GraphNode {

    private String name;
    private List<GraphLink> adjList=new ArrayList<>(); //Adjacency list now contains link objects = key!
    //Could use any concrete List implementation

    public List<GraphLink> getAdjList() {
        return adjList;
    }

    private void setAdjList(List<GraphLink> adjList) {
        this.adjList = adjList;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public GraphNode(String name) {
        this.setName(name);
    }

    public void connectToNodeUndirected(GraphNode destNode, int cost) {
        adjList.add( new GraphLink(destNode,cost) ); //Add new link object to source adjacency list
        destNode.adjList.add( new GraphLink(this,cost) ); //Add new link object to destination adjacency list
    }
}