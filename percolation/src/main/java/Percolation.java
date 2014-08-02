/**
 * Jerry Kiely
 * jerry@cowboysmall.com
 * Monday June 16th
 * <p/>
 * This class models the scientific problem of percolation.
 * All row, col entries are 1 indexed.
 */
public class Percolation {

    // data structure for holding the sites connected to the start
    private WeightedQuickUnionUF fullPath;

    // data structure for holding paths from start to end
    private WeightedQuickUnionUF completePath;

    // array representation of the N-by-N grid
    private boolean[] grid;

    // the length of the grid
    private int length;

    // the virtual start site
    private int start;

    // the virtual end site
    private int end;

    /**
     * create N-by-N grid, with all sites blocked
     */
    public Percolation(int N) {

        if (N <= 0)
            throw new IllegalArgumentException();

        length = N;

        int total = N * N;
        start = total;
        end = total + 1;

        grid = new boolean[total];

        fullPath = new WeightedQuickUnionUF(total + 1);
        completePath = new WeightedQuickUnionUF(total + 2);
    }

    /**
     * open site (row i, column j) if it is not already
     */
    public void open(int i, int j) {

        if (!isOpen(i, j)) {

            int index = convertToID(i, j);

            grid[index] = true;

            if (i == 1) {

                completePath.union(start, index);
                fullPath.union(start, index);
            }

            if (i == length)
                completePath.union(index, end);

            if (i > 1 && grid[convertToID(i - 1, j)]) {

                completePath.union(index, convertToID(i - 1, j));
                fullPath.union(index, convertToID(i - 1, j));
            }

            if (i < length && grid[convertToID(i + 1, j)]) {

                completePath.union(index, convertToID(i + 1, j));
                fullPath.union(index, convertToID(i + 1, j));
            }

            if (j > 1 && grid[convertToID(i, j - 1)]) {

                completePath.union(index, convertToID(i, j - 1));
                fullPath.union(index, convertToID(i, j - 1));
            }

            if (j < length && grid[convertToID(i, j + 1)]) {

                completePath.union(index, convertToID(i, j + 1));
                fullPath.union(index, convertToID(i, j + 1));
            }
        }
    }

    /**
     * is site (row i, column j) open?
     */
    public boolean isOpen(int i, int j) {

        checkValid(i, j);

        return grid[convertToID(i, j)];
    }

    /**
     * is site (row i, column j) full?
     */
    public boolean isFull(int i, int j) {

        checkValid(i, j);

        return fullPath.connected(start, convertToID(i, j));
    }

    /**
     * does the system percolate?
     */
    public boolean percolates() {

        return completePath.connected(start, end);
    }


    /**
     * utility method to check if a row, column is valid
     */
    private void checkValid(int row, int col) {

        if (row < 1 || row > length || col < 1 || col > length)
            throw new IndexOutOfBoundsException();
    }

    /**
     * utility method to convert grid position to index in the data structure
     */
    private int convertToID(int row, int col) {

        return (row - 1) * length + (col - 1);
    }
}
