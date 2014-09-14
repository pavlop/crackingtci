package dynamicProgramming;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertSame;

/**
Для заданной числовой последовательности A[1..N] найти максимальную сумму значений подряд иду-
щих элементов.
Входными параметрами являются число N и последовательность из N целых чисел, 1 ≤N ≤100, |Ai|≤100,
i = 1, .... N.
Например, при N = 3, А = {5; —3; 6} искомая сумма равна 8.

*/

/**
 * Created by pavlop on 3/30/14.
 */
public class SequenceWithMaxSum {
    public int getMaxSequeceSum (int[] arr) {
        int[] maxSummsTillIElement = new int[arr.length];
        maxSummsTillIElement[0] = arr[0];
        for(int i = 1 ; i < arr.length; i++) {
            int curMaxCandidate = maxSummsTillIElement[i-1]+arr[i];
            maxSummsTillIElement[i] = (curMaxCandidate>arr[i])?curMaxCandidate:arr[i];
        }
        System.out.println(Arrays.toString(maxSummsTillIElement));
        return getMax(maxSummsTillIElement);
    }

    @Test
    public void test() {
        int[] arr = {5, -3, 8, -10, 2};
        assertSame (10, getMaxSequeceSum(arr));

        int[] arr2 = {1, -30, 2, -30};
        assertSame (2, getMaxSequeceSum(arr2));

        int[] arr3 = {10, -5, 10, -5, 10};
        assertSame (20, getMaxSequeceSum(arr3));
    }

    public int getMax(int[] arr){
        Arrays.sort(arr);
        return arr[arr.length-1];
    }
}
