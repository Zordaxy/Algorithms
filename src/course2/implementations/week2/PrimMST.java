package course2.implementations.week2;

import java.util.LinkedList;

import edu.princeton.cs.algs4.MinPQ;

public class PrimMST {
    private LinkedList<Edge> mst;
    private MinPQ<Edge> minPq;
    private boolean[] marked;

    public PrimMST(WeightedGraph G) {
        mst = new LinkedList<Edge>();
        minPq = new MinPQ<Edge>();
        marked = new boolean[G.V()];
        visit(G, 0);

        while(!minPq.isEmpty()) {
            Edge e = minPq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w]) {
                continue;
            }
            mst.add(e);
            if (marked[v]) {
                visit(G, w);
            } else {
                visit(G, v);
            }
        }
    }

    private void visit(WeightedGraph G, int v) {
        marked[v] = true;
        G.adj(v).forEach(e -> {
            int that = e.other(v);
            if (!marked[that]) {
                minPq.insert(e);
            }
        });
    }

    public Iterable<Edge> edges() {
        return mst;
    }
}
