package leetcode;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *
 *
 *
 */
public class MaximumSubarrayRec {
    int[] bestStartat;

    // CAN DO WITHOUT MEMORY
    // ISSUE: java.lang.StackOverflowError

    public int bestSumAt(int startAt, int[] A) {
        System.out.println("startAt="+startAt);
        if(startAt == A.length-1) return A[A.length-1];
        int maxStartAt;
        maxStartAt = Math.max(A[startAt], A[startAt]+bestSumAt(startAt+1, A));
        bestStartat[startAt] = maxStartAt;
        return maxStartAt;
    }

    public int maxSubArray(int[] A) {
        bestStartat = new int[A.length];
        bestSumAt(0, A);
        System.out.println("bestStartat"+ Arrays.toString(bestStartat));
        int max = Integer.MIN_VALUE;
        for (int x:bestStartat) {
            max = Math.max(max,x);
        }
        return max;
    }

    @Test
    public void test() {
        int[] arr = {12, -1, -10, 0, 20, -3};
        Assert.assertEquals(21, maxSubArray(arr));
    }
}
