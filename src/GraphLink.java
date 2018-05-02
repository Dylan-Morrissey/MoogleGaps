public class GraphLink {

    private GraphNode sourceNode;
    private GraphNode destNode;
    private int distance;
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

    public int getDistance() {
        return distance;
    }

    private void setDistance(int distance) {
        this.distance = distance;
    }

    public Character getRoadType() {
        return roadType;
    }

    private void setRoadType(Character roadType) {
        this.roadType = roadType;
    }

    public GraphLink(GraphNode sourceNode, GraphNode destNode, int distance, char roadType) {
        this.setSourceNode(sourceNode);
        this.setDestNode(destNode);
        this.setDistance(distance);
        this.setRoadType(roadType);
    }
}