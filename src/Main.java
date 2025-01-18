import util.LogLevel;
import util.Logger;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ETNgraph etngraph = new ETNgraph();

        long start = System.currentTimeMillis();

        etngraph.loadBlacklist();
        etngraph.loadBAYCAddresses();
        etngraph.buildGraph();

        long end = System.currentTimeMillis();
        Logger.log("Time needed: "+(end-start), LogLevel.Success);
        Logger.log("Initial graph size: "+etngraph.graph.size());

    }
}