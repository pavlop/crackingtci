package leetcode;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**


 '.' Matches any single character.
 '*' Matches zero or more of the preceding element.

 The matching should cover the entire input string (not partial).

 The function prototype should be:
 bool isMatch(const char *s, const char *p)

 Some examples:
 isMatch("aa","a") → false
 isMatch("aa","aa") → true
 isMatch("aaa","aa") → false
 isMatch("aa", "a*") → true
 isMatch("aa", ".*") → true
 isMatch("ab", ".*") → true
 isMatch("aab", "c*a*b") → true


 *
 */
public class RegularExpressionMatching {

    public boolean isMatch(String s, String mask) {
        if(mask.length()==0 && s.length() > 0) return false;
        return isMatch(s, s.length(), mask, mask.length());
    }

    public boolean isMatch(String s, int sLen, String mask, int maskLen) {
        if (maskLen <= 0) {
            return sLen <= 0;
        }

        char curMaskChar = mask.charAt(maskLen-1);
        switch (curMaskChar) {
            case '.':
                return sLen>0 && isMatch(s, sLen-1, mask,maskLen-1);
            case '*':
                //case when char before * is zero times
                if(isMatch(s, sLen, mask,maskLen-2)) return true;
                char prevChar  = mask.charAt(maskLen-2);
                for (int i = sLen-1; i >= 0 && (prevChar == s.charAt(i) || prevChar=='.'); i--) {
                    if(isMatch(s, i, mask,maskLen-2)) return true;
                }
                return false;
            default:
                if(maskLen <= 0 || sLen <= 0) return false;
                if (mask.charAt(maskLen-1) != s.charAt(sLen-1)) return false;
                else return isMatch(s, sLen-1, mask,maskLen-1);
        }
    }

    @Test
    public void doTest() {
        assertTrue(isMatch("aa","aa"));
        assertTrue(isMatch("aa", "a*"));
        assertTrue(isMatch("aa", ".*"));
        assertTrue(isMatch("ab", ".*"));
        assertTrue(isMatch("aab", "c*a*b"));

        assertFalse(isMatch("aa","a"));
        assertFalse(isMatch("aaa","aa"));
        assertFalse(isMatch("bbaa","abc"));

        assertFalse(isMatch("a",""));
        assertFalse(isMatch("","a"));

        assertTrue(isMatch("baccbbcbcacacbbc","c*.*b*c*ba*b*b*.a*"));
        assertTrue(isMatch("", ".*"));
        assertFalse(isMatch("", "."));

    }
}
