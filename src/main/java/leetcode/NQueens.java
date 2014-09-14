package leetcode;

import junit.framework.Assert;
import org.junit.Test;

import java.util.*;

/**
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 *
 *
 */
public class NQueens {
    List<List<Integer>> globalQueensPositions = new ArrayList<List<Integer>> ();
    int nMask = 0;

    public List<String[]> solveNQueens(int n) {
        nMask = (1 << (n)) - 1;
        globalQueensPositions.clear();
        solveNQueens(n, 0, 0, 0, new ArrayList<Integer>());

        List<String[]> result = new ArrayList<String[]>();
        for (List<Integer> queens : globalQueensPositions) {
            String[] curRes = new String[n];
            for (int i = 0; i < n; i++) {
                curRes[i] = makeDots(n).replace(queens.get(i) , queens.get(i)+1, "Q").toString();
            }
            result.add(curRes);
        }
        return result;
    }

    public void solveNQueens(int n, long lefts, long downs, long rights, List<Integer> curPositions) {
        if(curPositions.size() == n) {
            globalQueensPositions.add(curPositions);
            return;
        }

        for (int i = 0; i < n; i++) {
            int curBIt = 1 << i;

            if(isFree(curBIt, lefts, downs, rights)) {
                List<Integer> newPositions = new ArrayList<Integer>(curPositions);
                newPositions.add(i);
                long newlefts = lefts | curBIt;
                long newdowns = downs | curBIt;
                long newrights= rights | curBIt;
                solveNQueens(n, newlefts<<1, newdowns, newrights>>1, newPositions);
            }
        }
    }

    boolean isFree(long curBIt, long lefts, long downs,  long rights) {
        return ((curBIt & lefts) == 0) && ((curBIt & downs) == 0) && ((curBIt & rights) == 0);
    }

    StringBuilder makeDots(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(".");
        return sb;
    }

    @Test
    public void test() {
        Assert.assertEquals(1, solveNQueens(1).size());
        Assert.assertEquals(0, solveNQueens(2).size());
        Assert.assertEquals(0, solveNQueens(3).size());
        Assert.assertEquals(2, solveNQueens(4).size());
        Assert.assertEquals(92, solveNQueens(8).size());
//        System.out.println(solveNQueens(7));
    }
}
