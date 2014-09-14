import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class Jewelry {
    int MAX_SUM = 0;
    int[] vals;

    public long howMany(int[] values) {
        this.vals = values;
        long [][] C = new long[31][32];
        long ret = 0;
        Arrays.sort(values);

        // group start/stop
        for (int i = 0; i<values.length; i++) {
            int startIdx = i;
            int endIdx = i;
            int G = values[i];
            while (startIdx > 0 && G == values[startIdx-1]) startIdx--;
            while (endIdx < values.length -1 && G == values[endIdx+1]) endIdx++;
            ret += count(values, startIdx, endIdx); // count for group
            i = endIdx;
        }
        return ret;
	}

    private long count(int[] values, int start, int end/*, long[][] C)*/ ) {
        long ret = 0;
        int sumA = 0;
        for (int i = 0; i < start ; i++) sumA +=values[i];
        long[] below = new long[sumA + 1];
        below[0] = 1;

        // ways to sum to s using 0..start-1
        for (int i = 0; i<start;i++)
            for (int s = sumA; s>= values[i]; s--)
                below[s] += below[s-values[i]];

        int sz = end - start + 1; // size of the group
        int G = values[start]; // group element

        for (int aG = 1; aG <= sz; aG++) { // # of G:s to A

            int sumB = 0;
            for (int i = start+aG; i < values.length; i++) sumB += values[i];

            long[] above = new long[sumB+1];
            above[0] = 1;

            for (int i = start+aG; i < values.length; i++)
                for (int s = sumB; s >= values[i]; s--)
                    above[s] += above[s-values[i]];

            for (int s = 0; s <= sumB; s++) {
                int belowA = s - aG*G;
                if (belowA <= sumA && belowA >= 0) {
                    long plus = choose(sz, aG /*, C*/) * below[belowA]*above[s];
                    ret += plus;
                }
            }
        }


        return ret;
    }

    private long choose(int n, int k/*, long[][] C*/) {
        if (n < 0 || n > k) return 0;
        if (n == k || k == 0) {
            return 1;
        } else {
            return choose(n-1, k-1) + choose(n-1, k);
        }
    }

/**
    Given K elements 1..K, you can calculate the number of ways to make various sums of those values using a typical knapsack algorithm:
    initialize array ways[0..Max] to all zeros
    ways[0] = 1
            for each i from 1 upto K do
            for each sum s from max downto element i do
    ways[s] += ways[s - element i]
 */
/*long[][] getNumberOfWaysUsingLeftElements() {
        long[][] waysToGetSumUsingLeftElements = new long[MAX_SUM + 1][vals.length];
        for (int curMaxElementID = 0; curMaxElementID < vals.length; curMaxElementID++) {

            waysToGetSumUsingLeftElements[0][curMaxElementID] = 1;

            for (int i = 0; i <= curMaxElementID; i++) {
                int curElement = vals[i];
                for (int sum = i+1; sum <= MAX_SUM; sum++) {
                    waysToGetSumUsingLeftElements[sum][curMaxElementID] += waysToGetSumUsingLeftElements[sum - curElement][curMaxElementID];
                }
            }

        }
        return waysToGetSumUsingLeftElements;
    }



    int[] combunationsFromElements(int[] elements, int MAX_SUM) {
        int[] combinations = new int[MAX_SUM+1];
        combinations[0] = 1;
        Arrays.sort(elements);
        for (int sum = 0; sum < combinations.length; sum ++) {
            for (int element : elements) {

            }
        }
    }*/

public void printArrays(long[] ... arrays) {
    for (long[] arr : arrays) {
        for (int i = 0; i < arr.length; i++) System.out.format("%05d ", arr[i]);
        System.out.println("");
    }
}

}
