package course2.implementations.week1;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

/**
 * Adjacensy list representation: list of vertices with lists of adjacent vertices in each
 */
public class Digraph {
    private int V;
    private int E;
    private LinkedList<Integer>[] adj;

    Digraph(int v) {
        V = v;
        adj = (LinkedList<Integer>[]) new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
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

    public void print() {
        for (int v = 0; v < this.V(); v++) {
            for (int w : this.adj(v)) {
                StdOut.println(v + "->" + w);
            }
        }
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
        edges.put(4, 2);
        edges.put(2, 3);
        edges.put(3, 2);
        edges.put(6, 0);
        edges.put(0, 1);
        edges.put(2, 0);
        edges.put(11, 12);
        edges.put(12, 9);
        edges.put(9, 10);
        edges.put(9, 11);
        edges.put(7, 9);
        edges.put(10, 12);
        edges.put(11, 4);
        edges.put(4, 3);
        edges.put(3, 5);
        edges.put(6, 8);
        edges.put(8, 6);

        for (Map.Entry<Integer, Integer> entry : edges.entrySet()) {
            g.addEdge(entry.getKey(), entry.getValue());
        }
        return g;
    }
}