package leetcode;
import java.util.*;

/**
 *
 *Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 For example, given n = 3, a solution set is:

 "((()))", "(()())", "(())()", "()(())", "()()()"
 *
 */
public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> r = new ArrayList<String>();
        generateParenthesis(n*2, 0, 0, new StringBuilder(), r);
        return r;
    }

    public static void generateParenthesis(int total, int nOpen, int nClosing, StringBuilder curStr,  List<String> result) {

        if(nOpen + nClosing == total) {
            if(nOpen == nClosing) result.add(curStr.toString());
            return;
        }

        if(nOpen > nClosing) {
            generateParenthesis (total, nOpen+1, nClosing, (new StringBuilder(curStr)).append("("),  result);
            generateParenthesis (total, nOpen, nClosing+1, (new StringBuilder(curStr)).append(")"),  result);
        } else {
            generateParenthesis (total, nOpen+1, nClosing, (new StringBuilder(curStr)).append("("),  result);
        }
    }
}
