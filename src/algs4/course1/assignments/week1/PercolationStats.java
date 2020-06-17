package course1.assignments.week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final int size;
    private final int sideSize;
    private final double[] results;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Exception!");
        }
        size = n * n;
        sideSize = n;
        results = new double[trials];

        for (int i = 0; i < trials; i++) {
            results[i] = runExperiment();
        }
    }

    private double runExperiment() {
        Percolation percolation = new Percolation(sideSize);
        while (!percolation.percolates()) {
            int x = StdRandom.uniform(sideSize) + 1;
            int y = StdRandom.uniform(sideSize) + 1;

            if (!percolation.isOpen(x, y)) {
                percolation.open(x, y);
            }
        }
        double result = (double) percolation.numberOfOpenSites() / size;
        return result;
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLo() {
        return StdStats.mean(results) - ((CONFIDENCE_95 * StdStats.stddev(results)) / Math.sqrt(results.length));
    }

    public double confidenceHi() {
        return StdStats.mean(results) + ((CONFIDENCE_95 * StdStats.stddev(results)) / Math.sqrt(results.length));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, trials);
        System.out.println("% java-algs4 PercolationStats " + n + " " + trials);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}
