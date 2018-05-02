import java.util.ArrayList;
import java.util.List;

//New class to hold a CostedPath object i.e. a list of GraphNode objects and a total cost attribute
public class CostedPath {

    private static List<CostedPath> allPaths = new ArrayList<>(); //Collection for all candidate costed paths from this node
    public int pathCost = 0;
    private int pathTime = 0;
    public List<GraphNode> pathList = new ArrayList<>();
}