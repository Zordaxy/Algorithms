package course2.implementations.week2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Week2Test {
    @Test
    public void MSTTest() {
        WeightedGraph G = generateWeightedGraph();

        PrimMST mst = new PrimMST(G);
        int[][] expected = { { 0, 7 }, { 1, 7 }, { 0, 2 }, { 2, 3 }, { 5, 7 }, { 4, 5 }, { 6, 2 } };
        int index = 0;
        for (Edge e : mst.edges()) {
            int v = e.either();
            int w = e.other(v);
            int[] expectedEntry = expected[index++];
            assertEquals(expectedEntry[0], v);
            assertEquals(expectedEntry[1], w);
        }
        assertEquals(7, index);
    }

    private WeightedGraph generateWeightedGraph() {
        WeightedGraph G = new WeightedGraph(8);
        G.addEdge(new Edge(4, 5, 0.35));
        G.addEdge(new Edge(4, 7, 0.37));
        G.addEdge(new Edge(5, 7, 0.28));
        G.addEdge(new Edge(0, 7, 0.16));
        G.addEdge(new Edge(1, 5, 0.32));
        G.addEdge(new Edge(0, 4, 0.38));
        G.addEdge(new Edge(2, 3, 0.17));
        G.addEdge(new Edge(1, 7, 0.19));
        G.addEdge(new Edge(0, 2, 0.26));
        G.addEdge(new Edge(1, 2, 0.36));
        G.addEdge(new Edge(1, 3, 0.29));
        G.addEdge(new Edge(2, 7, 0.34));
        G.addEdge(new Edge(6, 2, 0.40));
        G.addEdge(new Edge(3, 6, 0.52));
        G.addEdge(new Edge(6, 0, 0.58));
        G.addEdge(new Edge(6, 4, 0.93));
        return G;
    }
}
