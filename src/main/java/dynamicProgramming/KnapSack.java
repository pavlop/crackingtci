package dynamicProgramming;

import junit.framework.Assert;
import org.junit.Test;

/**
 *
 *
 *
 */
public class KnapSack {

    int calculateBestValue(int[] values, int[] weights, int maxWeight) {
        int[][] bestValues = new int[values.length+1][maxWeight+1];

        for (int itemID = 1; itemID < bestValues.length; itemID++) {
            for (int curWeight = 1; curWeight < bestValues[0].length; curWeight++) {

                // if we skip, we take value for n-1 elements and the same weight
                int valueIfSkipped = bestValues[itemID-1][curWeight];

                // can only skip current item as it is too big
                if (weights[itemID-1] > curWeight) {
                    bestValues[itemID][curWeight] = valueIfSkipped;
                } else {
                    int valueIfIncluded = values[itemID-1] + bestValues[itemID-1][curWeight-weights[itemID-1]];

                    // choose between adding this item or skipping it
                    bestValues[itemID][curWeight] = Math.max(valueIfIncluded, valueIfSkipped);
                }
            }
        }
        return bestValues[values.length][maxWeight];
    }

    @Test
    public void test() {
        int[] w;
        int[] v;
        w = new int[] {1, 3, 4, 7};
        v = new int[] {3, 6, 8, 12};

        Assert.assertEquals(18, calculateBestValue(v, w, 10));
        Assert.assertEquals(23, calculateBestValue(v, w, 12));
    }
}
