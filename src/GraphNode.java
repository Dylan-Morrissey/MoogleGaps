import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphNode {

    private String name;

    public List<GraphLink> graphLinkList=new ArrayList<>();
    //public List<GraphNode> adjList=new ArrayList<>();
    public List<GraphLink> adjList= new ArrayList<>();
    public List<GraphLink> getGraphLinkList() {
        return graphLinkList;
    }
    public int nodeValue=Integer.MAX_VALUE;

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
    }

    public static void traverseGraphDepthFirstShowingTotalCost(GraphNode from, List<GraphNode> encountered, int totalCost ){
        System.out.println(from.name+" (Total cost of reaching node: "+ totalCost +")");
        if(encountered==null) encountered=new ArrayList<>(); //First node so create new (empty) encountered list
        encountered.add(from);
//Could sort adjacency list here based on cost – see next slide for more info!
        for(GraphLink adjLink : from.adjList)
            if(!encountered.contains(adjLink.destNode))
                traverseGraphDepthFirstShowingTotalCost(adjLink.destNode,encountered, totalCost+adjLink.cost );
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
        for(GraphLink adjLink : from.adjList){
            if(!encountered.contains(adjLink)) {
                temp2=findAllPathsDepthFirst(from,new ArrayList<>(encountered),lookingfor); //Use clone of encountered list
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

public static  CostedPath findCheapestPathDijkstra(GraphNode startNode, String lookingfor){
    CostedPath cp=new CostedPath(); //Create result object for cheapest path
    List<GraphNode> encountered=new ArrayList<>(), unencountered=new ArrayList<>(); //Create encountered/unencountered lists
    startNode.nodeValue=0; //Set the starting node value to zero
    unencountered.add(startNode); //Add the start node as the only value in the unencountered list to start
    GraphNode currentNode;
    do{ //Loop until unencountered list is empty
        currentNode=unencountered.remove(0); //Get the first unencountered node (sorted list, so will have lowest value)
        encountered.add(currentNode); //Record current node in encountered list
        if(currentNode.name.equals(lookingfor)){ //Found goal - assemble path list back to start and return it
            cp.pathList.add(currentNode); //Add the current (goal) node to the result list (only element)
            cp.pathCost=currentNode.nodeValue; //The total cheapest path cost is the node value of the current/goal node
            while(currentNode!=startNode) { //While we're not back to the start node...
                boolean foundPrevPathNode=false; //Use a flag to identify when the previous path node is identified
                for(GraphNode n : encountered) { //For each node in the encountered list...
                    for(GraphLink e : n.adjList) //For each edge from that node...
                        if(e.destNode==currentNode && currentNode.nodeValue-e.cost==n.nodeValue){ //If that edge links to the
//current node and the difference in node values is the cost of the edge -> found path node!
                            cp.pathList.add(0,n); //Add the identified path node to the front of the result list
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
            return cp; //The costed (cheapest) path has been assembled, so return it!
        }
//We're not at the goal node yet, so...
        for(GraphLink e : currentNode.adjList) //For each edge/link from the current node...
            if(!encountered.contains(e.destNode)) { //If the node it leads to has not yet been encountered (i.e. processed)
                e.destNode.nodeValue=Integer.min(e.destNode.nodeValue, currentNode.nodeValue+e.cost); //Update the node value at the end
                //of the edge to the minimum of its current value or the total of the current node's value plus the cost of the edge
                unencountered.add(e.destNode);
            }
        Collections.sort(unencountered,(n1,n2)->n1.nodeValue-n2.nodeValue); //Sort in ascending node value order
    }while(!unencountered.isEmpty());
    return null; //No path found, so return null
}

    public void connectToNodeUndirected(GraphNode sourceNode, GraphNode destNode, int cost, char roadType) {
        adjList.add(new GraphLink(sourceNode, destNode,cost, roadType)); //Add new link object to source adjacency list
        destNode.adjList.add( new GraphLink(this, destNode,cost, roadType) ); //Add new link object to destination adjacency list
    }

}