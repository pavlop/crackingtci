package leetcode;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 *
 */
public class PalindromePartitioningSmallest {

    public Map<String, List<String>> memory = new HashMap<String, List<String>>();

    public int minCut(String s) {
        System.out.println(minPartition(s));
        memory.clear();
        return minPartition(s).size() - 1;
    }

    public List<String> minPartition(String s) {
        if(memory.containsKey(s)) return memory.get(s);

        List<String> result = new ArrayList<String>();
        // if entre string is palindrome - we add it to the result of current level and return
        if(isPalindrome(s)) {
            result.add(s);
            memory.put(s, result);
            return result;
        }

        // starting from first chart split the string
        // if left and right substrings are a palindrome return the result
        for (int i = 0; i < s.length() ; i++) {
            String sLeft = s.substring(0, i + 1);
            String sRight = s.substring(i + 1, s.length());
            if(isPalindrome(sLeft) && isPalindrome(sRight)) {
                result.add(sLeft);
                result.add(sRight);
                memory.put(s, result);
                return result;
            }
        }

        int bestRightLength = Integer.MAX_VALUE;
        String bestLeft = "";
        List<String> bestRightResult = null;
        for (int i = 0; i < s.length() ; i++) {
            String sLeft = s.substring(0, i + 1);
            String sRight = s.substring(i + 1, s.length());
            if (isPalindrome(sLeft)) {
                List<String> rightResult = minPartition(sRight);
                if (rightResult.size() < bestRightLength) {
                    bestRightResult = rightResult;
                    bestLeft = sLeft;
                    bestRightLength = rightResult.size();
                }
            }
        }
        result.add(bestLeft);
        result.addAll(bestRightResult);
        memory.put(s, result);
        return result;
    }

    boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    @Test
    public void test() {
        Assert.assertEquals(1, minCut("aab"));
        Assert.assertEquals(2, minCut("abc"));
        Assert.assertEquals(3, minCut("ccaacabacb"));
        Assert.assertEquals(75, minCut("fifgbeajcacehiicccfecbfhhgfiiecdcjjffbghdidbhbdbfbfjccgbbdcjheccfbhafehieabbdfeigbiaggchaeghaijfbjhi"));
    }
}
