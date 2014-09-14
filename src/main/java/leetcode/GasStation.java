package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *
 *
 *
 */
public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas.length == 1) {
            return gas[0] >= cost[0] ? 0 : -1;
        }
        int minimalSum = gas[0] - cost[0];
        int totalSumGasAndCosts = minimalSum;
        int minTransitionId = 0;

        for (int i = 1; i < gas.length; i++) {
            int curTransition =  gas[i] - cost[i];

            totalSumGasAndCosts += curTransition;
            if (minimalSum > totalSumGasAndCosts) {
                minimalSum = totalSumGasAndCosts;
                minTransitionId = i;
            }
        }
        if(totalSumGasAndCosts >= 0 ) return minTransitionId+1;
        else return -1;
    }

    @Test
    public void test() {
        int[] gas = {0, 3, 10, 5};
        int[] cost = {1, 5, 3, 7};
        Assert.assertEquals(2, canCompleteCircuit(gas, cost));

        gas = new int[] {67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66};
        cost = new int[] {27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};
        Assert.assertEquals(74, canCompleteCircuit(gas, cost));

        gas = new int[] {2};
        cost = new int[] {2};
        Assert.assertEquals(0, canCompleteCircuit(gas, cost));

    }
}
