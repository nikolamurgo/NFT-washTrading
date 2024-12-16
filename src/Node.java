import java.util.HashMap;

public class Node {
    String address; // hash address of each entity
    public HashMap<String,Edge> edges; // connected nodes

    public Node(String address) {
        this.address = address;
        edges = new HashMap<>(32);
    }

    //getter for node hash address
    public String getAddress() {
        return address;
    }

}
