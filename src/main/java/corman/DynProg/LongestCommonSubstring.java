package corman.DynProg;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 *
 */
public class LongestCommonSubstring {
    char[] seq1, seq2;
    Map<String, Integer> longestSubsq = new HashMap<String, Integer>();
    int bestSeq1StartAt;
    int bestLenght;
    public String getLongestCommonSubequence(String s1, String s2) {
        longestSubsq.clear();
        seq1 = s1.toCharArray();
        seq2 = s2.toCharArray();
        bestSeq1StartAt = 0;
        bestLenght = 0;

        for(int i = 0; i < seq1.length; i++) {
            for(int j = 0; j < seq2.length; j++) {
               longestSeqStartingAt(i, j);
            }
        }
        System.out.println(longestSubsq);
        return s1.substring(bestSeq1StartAt, bestSeq1StartAt+bestLenght);
    }

    public int longestSeqStartingAt(int startAtSeq1, int startAtSeq2) {
        if(startAtSeq1 == seq1.length || startAtSeq2 == seq2.length) return 0;
        String key = startAtSeq1+"_"+startAtSeq2;
        if(longestSubsq.containsKey(key)) return longestSubsq.get(key);

        int res;
        if(seq1[startAtSeq1] == seq2[startAtSeq2])
            res= 1+longestSeqStartingAt(startAtSeq1+1, startAtSeq2+1);
        else
            res = 0;
        longestSubsq.put(key, res);
        if(res > bestLenght) {
            bestSeq1StartAt = startAtSeq1;
            bestLenght = res;
        }
        return res;
    }

    @Test
    public void test() {
        Assert.assertEquals("", getLongestCommonSubequence("", ""));
        Assert.assertEquals("", getLongestCommonSubequence("", "x"));
        Assert.assertEquals("", getLongestCommonSubequence("x", ""));
        Assert.assertEquals("", getLongestCommonSubequence("aabcd", "x"));
        Assert.assertEquals("a", getLongestCommonSubequence("aabcd", "a"));
        Assert.assertEquals("CGGAA", getLongestCommonSubequence("ACCGGTCGAGTGCGCGGAAGCCGGCCGAA", "GTCGTTCGGAATGCCGTTGCTCTGTAAA"));
    }
}
