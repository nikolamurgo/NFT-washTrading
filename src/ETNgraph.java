import java.util.ArrayList;

public class ETNgraph {

    // adjacency list
    private ArrayList<Node> graph;

    public ETNgraph(){
        this.graph = new ArrayList<>();
    }

    public void addNode(Node node){
        for(int i = 0; i< graph.size(); i++){
            if(graph.get(i).address.equals(node.address)){
                return;
            }
        }
        graph.add(node);
    }

    public void addEdge(Node node1, Node node2){
        Edge edge = new Edge(node1, node2);
        node1.edges.add(edge);
    }

    //implement a BuildGraph method here later:
    public void buildGraph(ETNgraph g){
        // TODO: implement buildGraph method
    }

    //print method used for testing
    public void printNumberOfSends(){
     for(int i = 0; i< graph.size(); i++){
         System.out.println(graph.get(i).address+" : "+graph.get(i).edges.size());
     }
    }
}
