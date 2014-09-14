package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000,
 * and there exists one unique longest palindromic substring.
 *
 * O(N) solution : http://leetcode.com/2011/11/longest-palindromic-substring-part-ii.html
 *
 * O(N) solution implemented below
 */
public class LongestPolyndromicSubstringUNRESOLVED {

    //make form "abaaba" -> "#a#b#a#a#b#a#" so that thre is always central element
    public static String preProcess(String s) {
        if (s.length() == 0) return "";

        StringBuffer modifiedStr = new StringBuffer();

        for (int i = 0; i < s.length(); i++) {
            modifiedStr.append(s.charAt(i)).append("#");
        }
        modifiedStr.insert(0, "#");
        System.out.println("return preProcess:"+modifiedStr.toString());
        return modifiedStr.toString();
    }

    public String longestPalindrome(String s) {
        char[] T = preProcess(s).toCharArray();

        int P[] = new int[T.length];
        int C = 0;
        int R = 0;

        for (int i = 1; i < T.length-1; i++) {
            int iMirror = 2*C-i; // equals to i' = C - (i-C)

            P[i] = (R > i) ? Math.min(R - i, P[iMirror]) : 0;

            // Attempt to expand palindrome centered at i
            System.out.println("i="+i + " P[i]="+P[i]);
            while ((i - 1 - P[i]) > 0 && (T[i + 1 + P[i]] < T.length) && (T[i + 1 + P[i]] == T[i - 1 - P[i]]))
                P[i]++;

            // If palindrome centered at i expand past R,
            // adjust center based on expanded palindrome.
            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }
        }

        // Find the maximum element in P.
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i <  T.length-1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }
        return s.substring((centerIndex -1 - maxLen)/2, (centerIndex  + maxLen)/2);
    }



    @Test
    public void doTest() {
        String inp = "asuasddsaioup";
        assertEquals("asddsa", longestPalindrome(inp));

        inp = "asu0asdsaioup";
        assertEquals("asdsa", longestPalindrome(inp));

        inp = "app";
        assertEquals("pp", longestPalindrome(inp));
        //inp = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        //assertEquals(inp, longestPalindrome(inp));
    }
}
