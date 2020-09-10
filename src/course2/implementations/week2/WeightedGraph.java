package course2.implementations.week2;

import java.util.LinkedList;

public class WeightedGraph {
    private final LinkedList<Edge>[] adj;
    private final int V;

    public WeightedGraph(int V) {
        this.V = V;
        this.adj = (LinkedList<Edge>[]) new LinkedList[V];
        for (int i = 0; i < this.V; i++) {
            this.adj[i] = new LinkedList<Edge>();
        }
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public int V() {
        return this.V;
    }
}
