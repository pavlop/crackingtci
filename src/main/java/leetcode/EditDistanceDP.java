package leetcode;

import junit.framework.Assert;
import org.junit.Test;

/**
 *
 *
 *
 */
public class EditDistanceDP {

    public int minDistance(String word1, String word2) {
        if(word1.length() == 0 || word2.length() == 0 ) return Math.max(word1.length(), word2.length());

        int[][] costs = new int[word1.length()][word2.length()];
        costs[0][0] = sameChar(word1, word2, 0, 0) ? 0 : 1;

        // fill first column
        for (int i = 1; i < word1.length(); i++) costs[i][0] = Math.min(i + (sameChar(word1, word2, i, 0) ? 0 : 1), costs[i-1][0] + 1);
        for (int i = 1; i < word2.length(); i++) costs[0][i] = Math.min(i + (sameChar(word1, word2, 0, i) ? 0 : 1), costs[0][i-1] + 1);

        for (int i = 1;  i < word1.length(); i++) {
            for (int j = 1;  j < word2.length(); j++) {
                int substitute = (word1.charAt(i) == word2.charAt(j)) ? 0 : 1;
                substitute += costs[i-1][j-1];
                costs[i][j] = min(substitute, costs[i-1][j]+1, costs[i][j-1]+1);
            }
        }
        return costs[word1.length() - 1][word2.length() - 1];
    }

    public static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public boolean sameChar(String s1, String s2, int i, int j) {
        //if(i >= s1.length() || j >= s2.length()) return false;
        return s1.charAt(i) == s2.charAt(j);
    }

    @Test
    public void test() {
        Assert.assertEquals(2, minDistance("sea", "eat"));
        Assert.assertEquals(3, minDistance("abcXXX", "XXX"));
        Assert.assertEquals(3, minDistance("umoumio", "umio"));

        // common: ultramicroscopic
        // pneumonoultramicroscopicsilicovolcanoconiosis -> 8 pneumonosilicovolcanoconiosis 21
        Assert.assertEquals(27, minDistance("pneumonoultramicroscopicsilicovolcanoconiosis", "ultramicroscopically"));

    }
}
