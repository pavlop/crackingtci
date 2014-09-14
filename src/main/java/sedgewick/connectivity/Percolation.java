package sedgewick.connectivity;

/**
 *
 *
 */
public class Percolation {
    private boolean[][] grid;
    private int N;
    private WeightedQuickUnionUF quickUnionWeighted;
    private int topNode;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException();
        this.N = N;
        this.topNode = N * N;
        grid = new boolean[N][N];
        quickUnionWeighted = new WeightedQuickUnionUF(topNode + 1);
    }

    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        grid[i - 1][j - 1] = true;
        connectNeighbours(i - 1, j - 1);
        if (i == 1) {
            quickUnionWeighted.union(getGlobalIndex(i - 1, j - 1), topNode);
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        return grid[i - 1][j - 1];
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        return (isOpen(i, j) && quickUnionWeighted.connected(getGlobalIndex(i - 1, j - 1), topNode));
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 0; i < N; i++) {
            if (grid[N - 1][i] && quickUnionWeighted.connected(topNode, getGlobalIndex(N - 1, i)))
                return true;
        }
        return false;
    }

    private void connectNeighbours(int i, int j) {
        if (grid[i][j]) {
            int curId = getGlobalIndex(i, j);
            if (i + 1 < N && grid[i + 1][j]) quickUnionWeighted.union(curId, getGlobalIndex(i + 1, j));
            if (i - 1 >= 0 && grid[i - 1][j]) quickUnionWeighted.union(curId, getGlobalIndex(i - 1, j));
            if (j + 1 < N && grid[i][j + 1]) quickUnionWeighted.union(curId, getGlobalIndex(i, j + 1));
            if (j - 1 >= 0 && grid[i][j - 1]) quickUnionWeighted.union(curId, getGlobalIndex(i, j - 1));
        }
    }


    private int getGlobalIndex(int i, int j) {
        return i * grid.length + j;
    }

}
