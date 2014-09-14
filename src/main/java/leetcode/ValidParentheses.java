package leetcode;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**

 Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

 */
public class ValidParentheses {
    public boolean isValid(String s) {

        Map<Character, Character> parenthesesPairs = new HashMap<Character, Character>();
        parenthesesPairs.put(')', '(');
        parenthesesPairs.put('}', '{');
        parenthesesPairs.put(']' , '[');

        Stack<Character> openParenthes = new Stack<Character>();
        for (Character c : s.toCharArray()) {
            // if this is closing Parenthes
            if (parenthesesPairs.containsKey(c)) {
                if (openParenthes.isEmpty() || !openParenthes.pop().equals(parenthesesPairs.get(c))) return false;
            } else {
                //have opening Parenthes, put in in the stack
                openParenthes.push(c);
            }
        }
        //should be empty at the end
        return openParenthes.isEmpty();
    }
}
