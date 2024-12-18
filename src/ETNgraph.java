import util.LogLevel;
import util.Logger;

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

    // TODO: reimplement, maybe works, test, long run time...
    public boolean removeCEX(String transaction) throws IOException {
        String filename = "src/linkabilityNetworksData/blacklist/cex.json";
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        while((line = br.readLine()) != null){
            // TODO: remove the address from graph
            String[] dataOnLine = line.split(",");
            if(dataOnLine[0].equals(transaction)){
                return true;
            }
        }
        br.close();
        return false;
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
            String transaction = dataOnLine[0];
            // TODO: improve... infinity run, why? find other way to remove CEX
            if(!removeCEX(transaction)){
                if(addNode(sender)){
                    addEdge(sender, receiver);
                }
            }

        }

        long end = System.currentTimeMillis();
        Logger.log("Time needed for init graph: "+(end-start), LogLevel.Success);
        Logger.log("Initial graph size: "+graph.size());
        br.close();
    }

}
