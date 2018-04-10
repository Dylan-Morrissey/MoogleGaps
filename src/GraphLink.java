public class GraphLink {

    private GraphNode sourceNode;
    private GraphNode destNode; //Could also store source node if required
    private int cost; //Other link attributes could be similarly stored

    public GraphNode getSourceNode() {
        return sourceNode;
    }

    public void setSourceNode(GraphNode sourceNode) {
        this.sourceNode = sourceNode;
    }

    public GraphNode getDestNode() {
        return destNode;
    }

    public void setDestNode(GraphNode destNode) {
        this.destNode = destNode;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public GraphLink(GraphNode sourceNode, GraphNode destNode, int cost) {
        this.setSourceNode(sourceNode);
        this.setDestNode(destNode);
        this.setCost(cost);
    }
}