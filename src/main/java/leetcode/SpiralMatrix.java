package leetcode;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if(matrix.length == 0 ) return res;

        int heightLen = matrix.length;
        int widthLen = matrix[0].length;
        int startX = 0;
        int startY = 0;

        while (heightLen > startX && widthLen > startY) {
            for (int i = startX; i < widthLen; i++) {
                res.add(matrix[startY][i]);
            }

            for (int i = startY + 1; i < heightLen; i++) {
                res.add(matrix[i][widthLen - 1]);
            }

            for (int i = widthLen - 2; i >= startX && heightLen-1 != startX; i--) {
                res.add(matrix[heightLen - 1][i]);
            }

            for (int i = heightLen - 2; i >= startY + 1 && widthLen-1!=startY; i--) {
                res.add(matrix[i][startX]);
            }

            heightLen--;
            widthLen--;
            startX++;
            startY++;
        }
        return res;
    }

    @Test
    public void test() {

        int[][] matrix;
        matrix = new int[][]{{1,2}};
        Assert.assertEquals("[1, 2]", "" + spiralOrder(matrix));

        matrix = new int[][]{{1},{2},{3}};
        Assert.assertEquals("[1, 2, 3]", "" + spiralOrder(matrix));


        matrix = new int[][]{{1,2},{3,4}};
        Assert.assertEquals("[1, 2, 4, 3]", "" + spiralOrder(matrix));

        matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        Assert.assertEquals("[1, 2, 3, 6, 9, 8, 7, 4, 5]", ""+spiralOrder(matrix));

        matrix = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        Assert.assertEquals("[1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]", ""+spiralOrder(matrix));


    }
}
