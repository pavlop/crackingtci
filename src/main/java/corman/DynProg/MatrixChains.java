package corman.DynProg;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * For instance :
 * A[10x30]
 * B[30x100]
 * C[100x5]
 *
 * (A*B)*C:
 * res: 10X100X30 = 30.000 to get A*B [10x30]
 *      10X100X5 = 5.000 to get (A*B)*C [10X5]
 * Total: 35.000
 *
 * A*(B*C):
 * res: 30X100X5 = 15000 to get B*C[30X5]
 *      10x30x5 = 1500
 * Total: 16.500
 */
public class MatrixChains {
    // chain = [10, 30, 100, 5, 1]
    // means 4 matrixes:
    // 10x30, 30x100, 100x5, 5x1
    public void makeParenthesesForMatrixChain(LinkedList<Integer> chain) {
        System.out.println(getBestMultiplicationTimesRec(chain));
    }

    public int getBestMultiplicationTimesRec(LinkedList<Integer> chain) {
        if (chain.size() <= 2) return 0;
        int minOperations = Integer.MAX_VALUE;
        for (int i = 0; i < chain.size() - 2; i++) {
            //Multiply matrixes [i i+1] with [i+1 i+2]

            LinkedList<Integer> newChain = new LinkedList<Integer> (chain);
            Integer firstLength = newChain.get(i);
            Integer firstHeight = newChain.get(i+1);
            Integer seconHeight = newChain.get(i+2);
            newChain.remove(i+1);

            int curOperations = firstLength*firstHeight*seconHeight;
            curOperations += getBestMultiplicationTimesRec(newChain);
            if(minOperations > curOperations) {
                minOperations = Math.min(minOperations, curOperations);
            }
        }
        return minOperations;
    }

    @Test
    public void test() {
        LinkedList<Integer> chain = new LinkedList<Integer> ();
        chain.add(10);
        chain.add(30);
        chain.add(100);
        chain.add(5);
        makeParenthesesForMatrixChain(chain);


        //30􏰒35 35􏰒15 15􏰒5 5􏰒10 10􏰒20 20􏰒25
        chain = new LinkedList<Integer> ();
        chain.add(30);
        chain.add(35);
        chain.add(15);
        chain.add(5);
        chain.add(10);
        chain.add(20);
        chain.add(25);
        makeParenthesesForMatrixChain(chain);
    }
}
