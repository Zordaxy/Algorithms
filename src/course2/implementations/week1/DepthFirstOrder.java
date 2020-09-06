package course2.implementations.week1;

import java.util.Stack;

/**
 * Topological sort is dfs reverse post order.
 */
public class DepthFirstOrder {
    private boolean[] marked;
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph g) {
        reversePost = new Stack<Integer>();
        marked = new boolean[g.V()];
        for (int i = 0; i < g.V(); i++) {
            if (!marked[i]) {
                dfs(g, i);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (Integer w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
            reversePost.push(v);
        }
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
