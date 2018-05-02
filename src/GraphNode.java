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

                                cp.pathList.add(0, n);

                                roadTypes.add(e.getRoadType().toString());
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
                return;
            }

            for(GraphLink e : currentNode.graphLinkList)
                if(!encountered.contains(e.getDestNode())) {
                    e.getDestNode().nodeValue=Integer.min(e.getDestNode().nodeValue, currentNode.nodeValue+e.getDistance());

                    unencountered.add(e.getDestNode());
                }
            unencountered.sort((n1, n2) -> n1.nodeValue - n2.nodeValue);
        }while(!unencountered.isEmpty());
    }

    public void connectToNodeUndirected(GraphNode sourceNode, GraphNode destNode, int cost, char roadType) {
        graphLinkList.add(new GraphLink(sourceNode, destNode, cost, roadType));
        destNode.graphLinkList.add( new GraphLink(destNode, sourceNode, cost, roadType) ); //Add new link object to destination adjacency list
    }

}