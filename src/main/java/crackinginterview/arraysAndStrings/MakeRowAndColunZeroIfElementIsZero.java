package crackinginterview.arraysAndStrings;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by pavlop on 2/25/14.
 */
public class MakeRowAndColunZeroIfElementIsZero {
    public static int[][] perform(int[][] matrix) {
        boolean areZerosInFirstRow = false;
        boolean areZerosInFirstCol = false;
        for(int i = 0; i < matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                if(matrix[i][j] == 0) {
                    if(i == 0) {
                        areZerosInFirstRow = true;
                    }
                    if(j == 0) {
                        areZerosInFirstCol = true;
                    }
                    if(i>0 && j >0) {
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }

            }

        }

        for(int i = 1; i<matrix.length; i++){
            if(matrix[i][0] == 0) makeRowZeros(matrix, i);
        }

        for (int i = 1; i<matrix[0].length; i++) {
            if(matrix[0][i] == 0) makeColZeros(matrix, i);
        }

        if(areZerosInFirstCol) makeColZeros(matrix,0);
        if(areZerosInFirstRow) makeRowZeros(matrix, 0);
        return matrix;
    }

    private static void makeColZeros(int[][] matrix, int colID) {
        for(int i =0; i<matrix.length; i++){
            matrix[i][colID]=0;
        }
    }

    private static void makeRowZeros(int[][] matrix, int rowID) {
        for(int i =0; i<matrix[0].length; i++){
            matrix[rowID][i]=0;
        }
    }

    @Test
    public void doTest(){
        int[][] input = {{1,2,0},
                    {3,3,2},
                    {0,3,3},
                    {3,3,3}
            };

        int[][] expected = {{0,0,0},
                {0,3,0},
                {0,0,0},
                {0,3,0}
        };

        assertEquals(Arrays.deepToString(expected), Arrays.deepToString(perform(input)));
    }

}
