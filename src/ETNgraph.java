import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class ETNgraph {

    // adjacency list
    private HashMap<String,Node> graph;

    public ETNgraph(){
        this.graph = new HashMap<>(16 );
    }

    public boolean addNode(String address){

        if(graph.containsKey(address)){
            return false;
        }else {
            Node node = new Node(address);
            graph.put(address, node);
            return true;
        }
    }

    public void addEdge(String sender, String receiver){

        if(sender.equals(receiver)){ // prevent self loops
            return;
        }
        Node receiverNode = new Node(receiver);
        Node senderNode = new Node(sender);
        Edge edge = new Edge(senderNode, receiverNode);

        if(!senderNode.edges.containsKey(receiver)){ // prevent multiple edges
            senderNode.edges.put(receiver, edge);
        }

    }

    public void buildGraph() throws IOException {

        String filename = "src/linkabilityNetworksData/prog3ETNsample.csv";
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        long start = System.currentTimeMillis();
        String[] dataOnLine;

        while ((line = br.readLine()) != null) {
            dataOnLine = line.split(",");
            String sender = dataOnLine[5];
            String receiver = dataOnLine[6];
            if(addNode(sender)){
                addEdge(sender, receiver);
            }

        }

        long end = System.currentTimeMillis();
        System.out.println("Time needed: "+(end-start));
        br.close();
    }

}
