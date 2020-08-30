package course2.implementations.week1;

import java.util.Stack;

/**
 * 1. Mark v as visited
 * 2. Recursively visit all unmarked vertices w adjacent to v.
 * 
 * Can be implemented by putting unvisited vertices on a stack
 */
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
}

