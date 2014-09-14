package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 *
 Given:
 start = "123"
 end = "323"
 dict = ["121","222","322","223","321", "323"]
 As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 return its length 5.


 SOLUTION:

            123
        121    223





 */
public class WordLadderBFS {

    public int ladderLength(String start, String end, Set<String> dict) {
        Set<String> visited = new HashSet<String>();
        int level = 1;
        Set<String> curelevel = new HashSet<String>();
        curelevel.add(start);

        while (!curelevel.isEmpty()) {
            Set<String> nextLevel = new HashSet<String>();
            for (String curStr:curelevel) {
                if(curStr.equals(end)) return level;
                if (!visited.contains(curStr)) {
                    visited.add(curStr);
                    nextLevel.addAll(getPossibleMutations(curStr, dict));
                }
            }
            curelevel = nextLevel;
            level++;
        }
        return 0;
    }

    Set<String> getPossibleMutations(String start, Set<String> dict) {
        Set<String> result = new HashSet<String>();
        for (int i = 0;  i < start.length(); i++) {
            char[] currCharArr = start.toCharArray();
            for (char c='a'; c<='z'; c++) {
                currCharArr[i] = c;
                String newWord = new String(currCharArr);
                if(dict.contains(newWord)) result.add(newWord);
            }
        }
        return result;
    }

//    Set<String> getPossibleMutations(String start, Set<String> dict) {
//        Set<String> result = new HashSet<String>();
//        for (String dic:dict) {
//            int diffCounter = 0;
//            for (int i = 0; i<dic.length(); i++) {
//                if (start.charAt(i) != dic.charAt(i)) {
//                    diffCounter++;
//                }
//            }
//            if (diffCounter == 1) result.add(dic);
//        }
//        System.out.println("start="+start+" result="+result);
//        return result;
//    }


    @Test
    public void test() {

        Set<String> dict = new HashSet<String>();
        dict.addAll(Arrays.asList(new String[]{"hot","cog","dot","dog","hit","lot","log"}));
        Assert.assertEquals(5, ladderLength("hit", "cog", dict));


    }
}
