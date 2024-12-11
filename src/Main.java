public class Main {
    public static void main(String[] args) {

        ETNgraph etngraph = new ETNgraph();

        // testing --------------------------------
        Node v1 = new Node("0x12312312");
        Node v2 = new Node("0xjbjbv4b32j");
        Node v3 = new Node("0xgrttgtg222j");
        Node v4 = new Node("0xgg425665tg22j");
        Node v5 = new Node("0xgg425665tg22j");
        Node v6 = new Node("0x12312312");


        etngraph.addNode(v1);
        etngraph.addNode(v2);
        etngraph.addNode(v3);
        etngraph.addNode(v4);
        etngraph.addNode(v5);

        etngraph.addEdge(v1,v2);
        etngraph.addEdge(v3,v1);
        etngraph.addEdge(v1,v5);
        etngraph.addEdge(v5,v3);
        etngraph.addEdge(v4,v1);

        // ------------------------------------------
        etngraph.printNumberOfSends();
    }
}