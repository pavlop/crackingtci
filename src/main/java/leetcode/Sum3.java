package leetcode;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertEquals;

/**
 Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

 Note:
 Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 The solution set must not contain duplicate triplets.

 For example, given array S = {-1 0 1 2 -1 -4},

 A solution set is:
 (-1, 0, 1)
 (-1, -1, 2)

 *
 */
public class Sum3 {
    public List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);
        Set<List<Integer>> result = new HashSet<List<Integer>>();

        for (int i = 0; i < num.length; i++) {
            for (int j = i+1,  k = num.length -1; j <  k; ) {
                int curSum = num[i]+num[j]+num[k];
                if (curSum == 0) {
                    List<Integer> three = new ArrayList<Integer>();
                    three.add(num[i]);
                    three.add(num[j]);
                    three.add(num[k]);
                    result.add(three);
                }
                if (curSum > 0) k--;
                else j++;
            }
        }
        return new ArrayList<List<Integer>>(result);
    }

    @Test
    public void doTest() {
        assertEquals("[[-5, 2, 3]]", ""+threeSum(new int[]{-5, 2, 3, 4}));
        assertEquals("[[0, 0, 0]]", "" + threeSum(new int[]{0, 0, 0}));
    }
 
}
