package course2.implementations.week1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class week1Test {
    @Test
    public void DFSTest() {
        Graph G = Graph.getDafaultGraph();
        int s = 5;
        int destination = 0;

        DFS paths = new DFS(G, s);
        assertTrue(paths.hasPathTo(destination));

        Iterable<Integer> path = paths.pathTo(destination);
        String expected = "0, 6, 4, 3, 5";
        String result = "";
        for (int node : path) {
            result += result == "" ? node : ", " + node;
        }

        assertEquals(expected, result);
    }

    @Test
    public void BFSTest() {
        Graph G = Graph.getDafaultGraph();
        int s = 4;
        int destination = 6;

        BFS paths = new BFS(G, s);
        assertTrue(paths.hasPathTo(destination));

        Iterable<Integer> path = paths.pathTo(destination);
        String expected = "6, 0";
        String result = "";
        for (int node : path) {
            result += result == "" ? node : ", " + node;
        }

        assertEquals(expected, result);
    }
}