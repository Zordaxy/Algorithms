package course2.implementations.week1;

import java.util.Stack;

public class DFS {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public DFS(Graph G, int s) {
        this.s = s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (Integer w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
                edgeTo[w] = v;
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
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args) {
        Graph G = Graph.getDafaultGraph();
        int s = 5;

        DFS paths = new DFS(G, s);
        for (int v = 0; v < G.V(); v++)
            if (paths.hasPathTo(v))
                System.out.println(paths.pathTo(v));
    }
}

