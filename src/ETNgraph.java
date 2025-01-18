import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;


public class ETNgraph {

    // adjacency list
    public HashMap<String,Node> graph;
    private HashSet<String> blacklistedAddresses;
    private HashSet<String> baycAddresses;

    public ETNgraph(){
        this.graph = new HashMap<>();
        this.blacklistedAddresses = new HashSet<>();
        this.baycAddresses = new HashSet<>();
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
        Node senderNode = graph.get(sender);

        if(!senderNode.edges.containsKey(receiver)){ // prevent multiple edges
            Edge edge = new Edge(sender, receiver);
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

    public void loadBAYCAddresses() throws IOException {
        String baycFile = "src/linkabilityNetworksData/boredapeyachtclub.csv";
        BufferedReader br = new BufferedReader(new FileReader(baycFile));
        String line;

        while ((line = br.readLine()) != null) {
            String[] dataOnLine = line.split(",");
            String fromAddress = dataOnLine[4];
            String toAddress = dataOnLine[5];

            // Add both addresses to the BAYC address set
            baycAddresses.add(fromAddress);
            baycAddresses.add(toAddress);
        }
        br.close();
    }

    public boolean isBAYCAddress(String address) {
        return baycAddresses.contains(address);
    }

    public void buildGraph() throws IOException {

        String fileSample = "src/linkabilityNetworksData/prog3ETNsample.csv";
        BufferedReader br = new BufferedReader(new FileReader(fileSample));
        String line;

        String[] dataOnLine;

        while ((line = br.readLine()) != null) {
            dataOnLine = line.split(",");
            String sender = dataOnLine[5];
            String receiver = dataOnLine[6];
            if ((!isBlacklisted(sender) && !isBlacklisted(receiver)) && (isBAYCAddress(sender) || isBAYCAddress(receiver) )) {
                addNode(sender);
                addNode(receiver);
                addEdge(sender, receiver);
            }
        }
        br.close();
    }

}
