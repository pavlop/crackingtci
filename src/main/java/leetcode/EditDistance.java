package leetcode;

import junit.framework.Assert;
import org.junit.Test;

import java.util.*;

/**
 *
 *
 *
 */
public class EditDistance {
    int globalMin;
    public int minDistance(String word1, String word2) {
         globalMin = Math.max(word1.length(), word2.length());
        List<Character> w1 = new ArrayList<Character>();
        for(char c:word1.toCharArray()) w1.add(c);
        minDistance(w1, 0, word2, 0);
        return globalMin;
    }

    public void minDistance (List<Character> word1, int p1, String word2, int curStpeps) {
        //skip cases which are werese then we already have
        if(curStpeps >= globalMin) return;

        // if(word1.size() == 0 && word2.length() > 0) {
        //     globalMin = Math.min(curStpeps, globalMin);
        //     return;
        // }

        if(p1 == word2.length()) {
            globalMin = Math.min(globalMin, curStpeps + Math.abs(word2.length() - word1.size()));
            return;
        }

        if(p1 < word1.size()) {

            //if the same leeters, go ahead
            if(word1.get(p1).charValue() == word2.charAt(p1)) {
                minDistance (word1, p1 + 1, word2, curStpeps);
                return;
            }

            //insertBefore
            List<Character> insertWord = new ArrayList<Character>(word1);
            insertWord.add(p1, word2.charAt(p1));
            minDistance (insertWord, p1 + 1, word2, curStpeps+1);

            if(word1.size() > 0) {

                //replace
                List<Character> replacedWord = new ArrayList<Character>(word1);
                replacedWord.remove(p1);
                replacedWord.add(p1, word2.charAt(p1));
                minDistance (replacedWord, p1 + 1, word2,curStpeps+1);

                //remove
                List<Character> removedWord = new ArrayList<Character>(word1);
                removedWord.remove(p1);
                removedWord.add(p1, word2.charAt(p1));
                minDistance (removedWord, p1, word2, curStpeps+1);

            }
        }
    }

    @Test
    public void tests() {
        Assert.assertEquals(0, minDistance("a", "a"));
        Assert.assertEquals(1, minDistance("a", "b"));
        Assert.assertEquals(2, minDistance("aa", "bb"));
        Assert.assertEquals(2, minDistance("ab", "ba"));
        Assert.assertEquals(2, minDistance("abc", "cba"));
        Assert.assertEquals(10, minDistance("aaaaaaaaaa", "bbbbbbbbbb"));

        Assert.assertEquals(7, minDistance("dinitro_phenylhydra", "acetyl_phenylhydra"));
    }
}
