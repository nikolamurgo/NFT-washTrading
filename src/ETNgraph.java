import java.util.ArrayList;

public class ETNgraph {

    // adjacency list
    private ArrayList<Node> graph;

    public ETNgraph(){
        this.graph = new ArrayList<>();
    }

    public void addNode(Node node){
        // TODO: check if node already exists in graph, if not then add it to the graph
        graph.add(node);
    }
    public void addEdge(Node node1, Node node2){
        Edge edge = new Edge(node1, node2);
        node1.edges.add(edge);
    }

    //implement a BuildGraph method here later:
}
