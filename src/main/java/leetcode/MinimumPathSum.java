package leetcode;

/**
 Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 Note: You can only move either down or right at any point in time.
 */
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int[][] bestPaths = new int[grid.length][grid[0].length];

        bestPaths[0][0] = grid[0][0];

        for (int i = 1; i < grid.length; i ++) {
            bestPaths[i][0] = bestPaths[i-1][0] + grid[i][0];
        }

        for (int j = 1; j < grid[0].length; j ++) {
            bestPaths[0][j] = bestPaths[0][j-1] + grid[0][j];
        }

        for (int i = 1; i < grid.length; i++)
            for (int j = 1; j < grid[0].length; j ++)
                bestPaths[i][j] = grid[i][j] + Math.min (bestPaths[i-1][j], bestPaths[i][j-1]);
        return bestPaths[grid.length-1][grid[0].length-1];
    }
}
