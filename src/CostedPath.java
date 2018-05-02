import java.util.ArrayList;
import java.util.List;

public class CostedPath {

    private static List<CostedPath> allPaths = new ArrayList<>();
    public int pathCost = 0;
    private int pathTime = 0;
    public List<GraphNode> pathList = new ArrayList<>();
}