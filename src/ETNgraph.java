import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class ETNgraph {

    // adjacency list
    private HashMap<String,Node> graph;

    public ETNgraph(){
        this.graph = new HashMap<>();
    }

    public void addNode(Node node){
        if (node == null){
            return; // since we dont know the sender of the transaction dont add it. Probably this cant happen...
        }
        if(graph.containsKey(node.getAddress())){
            return;
        }
        graph.put(node.getAddress(),node);
    }

    public void addEdge(Node node1, Node node2){
        // TODO: do we need to check if both nodes are already in the hashtable?
        // i think not ?? since addNode(sender) in buildGraph already ensures that node1 is added in graph and
        // we dont need the receiver to be added to the graph since there are just senders.

        Edge edge = new Edge(node1, node2);
        node1.edges.add(edge);

    }

    public void buildGraph() throws IOException {

        String filename = "src/linkabilityNetworksData/prog3ETNsample.csv";
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = "";

        long start = System.currentTimeMillis();
        while (br.readLine() != null) {
            line = br.readLine();
            String[] dataOnLine = line.split(",");
            Node sender = new Node(dataOnLine[5]);
            Node receiver = new Node(dataOnLine[6]);
            addNode(sender);
            addEdge(sender, receiver);
        }

        long end = System.currentTimeMillis();

        br.close();
    }

}
