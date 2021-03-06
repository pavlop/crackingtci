package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

/**
 * Given an array of integers, find two numbers such that
 * they add up to a specific target number.

 The function twoSum should return indices of the two numbers
 such that they add up to the target, where index1 must be less than index2.
 Please note that your returned answers (both index1 and index2) are not zero-based.

 You may assume that each input would have exactly one solution.

 Input: numbers={2, 7, 11, 15}, target=9
 Output: index1=1, index2=2
 */
public class TwoNumbersTargetSum {

    public int[] twoSum(int[] numbers, int target) {
        //MAP VLUE -> ID
        HashMap<Integer, Integer> tmp = new HashMap<Integer, Integer>();
        for(int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            if (tmp.containsKey(target - num)) return new int[] {tmp.get(target - num)+1, i+1};
            tmp.put(num, i);
        }
        return null;
    }

    @Test
    public void doTest() {
        TwoNumbersTargetSum clz = new TwoNumbersTargetSum();
        int[] numbers = {11,3,5,7, 4, 12};
        int target = 15;
        int[] res = {1,5};
        assertEquals(Arrays.toString(res), Arrays.toString(clz.twoSum(numbers, target)));
    }
}
