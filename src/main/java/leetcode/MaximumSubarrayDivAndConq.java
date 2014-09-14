package leetcode;


import junit.framework.Assert;
import org.junit.Test;

/**
 *
 *Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

 For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
 the contiguous subarray [4,−1,2,1] has the largest sum = 6.


 //ANSWER
 http://www.geeksforgeeks.org/divide-and-conquer-maximum-sum-subarray/

 *
 */
public class MaximumSubarrayDivAndConq {
    public int maxSubArray(int[] A) {
        return maxSubArraySum(A, 0, A.length - 1);
    }


    // A utility funtion to find maximum of three integers
    int max(int a, int b, int c) { return Math.max(Math.max(a, b), c); }

    // Find the maximum possible sum in arr[] auch that arr[m] is part of it
    int maxCrossingSum(int arr[], int left, int middle, int right)
    {
        // Include elements on left of mid.
        int sum = 0;
        int leftSum = Integer.MIN_VALUE;
        for (int i = middle; i >= left; i--) {
            sum = sum + arr[i];
            if (sum > leftSum)
                leftSum = sum;
        }

        // Include elements on right of mid
        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        for (int i = middle+1; i <= right; i++) {
            sum = sum + arr[i];
            if (sum > rightSum)
                rightSum = sum;
        }

        // Return sum of elements on left and right of mid
        return leftSum + rightSum;
    }

    // Returns sum of maxium sum subarray in aa[l..h]
    int maxSubArraySum(int arr[], int left, int right) {
        System.out.println("call left="+left+" right="+right);
        // Base Case: Only one element
        if (left == right)
            return arr[left];

        // Find middle point
        int mid = (left + right)/2;

   /* Return maximum of following three possible cases
      a) Maximum subarray sum in left half
      b) Maximum subarray sum in right half
      c) Maximum subarray sum such that the subarray crosses the midpoint */
        return max(maxSubArraySum(arr, left, mid),
                maxSubArraySum(arr, mid+1, right),
                maxCrossingSum(arr, left, mid, right));
    }

    @Test
    public void test() {
        int[] arr = {12, -1, -10, 0, 20, -3};
        //Assert.assertEquals(21, maxSubArray(arr));
        arr = new int[]{-1, 1, -2, 2, -3, 3, -4, 4, -5, 5, -6 ,6 -7, 7};
        Assert.assertEquals(21, maxSubArray(arr));

    }
}
