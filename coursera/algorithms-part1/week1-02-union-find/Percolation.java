import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF unionFind;
    private int visualBottom;
    private int visualTop;
    private int count;

    private void validate(int row, int col) {
        int n = grid.length;
        if (row < 1 || row > n) {
            throw new IllegalArgumentException("row " + row + " is not between 1 and " + n);
        }
        if (col < 1 || col > n) {
            if (row < 1 || row > n) {
                throw new IllegalArgumentException("col " + col + " is not between 1 and " + n);
            }
        }
    }

    private int index(int row, int col) {
        return row * grid[row].length + col + 1;
    }

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n can not be less than 1");
        }

        grid = new boolean[n][n];

        int ufSize = n * n + 2;
        visualTop = 0;
        visualBottom = ufSize - 1;
        unionFind = new WeightedQuickUnionUF(ufSize);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);

        if (isOpen(row, col)) {
            return;
        }

        row--;
        col--;
        grid[row][col] = true;
        count++;
        int[][] offsets = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
        int point = index(row, col);
        for (int[] offset : offsets) {
            int r = row + offset[0];
            int c = col + offset[1];
            if (r >= 0 && r < grid.length && c >= 0 && c < grid[r].length && grid[r][c]) {
                unionFind.union(point, index(r, c));
            }
            if (row == 0) {
                unionFind.union(visualTop, point);
            }
            if (row == grid.length - 1) {
                unionFind.union(visualBottom, point);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        row--;
        col--;
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        row--;
        col--;

        return unionFind.find(index(row, col)) == unionFind.find(visualTop);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return unionFind.find(visualBottom) == unionFind.find(visualTop);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(2);
        percolation.open(1, 1);
        percolation.open(2, 2);

        StdOut.println(percolation.numberOfOpenSites());
        StdOut.println(percolation.percolates());
        StdOut.println(percolation.isFull(1, 1));
        StdOut.println(percolation.isOpen(1, 2));

        percolation.open(2, 1);
        StdOut.println(percolation.isOpen(2, 1));
        StdOut.println(percolation.percolates());
    }
}
