public class Edge {

    Node sender;
    Node receiver;

    public Edge(Node sender, Node receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    // getters
    public Node getSender() {
        return sender;
    }
    public Node getReceiver() {
        return receiver;
    }


}
