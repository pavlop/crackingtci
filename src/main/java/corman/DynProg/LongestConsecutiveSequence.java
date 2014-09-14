package corman.DynProg;

import junit.framework.Assert;
import org.junit.Test;

import java.util.*;

/**
 *Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

 For example,
 Given [100, 4, 200, 1, 3, 2],
 The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

 Your algorithm should run in O(n) complexity. *
 */
public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] num) {
        // Add everything to a set
        // then start form a random number in a set - remove the
        // connected areas from the set, while calculating the size of the area
        Set<Integer> allNumbers = new HashSet<Integer>();
        for(int x : num) allNumbers.add(x);
        int longestConsecutiveSize = 0;

        while (!allNumbers.isEmpty()) {
            Integer randomInt = allNumbers.iterator().next();
            allNumbers.remove(randomInt);

            List<Integer> currentConsecutiveArea = new ArrayList<Integer>();
            currentConsecutiveArea.add(randomInt);

            int curConsecutiveSize = 0;
            while (!currentConsecutiveArea.isEmpty()) {
                int midNum = currentConsecutiveArea.remove(0);
                curConsecutiveSize++;
                if (allNumbers.contains(midNum-1)) {
                    currentConsecutiveArea.add(midNum-1);
                    allNumbers.remove(midNum-1);
                }
                if (allNumbers.contains(midNum+1)) {
                    currentConsecutiveArea.add(midNum+1);
                    allNumbers.remove(midNum+1);
                }
            }
            longestConsecutiveSize = Math.max(longestConsecutiveSize, curConsecutiveSize);
        }
        return longestConsecutiveSize;
    }


    @Test
    public void test() {
        int[] num;

        num = new int[]{100, 4, 200, 1, 3, 2};
        Assert.assertEquals(4, longestConsecutive(num));
    }
}
