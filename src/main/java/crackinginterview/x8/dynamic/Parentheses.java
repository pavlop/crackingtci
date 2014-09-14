package crackinginterview.x8.dynamic;

import java.util.Stack;

/**
 * Implement an algorithm to print all valid (i.e., properly opened and closed)
 * combinations of n-pairs of parentheses.
 *
 * Let's consider the solution for n = 3:
((()))
(())()
()(())
()()()
(()())

 for n = 2:
 (())
 ()()

 *
 */
public class Parentheses {
    public static void printParenthes(Stack<Character> leftParenthes, Stack<Character> rightParenthes, Stack<Character> curResult){
        //all parenthes are used
        if (leftParenthes.size() == 0 && rightParenthes.size() == 0) {
            System.out.println("Printing result:"+curResult);
        }


        //try to print "(" in case there are parentheses of this type left
        if (leftParenthes.size() > 0) {
            curResult.push(leftParenthes.pop());
            printParenthes(leftParenthes, rightParenthes, curResult);
            leftParenthes.push('(');
            curResult.pop();
        }

        // try to print "(" in case there are parentheses of this type left
        // and if there are more "(" used then ")"
        if (rightParenthes.size() > 0 && rightParenthes.size() > leftParenthes.size()) {
            curResult.push(rightParenthes.pop());
            printParenthes(leftParenthes, rightParenthes, curResult);
            rightParenthes.push(')');
            curResult.pop();
        }

    }

    public static void main (String args[]) {
        Stack<Character> leftParenthes = new Stack<Character>();
        leftParenthes.push('(');
        leftParenthes.push('(');
        leftParenthes.push('(');
        Stack<Character> rightParenthes = new Stack<Character>();
        rightParenthes.push(')');
        rightParenthes.push(')');
        rightParenthes.push(')');
        printParenthes(leftParenthes, rightParenthes, new Stack<Character>());
    }


}
