package leetcode;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 There is an array A[N] of N numbers. You have to compose an array Output[N] such that Output[i] will be equal to multiplication of all the elements of A[N] except A[i]. Solve it without division operator and in O(n).

 For example Output[0] will be multiplication of A[1] to A[N-1] and Output[1] will be multiplication of A[0] and from A[2] to A[N-1].

 Example:
 A: {4, 3, 2, 1, 2}
 OUTPUT: {12, 16, 24, 48, 24}
 */
public class MultiplicationOfNumbers {
    int[] multiplicationOfNumbers(int[] A) {
        int[] result = new int[A.length];
        for (int i = 0; i < A.length; i++) result[i] = 1;
        // {1, 3, 2, 5})
        // i = 0:
        // result={1,1,1,1}, left=1, right=5
        // i = 1:
        // result={1,1,5,1}, left=3, right=10
        // i = 2:
        // result={1,10,15,1}, left=6, right=30
        // i = 3:
        // result={30,10,15,6}, left=30, right=30


        int leftProductTillCur = 1;
        int rightProductTillCur = 1;
        for (int i = 0; i < A.length; i++) {
            int j = A.length - i - 1;
            result[i] = result[i]*leftProductTillCur;
            result[j] = result[j]*rightProductTillCur;
            leftProductTillCur = leftProductTillCur * A[i];
            rightProductTillCur = rightProductTillCur * A[j];
        }
        return result;
    }



    @Test
    public void test() {
        Assert.assertEquals("[30, 10, 15, 6]", ""+ Arrays.toString(multiplicationOfNumbers(new int[]{1, 3, 2, 5})));
        Assert.assertEquals("[12, 16, 24, 48, 24]", ""+ Arrays.toString(multiplicationOfNumbers(new int[]{4, 3, 2, 1, 2})));
    }
}
