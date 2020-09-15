package course2.implementations.week2;

import java.util.Stack;
import edu.princeton.cs.algs4.IndexMinPQ;

/**
 * Shortest path tree 
 * Dijkstra's algorithm
 * 
 * 1. Consider vertices in increasing order of distance from s
 * 2. Add vertex to tree and relax all edges pointing from that vertex
 * 
 * For DAG instead of Priority Queue use reversed postorder after dfs (topological order).
 */
public class DijkstraSP {
    private final double[] distTo;
    private final DirectedEdge[] edgeTo;
    private IndexMinPQ<Double> pq;

    /**
     * Shortest path from s in graph G
     */
    public DijkstraSP(WeightedDiGraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<Double>(G.V());

        for (int i = 0; i < G.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            G.adj(v).forEach(x -> relax(x));
        }
    }

    double distTo(int v) {
        return distTo[v];
    }

    Iterable<DirectedEdge> pathTo(int v) {
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return (path);
    }

    private void relax(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        double newDistance = distTo[v] + e.weight();
        if (newDistance < distTo[w]) {
            distTo[w] = newDistance;
            edgeTo[w] = e;

            if (pq.contains(w)) {
                pq.decreaseKey(w, distTo[w]);
            } else {
                pq.insert(w, distTo[w]);
            }
        }
    }

}
