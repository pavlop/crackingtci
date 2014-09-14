package leetcode;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

 For example, given the following triangle
 [
 [2],
 [3,4],
 [6,5,7],
 [4,1,8,3]
 ]
 The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

 Note:
 Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 *
 *
 * Leetcode # Triangle
 */


// we can see that i element of cur level is connected with i and i+1 for the next level
// Best solution of current level at position i :
// min (element(i) + min (previousBest(i),  previousBest(i+1))

public class TriangleLesatPath {
    public int minimumTotal(List<List<Integer>> triangle) {
        //best solutions at the last level are the elements themselves
        List<Integer> bestOfPreviousLevel = triangle.get(triangle.size()-1);

        //go from the bottom to the up
        for (int lvl = triangle.size() - 2; lvl >= 0; lvl --) {
            List <Integer> curLevel = triangle.get(lvl);
            List<Integer> bestOfCurLevel = new ArrayList<Integer>();

            //fill best solutions of cur level
            for (int i = 0; i < curLevel.size(); i++) {
                bestOfCurLevel.add(curLevel.get(i)+Math.min(bestOfPreviousLevel.get(i), bestOfPreviousLevel.get(i+1)));
            }
            bestOfPreviousLevel = bestOfCurLevel;
        }
        return bestOfPreviousLevel.get(0);
    }

    @Test
    public void test() {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3,4));
        System.out.println(triangle);
        Assert.assertEquals(5, minimumTotal(triangle));
    }

}
