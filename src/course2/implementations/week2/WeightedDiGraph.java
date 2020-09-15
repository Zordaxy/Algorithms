package course2.implementations.week2;

import java.util.LinkedList;

public class WeightedDiGraph {
    private final LinkedList<DirectedEdge>[] adj;
    private final int V;

    public WeightedDiGraph(int V) {
        this.V = V;
        this.adj = (LinkedList<DirectedEdge>[]) new LinkedList[V];
        for (int i = 0; i < this.V; i++) {
            this.adj[i] = new LinkedList<DirectedEdge>();
        }
    }

    public void addEdge(DirectedEdge e) {
        int v = e.from();
        adj[v].add(e);
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public int V() {
        return this.V;
    }
}
