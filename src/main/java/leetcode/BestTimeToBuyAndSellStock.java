package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 Say you have an array for which the ith element is the price of a given stock on day i.

 If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 */
public class BestTimeToBuyAndSellStock {

    // look to the right from the minimum
    // or to the left from the minimum
    public int maxProfit(int[] prices) {
        int maxID = prices.length - 1;
        int minID = 0;

        int[] mins = new int[prices.length];
        int[] maxs = new int[prices.length];

        for(int i = 0; i < prices.length; i++) {
            int j = prices.length - i -1;
            if (prices[i] < prices[minID]) minID = i;
            mins[i] = prices[minID];

            if (prices[j] > prices[maxID]) maxID = j;
            maxs[j] = prices[maxID];
        }

        int result = 0;
        for (int i = 0; i < prices.length; i++) {
            result = Math.max(result, maxs[i] - mins[i]);
        }

        return result;
    }

    @Test
    public void test() {
        int[] input;
        input = new int[]{1,2,3};
        Assert.assertEquals(2, maxProfit(input));

        input = new int[]{3,2,1};
        Assert.assertEquals(0, maxProfit(input));

        input = new int[]{80,89,0, 20};
        Assert.assertEquals(20, maxProfit(input));

        input = new int[]{7,2,4,1};
        Assert.assertEquals(2, maxProfit(input));

        input = new int[]{10,15,20, 5, 20, 0, 10};
        Assert.assertEquals(15, maxProfit(input));
    }
}
