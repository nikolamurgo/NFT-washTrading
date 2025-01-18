import java.util.HashMap;

public class Node {
    String address; // hash address of each entity
    public HashMap<String,Edge> edges; // connected nodes

    public Node(String address) {
        this.address = address;
        this.edges = new HashMap<>();
    }
}
