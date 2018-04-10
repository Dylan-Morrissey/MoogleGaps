public class GraphLink {

    private GraphNode destNode; //Could also store source node if required
    private int cost; //Other link attributes could be similarly stored

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

    public GraphLink(GraphNode destNode, int cost) {
        this.setDestNode(destNode);
        this.setCost(cost);
    }
}