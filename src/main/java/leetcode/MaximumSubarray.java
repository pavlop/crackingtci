package leetcode;

import junit.framework.Assert;
import org.junit.Test;

/**
 *
 *Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

 For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
 the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 *
 */
public class MaximumSubarray {
    // determine maximum ending at i position
    // it is either :
    //      maximum ending at i-1
    //      or
    //      A[i] element
    public int maxSubArray(int[] A) {
        int maxEndingHere = A[0];
        int maxSoFar = A[0];
        for (int i = 1; i < A.length; i++) {
            maxEndingHere = Math.max(A[i], maxEndingHere + A[i]);
            //System.out.println("maximum ending at "+i+" is "+maxEndingHere);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
            //System.out.println("maximum maxSoFar is "+maxSoFar);
        }
        return maxSoFar;
    }

    @Test
    public void test() {
        int[] arr = {12, -1, -10, 0, 20, -3};
        Assert.assertEquals(21, maxSubArray(arr));

    }
}
