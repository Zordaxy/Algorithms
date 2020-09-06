package course2.implementations.week1;

import java.util.*;

/**
 * Adjacensy list representation: list of vertices with lists of adjacent vertices in each
 */
public class Graph {
    private int V;
    private int E;
    private LinkedList<Integer>[] adj;

    Graph(int v) {
        V = v;
        adj = (LinkedList<Integer>[]) new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public static void main(String[] args) {
        // Scanner reader = new Scanner(System.in);
        Graph g = getDafaultGraph();

        for (int v = 0; v < g.V(); v++) {
            for (int w : g.adj(v)) {
                System.out.println(v + "-" + w);
            }
        }
    }

    public static Graph getDafaultGraph() {
        Graph g = new Graph(13);

        Map<Integer, Integer> edges = new HashMap<>();
        edges.put(0, 5);
        edges.put(4, 3);
        edges.put(0, 1);
        edges.put(9, 12);
        edges.put(6, 4);
        edges.put(5, 4);
        edges.put(0, 2);
        edges.put(11, 12);
        edges.put(9, 10);
        edges.put(0, 6);
        edges.put(7, 8);
        edges.put(9, 11);
        edges.put(5, 3);

        for (Map.Entry<Integer, Integer> entry : edges.entrySet()) {
            g.addEdge(entry.getKey(), entry.getValue());
        }
        return g;
    }
}
