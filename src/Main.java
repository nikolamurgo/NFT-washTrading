import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ETNgraph etngraph = new ETNgraph();

        etngraph.loadBlacklist();
        etngraph.buildGraph();

    }
}