import util.LogLevel;
import util.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;


public class ETNgraph {

    // adjacency list
    private HashMap<String,Node> graph;
    private HashSet<String> blacklistedAddresses;

    public ETNgraph(){
        this.graph = new HashMap<>(16);
        this.blacklistedAddresses = new HashSet<>();
    }

    public void addNode(String address){

        if(!graph.containsKey(address)){
            Node node = new Node(address);
            graph.put(address, node);
        }
    }

    public void addEdge(String sender, String receiver){

        if(sender.equals(receiver)){ // prevent self loops
            return;
        }
        Node receiverNode = graph.get(receiver);
        Node senderNode = graph.get(sender);
        Edge edge = new Edge(senderNode, receiverNode);

        if(!senderNode.edges.containsKey(receiver)){ // prevent multiple edges
            senderNode.edges.put(receiver, edge);
        }

    }

    public void loadBlacklist() throws IOException {
        File folder = new File("src/linkabilityNetworksData/blacklist");
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.startsWith("[") || line.startsWith("]")) {
                        continue;
                    }

                    line = line.replace("\"", "").replace(",", "").trim();
                    if (!line.isEmpty()) {
                        blacklistedAddresses.add(line);
                    }
                }
                br.close();

            }
        }
    }

    public boolean isBlacklisted(String address) {
        return blacklistedAddresses.contains(address);
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

            if (!isBlacklisted(sender) && !isBlacklisted(receiver)) {
                addNode(sender);
                addNode(receiver);
                addEdge(sender, receiver);
            }

        }

        long end = System.currentTimeMillis();
        Logger.log("Time needed for init graph: "+(end-start), LogLevel.Success);
        Logger.log("Initial graph size: "+graph.size());
        br.close();
    }

}
