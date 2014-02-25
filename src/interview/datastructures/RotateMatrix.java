package interview.datastructures;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by pavlop on 2/23/14.
 */
public class RotateMatrix {
    public static int[][] perform(int[][] matrix) {

        // solution with additional memory

        /*
        int[][] res = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                res[j][matrix.length - i-1] = matrix[i][j];
            }
        }
        return res;
        */

        // in-place solution (for sq only)
        for (int i = 0; i < (matrix.length)/2; i++) {
            for (int j = 0; j < (matrix.length+1)/2; j++) {
                int tmp = matrix[j][matrix.length - i-1] ;

                matrix[j][matrix.length - i-1] = matrix[i][j];
                matrix[i][j] = matrix[matrix.length-1-j][i];
                matrix[matrix.length-1-j][i] = matrix[matrix.length - i-1][matrix.length - j-1];
                matrix[matrix.length - i-1][matrix.length - j-1] = tmp;

            }
        }
       return matrix;

        /*
        Rotate by +90:

        Transpose
        Reverse each row
        Rotate by -90:

        Transpose
        Reverse each column
        Rotate by +180:

        Method 1: Rotate by +90 twice

        Method 2: Reverse each row and then reverse each column

        Rotate by -180:

        Method 1: Rotate by -90 twice

        Method 2: Reverse each column and then reverse each row

        Method 3: Reverse by +180 as they are same
        Method 3: Reverse by +180 as they are same
      */
    }

    @Test
    public void positiveWithMemeory() throws Exception {

        int[][] input = {   {1,2},
                            {3,4}
                        };
        int[][] expected = {{3,1},
                            {4,2}
                        };
        assertEquals(Arrays.deepToString(expected), Arrays.deepToString(perform(input)));


        int[][] input2 = {  {1,2,3},
                            {4,5,6},
                            {7,8,9}
        };
        int[][] expec2 = {  {7,4,1},
                            {8,5,2},
                            {9,6,3}
        };
        assertEquals(Arrays.deepToString(expec2), Arrays.deepToString(perform(input2)));

        int[][] input3 = {{1}};
        int[][] expec3 = {{1}};
        assertEquals(Arrays.deepToString(expec3), Arrays.deepToString(perform(input3)));

        int[][] input4 =  {
                { 1,2,3,4 },
                { 5,6,7,8 },
                { 9,0,1,2 },
                { 3,4,5,6 }
        };
        ;
        int[][] expec4 = {{3,9,5,1},
                {4,0,6,2},
                {5,1,7,3},
                {6,2,8,4}};
        assertEquals(Arrays.deepToString(expec4), Arrays.deepToString(perform(input4)));
    }
}
