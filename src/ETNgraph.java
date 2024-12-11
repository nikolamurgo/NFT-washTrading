import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class ETNgraph {


    // TODO:  implement adjacencyList with Hashing
    //  instead of ArrayList to achieve average time complexity of O(1)

    // adjacency list
    private HashMap<String,Node> graph;

    public ETNgraph(){
        this.graph = new HashMap<>();
    }

    public void addNode(Node node){
        if(graph.containsKey(node.getAddress())){
            return;
        }
        graph.put(node.getAddress(),node);
    }

    public void addEdge(Node node1, Node node2){
        Edge edge = new Edge(node1, node2);
        node1.edges.add(edge);
    }

    public void buildGraph() throws IOException {

        String filename = "src/linkabilityNetworksData/prog3ETNsample.csv";
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = "";

        long start = System.currentTimeMillis();
        int counter=0;
        while (br.readLine() != null) {
            counter++;
            line = br.readLine();
            String[] dataOnLine = line.split(",");
            Node sender = new Node(dataOnLine[5]);
            Node receiver = new Node(dataOnLine[6]);
            addNode(sender);
            addEdge(sender, receiver);
        }

        long end = System.currentTimeMillis();
        System.out.println("Total number of transactions in ETN: " + counter);
        System.out.println("Number of unique senders: " + graph.size());
        System.out.println("Time: "+(end - start)+"ms");

        br.close();
    }

}
