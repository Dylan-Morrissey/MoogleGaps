import java.util.ArrayList;
import java.util.List;

public class GraphNode {

    private String name;
    public AdjacencyMatrix mat;
    public int nodeId;
    public List<GraphLink> graphLinkList=new ArrayList<>();
    public List<GraphNode> adjList=new ArrayList<>();
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
        this.name=name;
        /*this.mat=mat;
        mat.nodes[nodeId=mat.nodeCount++]=this;*/
    }
    public static List<List<GraphNode>> findAllPathsDepthFirst(GraphNode from, List<GraphNode> encountered, String lookingfor){
        List<List<GraphNode>> result=null, temp2;
        if(from.name.equals(lookingfor)) { //Found it
            List<GraphNode> temp=new ArrayList<>(); //Create new single solution path list
            temp.add(from); //Add current node to the new single path list
            result=new ArrayList<>(); //Create new "list of lists" to store path permutations
            result.add(temp); //Add the new single path list to the path permutations list
            return result; //Return the path permutations list
        }
        if(encountered==null) encountered=new ArrayList<>(); //First node so create new (empty) encountered list
        encountered.add(from); //Add current node to encountered list
        for(GraphNode adjNode : from.adjList){
            if(!encountered.contains(adjNode)) {
                temp2=findAllPathsDepthFirst(adjNode,new ArrayList<>(encountered),lookingfor); //Use clone of encountered list
//for recursive call!
                if(temp2!=null) { //Result of the recursive call contains one or more paths to the solution node
                    for(List<GraphNode> x : temp2) //For each partial path list returned
                        x.add(0,from); //Add the current node to the front of each path list
                    if(result==null) result=temp2; //If this is the first set of solution paths found use it as the result
                    else result.addAll(temp2); //Otherwise append them to the previously found paths
                }
            }
        }
        return result;
    }

    public void connectToNodeDirected(GraphNode sourceNode, GraphNode destNode, int cost, char roadType) {
        mat.amat[nodeId][destNode.nodeId]=true;
        graphLinkList.add(new GraphLink(sourceNode, destNode, cost, roadType));
    }

    public void connectToNodeUndirected(GraphNode sourceNode, GraphNode destNode, int cost, char roadType) {
        mat.amat[nodeId][destNode.nodeId]=mat.amat[destNode.nodeId][nodeId]=true;
        graphLinkList.add( new GraphLink(sourceNode, destNode,cost, roadType) ); //Add new link object to source adjacency list
        destNode.graphLinkList.add( new GraphLink(destNode, sourceNode, cost, roadType) ); //Add new link object to destination adjacency list
    }

}