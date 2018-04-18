public class GraphLink {

    private GraphNode sourceNode;
    private GraphNode destNode;
    private int cost;
    private char roadType;

    public GraphNode getSourceNode() {
        return sourceNode;
    }

    private void setSourceNode(GraphNode sourceNode) {
        this.sourceNode = sourceNode;
    }

    public GraphNode getDestNode() {
        return destNode;
    }

    private void setDestNode(GraphNode destNode) {
        this.destNode = destNode;
    }

    public int getCost() {
        return cost;
    }

    private void setCost(int cost) {
        this.cost = cost;
    }

    public Character getRoadType() {
        return roadType;
    }

    private void setRoadType(Character roadType) {
        this.roadType = roadType;
    }

    public GraphLink(GraphNode sourceNode, GraphNode destNode, int cost, char roadType) {
        this.setSourceNode(sourceNode);
        this.setDestNode(destNode);
        this.setCost(cost);
        this.setRoadType(roadType);
    }
}