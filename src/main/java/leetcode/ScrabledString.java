package leetcode;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *
 *
 *
 */
public class ScrabledString {

    // string s is a scrambled string of another string if
    // it is equal to the target
    // or it's child nodes are scrambled strings
     public boolean isScramble(String s, String target) {
        if (s.length() <= 0 || s.length() != target.length()) return false;
        if (s.equals(target)) return true;

        char[]c1 = s.toCharArray();
        char[]c2 = target.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        if(!Arrays.equals(c1, c2)) return false;


        boolean isScrambled = false;

        for (int i = 0; i < s.length() - 1; i++) {
            int splitAt = i;
            String sLeft = s.substring(0, splitAt + 1);
            String sRight = s.substring(splitAt + 1, s.length());

            String targetLeft1 = target.substring(0, splitAt + 1);
            String targetRight1 = target.substring(splitAt + 1, target.length());

            String targetLeft2 = target.substring(0, target.length() - splitAt - 1);
            String targetRight2 = target.substring(target.length() - splitAt - 1, s.length());

            isScrambled = (isScramble(sLeft, targetLeft1) && isScramble(sRight, targetRight1));
            isScrambled = isScrambled || isScramble(sRight, targetLeft2) && isScramble(sLeft, targetRight2);
            if (isScrambled) break;
        }

        return isScrambled;
    }

    @Test
    public void test() {
        System.out.println("zeroarray="+"".toCharArray());
        System.out.println("zeroarray2="+Arrays.toString("".toCharArray()));
        Assert.assertFalse(isScramble("aa", "ab"));
        Assert.assertTrue(isScramble("ab", "ba"));
        Assert.assertTrue(isScramble("abc", "bca"));
        Assert.assertTrue(isScramble("abab", "abba"));
        Assert.assertTrue(isScramble("abb", "bab"));
        Assert.assertFalse(isScramble("pknsvxobynprj", "kobvyxnjrppsn"));
    }
}
