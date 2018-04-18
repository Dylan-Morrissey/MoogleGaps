import java.util.ArrayList;
import java.util.List;

public class GraphNode {

    private String name;
    private List<GraphLink> graphLinkList=new ArrayList<>();

    public List<GraphLink> getGraphLinkList() {
        return graphLinkList;
    }

    private void setGraphLinkList(List<GraphLink> graphLinkList) {
        this.graphLinkList = graphLinkList;
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

    public void connectToNodeUndirected(GraphNode sourceNode, GraphNode destNode, int cost, char roadType) {
        graphLinkList.add( new GraphLink(sourceNode, destNode,cost, roadType) ); //Add new link object to source adjacency list
        destNode.graphLinkList.add( new GraphLink(destNode, sourceNode, cost, roadType) ); //Add new link object to destination adjacency list
    }
}