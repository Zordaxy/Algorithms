import edu.princeton.cs.algs4.Stack;

public class Board implements Comparable<Board> {
    private int[][] blocks;
    private int dimension;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        this.blocks = blocks;
        this.dimension = blocks.length > blocks[0].length ? blocks.length : blocks[0].length;
    }

    // board dimension n
    public int dimension() {
        return this.dimension;
    }

    // number of blocks out of place
    public int hamming() {
        int summ = 0;
        for (int i = 0; i < this.blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                int el = this.blocks[i][j];
                if (el != 0) {
                    el--;
                    int verticalDiff = Math.abs((el / this.dimension) - i);
                    int horizontallDiff = Math.abs((el % this.dimension) - j);
                    if (verticalDiff > 0 || horizontallDiff > 0) {
                        summ++;
                    }
                }
            }
        }

        return summ;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < this.blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                int el = this.blocks[i][j];
                if (el != 0) {
                    el--;
                    sum += Math.abs((el / this.dimension) - i);
                    sum += Math.abs((el % this.dimension) - j);
                }
            }
        }
        return sum;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        if (this.dimension == 1) {
            return this;
        } else {
            int[][] twinArray = clonedArray();
            if (twinArray[0][0] != 0 && twinArray[0][1] != 0) {
                swap(twinArray, 0, 0, 0, 1);
            } else {
                swap(twinArray, 1, 0, 1, 1);
            }
            return new Board(twinArray);
        }
    }

    private int[][] clonedArray() {
        int[][] twinArray = new int[dimension][dimension];
        for (int i = 0; i < twinArray.length; i++) {
            for (int j = 0; j < twinArray[i].length; j++) {
                twinArray[i][j] = blocks[i][j];
            }
        }
        return twinArray;
    }

    private void swap(int[][] array, int y1, int x1, int y2, int x2) {
        int aux = array[y1][x1];
        array[y1][x1] = array[y2][x2];
        array[y2][x2] = aux;
    }

    // does this board equal y?
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;

        Board that = (Board) other;
        return this.toString().equals(that.toString());
    }

    public int compareTo(Board that) {
        if (this.manhattan() < that.manhattan()) {
            return -1;
        } else if (this.manhattan() > that.manhattan()) {
            return +1;
        } else {
            return 0;
        }
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> neighbors = new Stack<>();

        int[] emptyBlock = new int[2];
        outerLoop:
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (blocks[i][j] == 0) {
                    emptyBlock[0] = i;
                    emptyBlock[1] = j;
                    break outerLoop;
                }
            }
        }

        int[][] neighborsArray;
        if (emptyBlock[0] > 0) {
            neighborsArray = clonedArray();
            swap(neighborsArray, emptyBlock[0] - 1, emptyBlock[1], emptyBlock[0], emptyBlock[1]);
            neighbors.push(new Board(neighborsArray));
        }
        if (emptyBlock[1] > 0) {
            neighborsArray = clonedArray();
            swap(neighborsArray, emptyBlock[0], emptyBlock[1] - 1, emptyBlock[0], emptyBlock[1]);
            neighbors.push(new Board(neighborsArray));
        }
        if (emptyBlock[0] < dimension - 1) {
            neighborsArray = clonedArray();
            swap(neighborsArray, emptyBlock[0] + 1, emptyBlock[1], emptyBlock[0], emptyBlock[1]);
            neighbors.push(new Board(neighborsArray));
        }
        if (emptyBlock[1] < dimension - 1) {
            neighborsArray = clonedArray();
            swap(neighborsArray, emptyBlock[0], emptyBlock[1] + 1, emptyBlock[0], emptyBlock[1]);
            neighbors.push(new Board(neighborsArray));
        }
        return neighbors;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dimension + "\n");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {

    }
}
