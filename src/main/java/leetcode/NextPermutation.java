package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

 If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

 The replacement must be in-place, do not allocate extra memory.

 Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 1,2,3 → 1,3,2
 3,2,1 → 1,2,3
 1,1,5 → 1,5,1


 // need to find desc sequence in the tail
 // then take last digit which not belongs to that sequence (descTailendsAt)
 // swap it with the smallest digit in the desc sequence bigger then descTailendsAt
 // sort the desc sequence
 // e.g. 326541
 // desc seq: 6541
 // samllest in 6541 that bigger then 2 is 4
 // swap 2 and 4
 // 346521
 // sort 6521
 // 341256

 */
public class NextPermutation {
    public void nextPermutation(int[] num) {
        int descTailendsAt = num.length - 2;
        while(descTailendsAt >= 0 && num[descTailendsAt + 1] <= num[descTailendsAt]) descTailendsAt--;

        //sorted in desc, like 4,3,2,1
        if(descTailendsAt < 0) {
            Arrays.sort(num);
            return;
        }

        //find in desc tail first num bigger then num[descTailendsAt]
        int tailToSwap = num.length - 1;
        for (int i = num.length - 1; i > descTailendsAt; i--) {
            if (num[i] > num[descTailendsAt]) {
                tailToSwap = i;
                break;
            }
        }

        //swap descTailendsAt and biggest tailToSwap
        int tmp = num[tailToSwap];
        num[tailToSwap] = num[descTailendsAt];
        num[descTailendsAt] = tmp;

        //sort
        Arrays.sort (num, descTailendsAt + 1, num.length );

    }

    @Test
    public void test() {
        int[] input = new int[] {1, 2};
        nextPermutation(input);
        Assert.assertEquals("[2, 1]", Arrays.toString(input));

        input = new int[] {1, 3, 2};
        nextPermutation(input);
        Assert.assertEquals("[2, 1, 3]", Arrays.toString(input));

        input = new int[] {3,2,1};
        nextPermutation(input);
        Assert.assertEquals("[1, 2, 3]", Arrays.toString(input));
    }

}
