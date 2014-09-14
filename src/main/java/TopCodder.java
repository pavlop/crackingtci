
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 *
 *
 *
 */
public class TopCodder {
    public static int[] waysToGetValuesFromElements(int[] elements) {
        int maxSum = 0;
        for (int e:elements) maxSum+=e;
        return waysToGetValuesFromElements(elements, maxSum);
    }

    public static int[] waysToGetValuesFromElements(int[] elements, int maxSum) {

        Arrays.sort(elements);

        int[] ways = new int[maxSum + 1];
        ways[0] = 1; // there is at least one way to get sum using current element

        for (int elemIdx = 0; elemIdx < elements.length; elemIdx++) {
            int curElement = elements[elemIdx];
            for (int sum = maxSum; sum >=curElement; sum--) {
                ways[sum] += ways[sum - curElement];
            }
        }
        return ways;
    }

    public static long[] toLongs(int ... nums) {
        long[] res = new long[nums.length];
        for (int i = 0; i < nums.length; i++) res[i] = nums[i];
        return res;
    }

    public static void printArrays(long[] ... arrays) {
        for (long[] arr : arrays) {
            for (int i = 0; i < arr.length; i++) System.out.format("%05d ", arr[i]);
            System.out.println("");
        }
    }

    public static void fill(int[] d, int val) {
        for(int i = 0; i<d.length; i++) d[i] = val;
    }

    public static void fill(long[] d, long val) {
        for(int i = 0; i<d.length; i++) d[i] = val;
    }

    @Test
    public void testWaysToGetValuesFromElements() {
        int[] elements = new int[] {1,2,3,4};
        assertThat("[1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1]", equalTo(Arrays.toString(waysToGetValuesFromElements(elements))));
    }


}

class Pair<T> {
    public T left;
    public T right;

    Pair(T left, T right) {
        this.left = left;
        this.right = right;
    }
}
