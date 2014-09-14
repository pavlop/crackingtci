package leetcode;

import java.util.*;

/**
 Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

 Note:
 Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
 The solution set must not contain duplicate quadruplets.

 For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

 A solution set is:
 (-1,  0, 0, 1)
 (-2, -1, 1, 2)
 (-2,  0, 0, 2)

 */
public class Sum4 {
    public List<List<Integer>> fourSum(int[] num, int target) {
        Arrays.sort(num);
        Set<List<Integer>> result = new HashSet<List<Integer>>();

        for (int i = 0; i < num.length; i++) {
            for (int j = i+1; j <  num.length; j++ ) {
                int k = j+1;
                int m = num.length-1;
                while (k<m) {
                    int curSum = num[i]+num[j]+num[k]+num[m];
                    if (curSum == target) {
                        List<Integer> three = new ArrayList<Integer>();
                        three.add(num[i]);
                        three.add(num[j]);
                        three.add(num[k]);
                        three.add(num[m]);
                        result.add(three);
                    }
                    if (curSum > target) m--;
                    else k++;
                }
            }
        }
        return new ArrayList<List<Integer>>(result);
    }

}
