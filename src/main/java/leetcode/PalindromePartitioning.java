package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 Given a string s, partition s such that every substring of the partition is a palindrome.

 Return all possible palindrome partitioning of s.

 For example, given s = "aab",
 Return

 [
   ["aa","b"],
   ["a","a","b"]
 ]

 */
public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();

        // if entre string is palindrome - we add it to the result of current level
        if(isPalindrome(s)) {
            List<String> levelSolution = new ArrayList<String>();
            if(s.length() > 0) levelSolution.add(s);
            result.add(levelSolution);
        }

        // starting from first chat split the string
        // if left substring is a palindrome
        // then we need to "multiply" it to all the palindromes of the right part
        for (int i = 0; i < s.length() - 1 ; i++) {
            String sLeft = s.substring(0, i + 1);
            String sRight = s.substring(i + 1, s.length());

            if(isPalindrome(sLeft)) {
                List<List<String>> rightResult = partition(sRight);
                cortesianProduct(sLeft, rightResult);
                result.addAll(rightResult);
            }
        }
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

    public void cortesianProduct(String left, List<List<String>> rightResult) {
        for(List<String> lsit:rightResult) {
            lsit.add(0, left);
        }
    }
}
