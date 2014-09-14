package sedgewick.connectivity;

/**
 *
 */
public class PercolationStats {
    private double[] results;

    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();
        results = new double[T];

        for (int i = 0; i < T; i++) {
            Percolation p = new Percolation(N);
            int count = 0;
            while (!p.percolates()) {
                int rdX = 1 + (int) (Math.random() * N);
                int rdY = 1 + (int) (Math.random() * N);
                if (!p.isOpen(rdX, rdY)) count++;
                p.open(rdX, rdY);
            }
            results[i] = ((1.0 * count) / N / N);
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

    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(results.length);
    }

    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(results.length);
    }

    // test client, described below
    public static void main(String[] args) {
        PercolationStats p = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        //PercolationStats p = new PercolationStats(64, 150);
        System.out.println(p.mean());
        System.out.println(p.confidenceLo());
        System.out.println(p.confidenceHi());
    }



}
