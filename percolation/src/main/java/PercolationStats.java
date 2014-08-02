/**
 * Jerry Kiely
 * jerry@cowboysmall.com
 * Monday June 16th
 *
 * This class allows you to estimate the percolation threshold by conducting
 * repeated experiments. The steps are as follows
 *
 * 1 - initialize a Percolation instance with all sites blocked
 * 2 - choose a site at random and open it
 * 3 - at percolation record the ratio of the open sites to the total
 * 4 - repeat T times
 *
 * the class can be invoked from the command line as follows:
 *
 *     java -cp classpath PercolationStats N T
 *
 * where
 *
 *     N is the length of the N-by-N grid and
 *     T is the number of independent experiments
 */
public class PercolationStats {

    // this variable stores the outcomes of the simulations
    private double[] tries;

    /**
     * perform T independent computational experiments on an N-by-N grid
     */
    public PercolationStats(int N, int T) {

        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException();

        tries = new double[T];
        int total = N * N;

        for (int i = 0; i < T; i++) {

            Percolation percolation = new Percolation(N);
            int count = 0;
            while (!percolation.percolates()) {

                int row = StdRandom.uniform(N) + 1;
                int col = StdRandom.uniform(N) + 1;
                if (!percolation.isOpen(row, col)) {

                    percolation.open(row, col);
                    count++;
                }
            }
            tries[i] = ((double) count) / total;
        }
    }

    /**
     * sample mean of percolation threshold
     */
    public double mean() {

        return StdStats.mean(tries);
    }

    /**
     * sample standard deviation of percolation threshold
     */
    public double stddev() {

        return StdStats.stddev(tries);
    }

    /**
     * returns lower bound of the 95% confidence interval
     */
    public double confidenceLo() {

        return mean() - ((1.96 * stddev()) / Math.sqrt(tries.length));
    }

    /**
     * returns upper bound of the 95% confidence interval
     */
    public double confidenceHi() {

        return mean() + ((1.96 * stddev()) / Math.sqrt(tries.length));
    }

    /**
     * test client
     */
    public static void main(String[] args) {

        PercolationStats percolationStats =
                new PercolationStats(
                        Integer.parseInt(args[0]),
                        Integer.parseInt(args[1])
                );

        StdOut.printf(
                "mean                     = %.5f\n",
                percolationStats.mean()
        );

        StdOut.printf(
                "stddev                   = %.5f\n",
                percolationStats.stddev()
        );

        StdOut.printf(
                "95%% confidence interval  = %.5f, %.5f\n",
                percolationStats.confidenceLo(),
                percolationStats.confidenceHi()
        );
    }
}
