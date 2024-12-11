import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ETNgraph {

    // adjacency list
    private ArrayList<Node> graph;

    public ETNgraph(){
        this.graph = new ArrayList<>();
    }

    public void addNode(Node node){
        for(int i = 0; i< graph.size(); i++){
            if(graph.get(i).getAddress().equals(node.address)){
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
    public void buildGraph() throws IOException {

        String line = "";
        BufferedReader br = new BufferedReader(new FileReader("src/linkabilityNetworksData/prog3ETNsample.csv"));

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
        System.out.println(end - start);


        br.close();
    }

}
