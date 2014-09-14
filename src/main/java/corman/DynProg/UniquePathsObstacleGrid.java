package corman.DynProg;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *
 *
 *
 */
public class UniquePathsObstacleGrid {
    int[][] sols;
    int[][] obstacleGrid;

    // TODO
    // If sols[m][n]  == 0 - doesn't mean we haven't calculated it yet for this task
    // need to initaize with "-1"
    public int uniquePathsWithObstacles(int[][] grid) {
        this.obstacleGrid = grid;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        sols = new int[m][n];
        int res = uniquePathsRec(m-1, n-1);
        return res;
    }

    public int uniquePathsRec(int m, int n) {
        if(m == 0 && n == 0) return 1-obstacleGrid[0][0];

        if (obstacleGrid[m][n] == 1) return 0;
        if(sols[m][n] != 0) return sols[m][n];

        int cur = 0;
        if(m != 0) {
            cur+=uniquePathsRec(m-1, n);
        }
        if (n != 0) {
            cur+=uniquePathsRec(m, n-1);
        }
        sols[m][n] = cur;
        return cur;
    }

    @Test
    public void test() {
        int[][] grid = {{0}};
        Assert.assertEquals(1, uniquePathsWithObstacles(grid));

        grid = new int[][]{{1}};
        Assert.assertEquals(0, uniquePathsWithObstacles(grid));

    }
}
