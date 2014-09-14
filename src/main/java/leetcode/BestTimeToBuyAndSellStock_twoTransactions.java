package leetcode;

/**
 *
 *
 *
 */
public class BestTimeToBuyAndSellStock_twoTransactions {
      /*
    We are actually trying to break the day at each time instance,
    by adding the potential max profit before and after it together.

    Best result to the left in point X is one of:
      1) (current - minLeft) or (est result to the left in point X - 1)
      2) similar to left

    */

    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len <= 1) return 0;
        int[] bestResutlFromZeroToNow = new int[len];
        int[] bestResutlFromNowToEnd = new int[len];
        bestResutlFromZeroToNow[0] = 0;
        bestResutlFromZeroToNow[len - 1] = 0;
        int minLeft  = prices[0];
        int maxRight = prices[len - 1];

        for (int i = 1; i < len; i++) {
            int j = len - i - 1;
            bestResutlFromZeroToNow[i] = Math.max (prices[i] - minLeft, bestResutlFromZeroToNow[i-1]);
            bestResutlFromNowToEnd[j] = Math.max (maxRight - prices[j], bestResutlFromNowToEnd[j+1]);
            minLeft = Math.min(minLeft, prices[i]);
            maxRight = Math.max(maxRight, prices[j]);
        }

        int bestResult = 0;
        for (int i = 0; i < len ; i++) {
            bestResult = Math.max(bestResult, bestResutlFromZeroToNow[i]+bestResutlFromNowToEnd[i]);
        }
        return bestResult;
    }
}
