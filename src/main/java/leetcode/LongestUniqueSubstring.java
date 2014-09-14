package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring
 * without repeating characters.
 *
 * For example, the longest substring
 * without repeating letters for "abcabcbb" is "abc", which the length is 3.
 *
 * For "bbbbb" the longest substring is "b", with the length of 1.
 *
 * http://www.programcreek.com/2013/02/leetcode-longest-substring-without-repeating-characters-java/
 */
public class LongestUniqueSubstring {

    public static int lengthOfLongestSubstring(String s) {
        //storage of current substring
        HashSet<Character> usedChars = new HashSet<Character>();

        // store longest substring length
        // value is updated when we find a duplicate char
        // or at the end of the program
        int maxLenCount = 0;

        //this is where current substring starts
        // value is updated when we find a duplicate char
        int j = 0;
        char[] str = s.toCharArray();

        for (int i = 0; i < str.length; i++) {
            char curChar = str[i];

            if (usedChars.contains(curChar)) {
                // if we found a duplicate char
                // we need to remeber current str length
                // we also need to remove all chars before before the first occurrence of "curChar"
                maxLenCount = Math.max(maxLenCount, i - j);
                for (int k = j; k < i; k++) {
                    if (str[k] == curChar) {
                        j = k + 1;
                        break;
                    }
                    usedChars.remove(str[k]);
                }
            } else {
                usedChars.add(curChar);
            }
        }

        maxLenCount=Math.max(str.length-j,maxLenCount);
        return maxLenCount;
    }
}
