package corman.DynProg;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *
 *
 *
 */
public class LongestCommonSubstringOptimal {
    char[] seq1, seq2;

    public String getLongestCommonSubstring(String s1, String s2) {
        System.out.println("s1="+s1+" s2="+s2);
        int maxLen = 0;
        int maxI = 0;
        seq1 = s1.toCharArray();
        seq2 = s2.toCharArray();

        if(seq1.length == 0 || seq2.length == 0) return "";
        int[] prevRow = new int[s1.length()];


        // e.g. have "abbc" and "bb"
        // the following seqeunc or rows will have
        // combare "abbc" with b
        // [0 1 1 0]
        //combare "abbc" with second b and compare with previous result (current i with i-1)
        // [0 1 2 0]
        // best result is finishes at i = 2 (3rd element in array). length = 2 (value of 3rd element)

        for(int j = 0; j < seq2.length; j++) {
            int[] curRow = new int[s1.length()];
            for(int i = 0; i < seq1.length; i++) {
                if (seq1[i] == seq2[j]) {
                    if(i == 0) {
                        curRow[i] = 1;
                    }
                    else {
                        curRow[i] = prevRow[i - 1] + 1;
                    }
                    if (curRow[i] > maxLen) {
                        maxLen = curRow[i];
                        maxI = i;
                    }
                }
            }
            prevRow = curRow;
            System.out.println(Arrays.toString(curRow));
        }
        System.out.println("maxI="+maxI);
        System.out.println("maxLen="+maxLen);
        return s1.substring(maxI-maxLen+1, maxI+1);
    }


    @Test
    public void test() {
        Assert.assertEquals("", getLongestCommonSubstring("", ""));
        Assert.assertEquals("", getLongestCommonSubstring("", "x"));
        Assert.assertEquals("", getLongestCommonSubstring("x", ""));
        Assert.assertEquals("", getLongestCommonSubstring("aabcd", "x"));
        Assert.assertEquals("a", getLongestCommonSubstring("abc", "xax"));
        Assert.assertEquals("bb", getLongestCommonSubstring("abbc", "bb"));
        Assert.assertEquals("bb", getLongestCommonSubstring("bb", "xbbb"));
        Assert.assertEquals("CGGAA", getLongestCommonSubstring("ACCGGTCGAGTGCGCGGAAGCCGGCCGAA", "GTCGTTCGGAATGCCGTTGCTCTGTAAA"));
    }
}
