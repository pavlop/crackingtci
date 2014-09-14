package leetcode;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 *
 */
public class LongestPolyndromicSubstring {

    public String longestPalindrome(String s) {

        Set<Integer> mirrorPositions = new HashSet<Integer>();
        mirrorPositions.add(0);

        boolean[] isPolyndromContainsTheSame = new boolean[s.length()];
        int biggestLen = 0;
        String res = "" + s.charAt(0);
        isPolyndromContainsTheSame[0] = true;

        for(int i = 1; i < s.length(); i++) {
            isPolyndromContainsTheSame[i] =  isPolyndromContainsTheSame[i-1] && (s.charAt(i) == s.charAt(i-1));
            Set<Integer> nextMirrorPositions = new HashSet<Integer>();
            if(!isPolyndromContainsTheSame[i]) {
                mirrorPositions.add((i));
                if (i - 1 >= 0) mirrorPositions.add((i - 1));
            }

            for (Integer prevMirrorPosition : mirrorPositions) {
                if (prevMirrorPosition > 0 && s.charAt(prevMirrorPosition - 1) == s.charAt(i)) nextMirrorPositions.add(prevMirrorPosition - 1);
                if (isPolyndromContainsTheSame[i] && s.charAt(i - 1) == s.charAt(i)) nextMirrorPositions.add(prevMirrorPosition);
            }

            for (Integer curMirrorPositions:nextMirrorPositions) {
                int curLen = i - curMirrorPositions;
                if (curLen >= biggestLen) {
                    res = s.substring(curMirrorPositions, i+1);
                    biggestLen = curLen;
                }
            }
            mirrorPositions = nextMirrorPositions;
        }
        return res;
    }

    @Test
    public void test() {

        Assert.assertEquals("a", longestPalindrome("a"));
        Assert.assertEquals("xabax", longestPalindrome("xabax"));
        Assert.assertEquals("aaa", longestPalindrome("aaa"));
        Assert.assertEquals("aa", longestPalindrome("aa"));
        Assert.assertEquals("aaaa", longestPalindrome("aaaa"));
        Assert.assertEquals("anana", longestPalindrome("bananas"));
        Assert.assertEquals("ababa", longestPalindrome("ababa"));
        Assert.assertEquals("abababa", longestPalindrome("abababa"));
//
        String s1 = "aaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaa";

        Assert.assertEquals(s1, longestPalindrome(s1));

    }

}
