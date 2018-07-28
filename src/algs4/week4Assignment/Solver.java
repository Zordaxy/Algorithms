package algs4.week4Assignment;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Comparator;

public class Solver {
    private final boolean isSolvable;
    private final SearchNode current;

    private class SearchNode {
        Board board;
        int moves;
        SearchNode predecessor;
        int manhattan;
        boolean isGoal;

        SearchNode(Board board, int moves, SearchNode predecessor) {
            this.board = board;
            this.moves = moves;
            this.predecessor = predecessor;
            this.manhattan = board.manhattan();
            this.isGoal = board.isGoal();
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new java.lang.IllegalArgumentException();
        }

        MinPQ<SearchNode> queue, queue2;
        queue = new MinPQ<>(1, new BoardComparator());
        queue.insert(new SearchNode(initial, 0, null));

        queue2 = new MinPQ<>(1, new BoardComparator());
        queue2.insert(new SearchNode(initial.twin(), 0, null));

        SearchNode node, node2;
        do {
            node = queue.delMin();
            for (Board neighbour : node.board.neighbors()) {
                if (node.predecessor == null || !neighbour.equals(node.predecessor.board)) {
                    queue.insert(new SearchNode(neighbour, node.moves + 1, node));
                }
            }

            node2 = queue2.delMin();
            for (Board neighbour : node2.board.neighbors()) {
                if (node2.predecessor == null || !neighbour.equals(node2.predecessor.board)) {
                    queue2.insert(new SearchNode(neighbour, node2.moves + 1, node2));
                }
            }
        } while (!node.isGoal && !node2.isGoal);

        this.isSolvable = node.isGoal;
        this.current = node;
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return this.isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return this.isSolvable ? this.current.moves : -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!this.isSolvable) {
            return null;
        }

        Stack<Board> solution = new Stack<>();
        SearchNode node = this.current;
        while (node != null) {
            solution.push(node.board);
            node = node.predecessor;
        }
        return solution;
    }

    private class BoardComparator implements Comparator<SearchNode> {
        public int compare(SearchNode a, SearchNode b) {
            return (a.manhattan + a.moves) - (b.manhattan + b.moves);
//            if (a.manhattan < b.manhattan) {
//                return -1;
//            } else if (a.manhattan > b.manhattan) {
//                return +1;
//            } else {
//                return 0;
//            }
        }
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        // java -cp C:\localProjects\algs4\lib\algs4.jar;. Solver puzzle05.txt
        // create initial board from file

//        In in = new In(args[0]);
//        int n = in.readInt();
//        int[][] blocks = new int[n][n];
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < n; j++)
//                blocks[i][j] = in.readInt();
//        Board initial = new Board(blocks);
//        // solve the puzzle
//        Solver solver = new Solver(initial);
//        // print solution to standard output
//        if (!solver.isSolvable())
//            StdOut.println("No solution possible");
//        else {
//            StdOut.println("Minimum number of moves = " + solver.moves());
//            for (Board board : solver.solution())
//                StdOut.println(board);
//        }
    }
}
