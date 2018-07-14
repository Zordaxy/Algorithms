import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private int moves = -1;
    private Stack<Board> solution;
    private boolean isSolvable;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        solution = new Stack<>();
        MinPQ<Board> queue;

        queue = new MinPQ<>();
        queue.insert(initial);
        Board min = initial;

        Board twinMin = initial.twin();
        MinPQ<Board> twinQueue = new MinPQ<>();
        twinQueue.insert(twinMin);

        while (!twinMin.isGoal() && !min.isGoal()) {
            moves++;
            min = queue.delMin();
            solution.push(min);
            for (Board neighbour : min.neighbors()) {
                queue.insert(neighbour);
            }

            twinMin = twinQueue.delMin();
            for (Board neighbour : twinMin.neighbors()) {
                twinQueue.insert(neighbour);
            }
        }

        this.isSolvable = min.isGoal();
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return this.isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return this.isSolvable ? this.moves : 0;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return this.isSolvable ? solution : null;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        // java -cp C:\localProjects\algs4\lib\algs4.jar;. Solver puzzle05.txt
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
