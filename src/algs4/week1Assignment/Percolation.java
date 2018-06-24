package algs4.week1Assignment;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private boolean[] isOpened;
    private final int count;
    private final int sideSize;
    private int numberOfOpenSites;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("index " + n + " should be more than 0");
        }
        sideSize = n;
        count = n * n;
        isOpened = new boolean[count + 2];
        isOpened[count] = true;
        isOpened[count + 1] = true;
        weightedQuickUnionUF = new WeightedQuickUnionUF(count + 2);
    }

    public void open(int row, int col) {
        int element = xyTo1D(row, col);
        isOpened[element] = true;
        numberOfOpenSites++;
        if (row > 1) {
            int top = xyTo1D(row - 1, col);
            if (isOpened[top]) {
                weightedQuickUnionUF.union(element, top);
            }
        } else {
            weightedQuickUnionUF.union(element, count);
        }
        if (row < sideSize) {
            int bottom = xyTo1D(row + 1, col);
            if (isOpened[bottom]) {
                weightedQuickUnionUF.union(element, bottom);
            }
        } else {
            weightedQuickUnionUF.union(element, count + 1);
        }
        if (col > 1) {
            int left = xyTo1D(row, col - 1);
            if (isOpened[left]) {
                weightedQuickUnionUF.union(element, left);
            }
        }
        if (col < sideSize) {
            int right = xyTo1D(row, col + 1);
            if (isOpened[right]) {
                weightedQuickUnionUF.union(element, right);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        int element = xyTo1D(row, col);
        return isOpened[element];
    }

    public boolean isFull(int row, int col) {
        int element = xyTo1D(row, col);
        return weightedQuickUnionUF.connected(element, count);
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        return weightedQuickUnionUF.connected(count, count + 1);
    }

    private void validate(int p) {
        if (p >= count + 2 || p < 0) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (count - 1));
        }
    }

    private int xyTo1D(int x, int y) {
        int result = (y - 1) * sideSize + (x - 1);
        validate(result);
        return result;
    }

    public static void main(String[] args) {
        // Empty body
    }
}
