import java.util.ArrayList;

public class Node {
    String address; // hash address of each entity
    public ArrayList<Edge> edges; // connected nodes

    public Node(String address) {
        this.address = address;
        edges = new ArrayList<>();
    }

    //getter for node hash address
    public String getAddress() {
        return address;
    }

}
