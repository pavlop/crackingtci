package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 *
 *
 */
public class MaxRectangle {

    public int maximalRectangle(char[][] matrix) {

        int x = matrix.length;
        if(x == 0) return 0;
        int y = matrix[0].length;
        int[][] upSums = new int[x][y];
        int[][] matrixInt = new int[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                matrixInt[i][j] = matrix[i][j] - '0';
            }
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(matrixInt[i][j] == 0) continue;
                if(i == 0 ) {
                    upSums[0][j] = matrixInt[0][j];
                } else {
                    upSums[i][j] = upSums[i - 1][j] + 1;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < x; i++) {
            int curmax = (new LargestRectangleHistogram()).largestRectangleArea(upSums[i]);
            max = Math.max(curmax, max);
        }
        return max;
    }


    @Test
    public void test() {
        char[][] matrix ;
        matrix = new char[][]{{'0'}};
        Assert.assertEquals(0, maximalRectangle(matrix));

        matrix = new char[][]{{'1'}};
        Assert.assertEquals(1, maximalRectangle(matrix));

        matrix = new char[][]{{'1', '1'}};
        Assert.assertEquals(2, maximalRectangle(matrix));

        matrix = new char[][]{{'1', '0'}};
        Assert.assertEquals(1, maximalRectangle(matrix));

        matrix = new char[][] {
                {'0','1','1','0','1'},
                {'1','1','0','1','0'},
                {'0','1','1','1','0'},
                {'1','1','1','1','0'},
                {'1','1','1','1','1'},
                {'0','0','0','0','0'},
        };
        Assert.assertEquals(9, maximalRectangle(matrix));

        matrix = new char[][] {
                {'1','1','1','1'},
                {'1','1','1','1'},
                {'1','1','1','1'}
        };
        Assert.assertEquals(12, maximalRectangle(matrix));
        matrix = new char[][] {
            "111111011111111".toCharArray(),
            "101101111111111".toCharArray(),
            "111111111111111".toCharArray(),
            "011111101110111".toCharArray(),
            "100111111110111".toCharArray(),
            "111111111111111".toCharArray(),
            "111011111110111".toCharArray(),
            "111100011111010".toCharArray(),
            "101100011110101".toCharArray(),
            "101111110111011".toCharArray(),
            "101111111111111".toCharArray(),
            "111011111111111".toCharArray(),
            "111000101111111".toCharArray(),
            "111111011111111".toCharArray(),
            "111111101111101".toCharArray()};
        Assert.assertEquals(30, maximalRectangle(matrix));



    }
}
