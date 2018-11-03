package algs4.Course2.week1;


import java.util.LinkedList;
import java.util.Stack;

public class BFS {
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
    private int s;

    public BFS(Graph G, int v) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(s);
        marked[s] = true;
        distTo[s] = 0;

        while (!q.isEmpty()) {
            int v = q.poll();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    q.add(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                }
            }
        }
    }

    boolean hasPathTo(int v) {
        return marked[v];
    }

    Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int x = v; distTo[x] != 0; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args) {
        Graph G = Graph.getDafaultGraph();
        int s = 5;

        BFS paths = new BFS(G, s);
        for (int v = 0; v < G.V(); v++)
            if (paths.hasPathTo(v))
                System.out.println(paths.pathTo(v));
    }
}
