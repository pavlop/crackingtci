package leetcode;

import junit.framework.Assert;
import org.junit.Test;


/**
 *
 *Given an array of integers, every element appears three times except for one. Find that single one.

 Note:
 Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 */

public class SingleNumberTripple {

    public int singleNumber(int[] A) {
        int[] counterOfOnes = new int[32];
        int negatives = 0;
        for (long num:A) {
            int indx = 0;
            if (num < 0) {
                negatives++;
                num = -num;
            }
            while(num > 0) {
                if ((num&1) > 0) counterOfOnes[indx] += 1;
                num = num >> 1;
                indx++;
            }
        }

        int res = 0;
        for (int i = 0; i < counterOfOnes.length; i++ ) {
            counterOfOnes[i] = counterOfOnes[i]%3;
            counterOfOnes[i] = counterOfOnes[i] << i;
            res += counterOfOnes[i];
        }
        if (negatives%3 != 0) res = -res;
        return res;
    }

    @Test
    public void test() {
        Assert.assertEquals(-4, singleNumber(new int[]{-2,-2,1,1,-3,1,-3,-3,-4,-2}));
        Assert.assertEquals(-2147483648, singleNumber(new int[] {-2147483648}));
    }
}