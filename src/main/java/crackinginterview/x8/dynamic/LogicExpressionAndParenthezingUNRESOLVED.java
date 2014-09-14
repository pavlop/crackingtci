package crackinginterview.x8.dynamic;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Given a boolean expression consisting of the symbols
 * 0,1, &, |, and ^, and a desired boolean result value result,
 * implement a function to count the number of ways of parenthesizing
 * the expression such that it evaluates to resuLt.
 */

//WE can only place ( after a special symobl and ) only after a number
public class LogicExpressionAndParenthezingUNRESOLVED {
    public static boolean printParenthezedExpression(char[] inputExpr, int desiredRes, StringBuffer curSolution) {
        System.out.println("inputExpr:"+new String(inputExpr));

        if (evaluateExpressionNoParentheses(inputExpr) == desiredRes) {
            curSolution.append('(').append(inputExpr).append(')');
            System.out.println("SUCCESS"+curSolution.toString());
            return true;
        }

        //operator on every second position
        for(int i = 1; i < inputExpr.length; i+=2) {
            char[] subexpr1 = Arrays.copyOfRange(inputExpr, 0, i );
            char[] subexpr2 = Arrays.copyOfRange(inputExpr, i + 1, inputExpr.length);

            char operator = inputExpr[i];
            if(operator == '&') {
                boolean res1 = printParenthezedExpression(subexpr1, 1, new StringBuffer(new String(inputExpr)));
                boolean res2 = printParenthezedExpression(subexpr2, 1, new StringBuffer(new String(inputExpr)));
                //if(res1 && res2)
            }
            if(operator == '|') {
                boolean success1 = printParenthezedExpression(subexpr1, 1, new StringBuffer(new String(inputExpr)));
                boolean success2 = printParenthezedExpression(subexpr2, 1, new StringBuffer(new String(inputExpr)));

                boolean success3 = printParenthezedExpression(subexpr2, 0, new StringBuffer(new String(inputExpr)));
                boolean success4 = printParenthezedExpression(subexpr1, 1, new StringBuffer(new String(inputExpr)));

                boolean success5 = printParenthezedExpression(subexpr1, 1, new StringBuffer(new String(inputExpr)));
                boolean success6 = printParenthezedExpression(subexpr2, 0, new StringBuffer(new String(inputExpr)));;
            }
            if(operator == '^') {
                boolean success1 = printParenthezedExpression(subexpr1, 0, new StringBuffer(new String(inputExpr)));
                boolean success2 = printParenthezedExpression(subexpr2, 0, new StringBuffer(new String(inputExpr)));
            }
        }
        return true;
    }

    public static int evaluateExpressionNoParentheses(char[] inputExpr) {

        if(inputExpr == null || inputExpr.length == 0 ) System.out.println("Incorrect expr:"+new String(inputExpr));
        if (inputExpr.length == 1) return inputExpr[0];

        int curRes = inputExpr[0]-'0';

        for (int i = 1; i < inputExpr.length-2; i += 1) {
            int num1 = curRes;
            int num2 = inputExpr[i+1]-'0';
            char operator = inputExpr[i];
            curRes = evaluateExpression3(num1, num2, operator);
        }
        return curRes;
}
    


    public static int evaluateExpression3(int num1, int num2, char operator) {
        boolean res = false;
        if(operator == '&') res = (num1 == num2);
        if(operator == '|') res = (num1 == 1 || num2 == 1);
        if(operator == '^') res = (num1 != num2);

        if (res)
            return 1;
        else
            return 0;
    }

    @Test
    public void testCanPlace() {
        String exp = "0|1&1";
        printParenthezedExpression(exp.toCharArray(), 1, new StringBuffer());
    }
}
