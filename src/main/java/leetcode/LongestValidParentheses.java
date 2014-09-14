package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**

 Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

 For "(()", the longest valid parentheses substring is "()", which has length = 2.

 Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

 SOLUTION:

 Build array like this:

 in:                      ) ( ( ( ( ) ( ) ) (
 validParenthesesStartAt: 0 0 0 5 4 2 2 0 0 0

 in:                      ) ( ) ( ) )
 validParenthesesStartAt: 0 2 0 2 0 0

 build it left to right and reuse
 */

//

public class LongestValidParentheses {

    public int longestValidParentheses(String s) {
        int validParenthesesStartAt[] = new int[s.length()];
        int bestResult = 0;
        for (int startAt = s.length() - 2; startAt >= 0; startAt--) {
            if(s.charAt(startAt) == '(') {
                int longestNextToMe = validParenthesesStartAt[startAt+1];
                int nextCharAfterLongestID = startAt + validParenthesesStartAt[startAt+1] + 1;
                if (nextCharAfterLongestID < s.length() && s.charAt(nextCharAfterLongestID) == ')') {
                    validParenthesesStartAt[startAt] = longestNextToMe + 2;
                    //also add the result wich follows the current one
                    if(nextCharAfterLongestID + 1 < s.length()) validParenthesesStartAt[startAt] += validParenthesesStartAt[nextCharAfterLongestID+1];
                    bestResult = Math.max(bestResult, validParenthesesStartAt[startAt] );
                }
            }
        }
        //System.out.println(Arrays.toString(validParenthesesStartAt));
        return bestResult;
    }


    @Test
    public void test() {
        Assert.assertEquals(2, longestValidParentheses("(()"));
        Assert.assertEquals(4, longestValidParentheses(")()())"));

    }
}
