import java.util.ArrayList;
import java.util.List;

public class GraphNode {

    private static CostedPath cp = new CostedPath();

    private static ArrayList<String> roadTypes = new ArrayList<>();

    private String name;

    public List<GraphLink> graphLinkList=new ArrayList<>();
    public List<GraphLink> getGraphLinkList() {
        return graphLinkList;
    }
    public int nodeValue=Integer.MAX_VALUE;

    private void setGraphLinkList(List<GraphLink> graphLinkList) {
        this.graphLinkList = graphLinkList;
    }

    public static CostedPath getCp() {
        return cp;
    }

    public static void setCp(CostedPath cp) {
        GraphNode.cp = cp;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public GraphNode(String name) {
        this.name=name;
    }

    public static ArrayList<String> getRoadTypes() {
        return roadTypes;
    }

    public static void setRoadTypes(ArrayList<String> roadTypes) {
        GraphNode.roadTypes = roadTypes;
    }


    public static void findShortestPathDijkstra(GraphNode startNode, String lookingfor){
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
//current node and the difference in node values is the cost of the edge -> found path node!
                                cp.pathList.add(0, n); //Add the identified path node to the front of the result list

                                roadTypes.add(e.getRoadType().toString());
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
                return; //The costed (cheapest) path has been assembled, so return it!
            }
//We're not at the goal node yet, so...
            for(GraphLink e : currentNode.graphLinkList) //For each edge/link from the current node...
                if(!encountered.contains(e.getDestNode())) { //If the node it leads to has not yet been encountered (i.e. processed)
                    e.getDestNode().nodeValue=Integer.min(e.getDestNode().nodeValue, currentNode.nodeValue+e.getDistance()); //Update the node value at the end
                    //of the edge to the minimum of its current value or the total of the current node's value plus the cost of the edge
                    unencountered.add(e.getDestNode());
                }
            unencountered.sort((n1, n2) -> n1.nodeValue - n2.nodeValue); //Sort in ascending node value order
        }while(!unencountered.isEmpty());
    }

    public void connectToNodeUndirected(GraphNode sourceNode, GraphNode destNode, int cost, char roadType) {
        graphLinkList.add(new GraphLink(sourceNode, destNode, cost, roadType)); //Add new link object to source adjacency list
        destNode.graphLinkList.add( new GraphLink(destNode, sourceNode, cost, roadType) ); //Add new link object to destination adjacency list
    }

}