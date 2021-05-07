import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] results;
    private static final double FACTOR = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        // Throw an IllegalArgumentException in the constructor if either n ≤ 0 or
        // trials ≤ 0.
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        results = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                percolation.open(row, col);
            }
            results[i] = (double) percolation.numberOfOpenSites() / (n * n);
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);

    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return StdStats.mean(results) - FACTOR * StdStats.stddev(results) / Math.sqrt(results.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return StdStats.mean(results) + FACTOR * StdStats.stddev(results) / Math.sqrt(results.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        StdOut.println("mean\t\t\t = " + percolationStats.mean());
        StdOut.println("stddev\t\t\t = " + percolationStats.stddev());
        StdOut.println("95% confidence interval\t = [" + percolationStats.confidenceLo() + ", "
                + percolationStats.confidenceHi() + "]");
    }

}