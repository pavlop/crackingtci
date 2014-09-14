package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**

 Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

 The same repeated number may be chosen from C unlimited number of times.

 Note:
 All numbers (including target) will be positive integers.
 Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 The solution set must not contain duplicate combinations.
 For example, given candidate set 2,3,6,7 and target 7,

 A solution set is:
 [7]
 [2, 2, 3]

 */
public class CombinationSum {
    List<List<Integer>> result = new ArrayList<List<Integer>>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        combinationSumRec(candidates, 0, target, new ArrayList<Integer>());
        return result;
    }

    public void combinationSumRec(int[] candidates, int startAt, int target, List<Integer> curRes) {
        if(target == 0) {
            result.add(curRes);
        } else if (startAt >= candidates.length || target < 0) {
            //do nothing
        } else {
            // we can add current or skip it
            //skip
            combinationSumRec (candidates, startAt+1, target, new ArrayList<Integer>(curRes));

            //include
            List<Integer> incl = new ArrayList<Integer>(curRes);
            incl.add(candidates[startAt]);
            combinationSumRec (candidates, startAt, target - candidates[startAt], incl);
        }

    }
}
