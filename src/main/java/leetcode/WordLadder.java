package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 Given two words (start and end), and a dictionary, find the length of shortest transformation sequence
 from start to end, such that:

  - Only one letter can be changed at a time
  - Each intermediate word must exist in the dictionary

 For example,

 Given:
 start = "hit"
 end = "cog"
 dict = ["hot","dot","dog","lot","log"]
 As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 return its length 5.

 Note:
 Return 0 if there is no such transformation sequence.
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 */
public class WordLadder {
    Map<String, Integer> alreadyVisited = new HashMap<String, Integer>();
    int minCount;

    public int ladderLength(String start, String end, Set<String> dict) {
        minCount = Integer.MAX_VALUE;
        alreadyVisited.clear();
        ladderLength( start,  end, dict, 1);
        if (minCount == Integer.MAX_VALUE) return 0;
        return minCount;
    }

    public void ladderLength(String start, String end, Set<String> dict, int count) {
        if (start.equals(end)) {
            minCount = Math.min(minCount, count);
            return;
        }

        Integer countSaved = alreadyVisited.get(start);
        if(countSaved != null && countSaved <= count) {
            return;
        }

        alreadyVisited.put(start, count);


        for (int i = 0; i < start.length(); i++) {
            for (char c = 'a'; c<='z'; c++) {
                StringBuilder newStrB = new StringBuilder(start);
                newStrB.replace(i, i+1, ""+c);
                String newStr = newStrB.toString();
                if(dict.contains(newStr)) {
                    ladderLength(newStr, end, dict, count+1);
                }
            }
        }
    }

    @Test
    public void test() {

        Set<String> dict = new HashSet<String>();
        dict.addAll(Arrays.asList(new String[]{"hot","cog","dot","dog","hit","lot","log"}));
        Assert.assertEquals(5, ladderLength("hit", "cog", dict));


    }
}
