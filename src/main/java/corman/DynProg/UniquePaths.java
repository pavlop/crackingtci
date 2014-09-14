package corman.DynProg;

import org.junit.Test;

import java.util.Arrays;

/**
 *
 *
 *
 */
public class UniquePaths {
    /*
    A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?
     */
    int[][] sols;
    public int uniquePaths(int m, int n) {
        sols = new int[m][n];
        System.out.println(Arrays.asList(sols));
        return uniquePathsRec(m-1, n-1);
    }

    public int uniquePathsRec(int m, int n) {
        if(m == 0 || n == 0) return 1;
        if(sols[m][n] != 0) return sols[m][n];
        int cur = uniquePathsRec(m-1, n) + uniquePathsRec(m, n-1);
        sols[m][n] = cur;
        return cur;
    }

    @Test
    public void test() {
        uniquePaths(2,2);
    }
}
