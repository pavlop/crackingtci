package leetcode;

import junit.framework.Assert;
import org.junit.Test;

import java.util.*;

/**
 *
 *
 *
 */
public class CombinationSumUnique {
    Set<List<Integer>> result = new LinkedHashSet<List<Integer>>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        combinationSumRec(candidates, 0, target, new ArrayList<Integer>());
        List<List<Integer>> listResult = new ArrayList<List<Integer>>(result);
        return listResult;
    }

    public void combinationSumRec(int[] candidates, int startAt, int target, List<Integer> curRes) {
        if(target == 0) {
            result.add(curRes);
        } else if (startAt >= candidates.length || target < 0) {
            //do nothing
        } else if (candidates[startAt] > target) {
            // do nothing, all the remaining elements of the array are too big
        } else {
            // we can add current or skip it
            //skip
            combinationSumRec (candidates, startAt+1, target, new ArrayList<Integer>(curRes));

            //include
            List<Integer> incl = new ArrayList<Integer>(curRes);
            incl.add(candidates[startAt]);
            combinationSumRec (candidates, startAt+1, target - candidates[startAt], incl);
        }
    }

    @Test
    public void test() {
        int [] input = {1};
        List<List<Integer>> listResult = new ArrayList<List<Integer>>();
        listResult.add(Arrays.asList(1));
        Assert.assertEquals(listResult, combinationSum2(input, 1));


        int [] input2 = {13,23,25,11,7,26,14,11,27,27,26,12,8,20,22,34,27,17,5,26,31,11,16,27,13,20,29,18,7,14,13,15,25,25,21,27,16,22,33,8,15,25,16,18,10,25,9,24,7,32,15,26,30,19,13,23,25,11,7,26,14,11,27,27,26,12,8,20,22,34,27,17,5,26,31,11,16,27,13,20,29,18,7,14,13,15,25,25,21,27,16,22,33,8,15,25,16,18,10,25,9,24,7,32,15,26,30,19,13,23,25,11,7,26,14,11,27,27,26,12,8,20,22,34,27,17,5,26,31,11,16,27,13,20,29,18,7,14,13,15,25,25,21,27,16,22,33,8,15,25,16,18,10,25,9,24,7,32,15,26,30,19};
        combinationSum2(input2, 25);
    }
}
