package leetcode;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *
 *
 *
 */
public class FirstMissingPositive {
    public int firstMissingPositive(int[] A) {

        // swap current element back, if it is positive
        for (int i = 0; i < A.length; i++) {
            int idTo = A[i] - 1;
            // don't swap ij in the tartget location is the same element already
            while (A[i] <= i && A[i] > 0 && A[i] != A[idTo]) {
                idTo = A[i] - 1;
                swap (A, i, idTo);
            }
        }
        int i = 0;
        // System.out.println(Arrays.toString(A));
        while (i < A.length && A[i] == i+1) {
            i++;
        }
        return i+1;
    }

    public void swap (int[]A, int curId, int newId) {
        int tmp = A[curId];
        A[curId] = A[newId];
        A[newId] = tmp;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, firstMissingPositive(new int[]{-1, 4, 2, 1, 9, 10}));
        Assert.assertEquals(7, firstMissingPositive(new int[]{11, 1, 6, 11, 5, 5, -6, 9, -3, 9, 5, 4, 2, -8, 16, -6, -4, 2, 3}));
    }
}
