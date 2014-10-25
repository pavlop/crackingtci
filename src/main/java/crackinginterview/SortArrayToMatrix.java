package crackinginterview;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 *
 *
 */
public class SortArrayToMatrix {
    public static int[][] asSortedMatrix(int[] array) {
        // sort the input array (n*n * log n*n) -- too slow

        // split array to n groups (rows)
        // sort each row (n*n logn)
        // sort by first element (n*n logn)
        int n = (int) Math.sqrt(array.length); // validate that n*n == array.length
        int[][] result = new int[n][n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = array[idx];
                idx++;
            }
            Arrays.sort(result[i]);
        }

        rotate(result);
        for ( int i =0; i < n; i++) {
            Arrays.sort(result[i]);
        }

        return result;
    }

    public static void rotate(int[][] matrix) {
        int start = 0;
        int end = matrix.length - 1;
        while(start<end) {
            for (int i = 0; i < end-start; i++) {
                int tmp = matrix[start][start+i];
                matrix[start][start+i] = matrix[end-i][start];
                matrix[end-i][start] = matrix[end][end-i];
                matrix[end][end-i] = matrix[start+i][end];
                matrix[start+i][end] = tmp;
            }
            start++;
            end--;
        }
    }

    @Test
    public void test() {
        int[] input = new int[] {1,2,3,4};
        System.out.println(Arrays.deepToString(asSortedMatrix(input)));

        input = new int[] {4,3,2,1};
        System.out.println(Arrays.deepToString(asSortedMatrix(input)));

        input = new int[] {1,3,4,2};
        System.out.println(Arrays.deepToString(asSortedMatrix(input)));

        input = new int[] {1};
        System.out.println(Arrays.deepToString(asSortedMatrix(input)));

        input = new int[] {9, 1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(Arrays.deepToString(asSortedMatrix(input)));
    }
}
