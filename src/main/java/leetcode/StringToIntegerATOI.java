package leetcode;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertSame;

/**
 * Implement atoi to convert a string to an integer.
 *
 * Hint: Carefully consider all possible input cases.
 * If you want a challenge, please do not see below and ask yourself what are the possible input cases.
 *
 * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). Y
 * ou are responsible to gather all the input requirements up front.
 *
 * SOLUTION:

 1. null or empty string
 2. white spaces
 3. +/- sign
 4. calculate real value
 5. handle min & max

 http://www.programcreek.com/2012/12/leetcode-string-to-integer-atoi/

 *
 */

public class StringToIntegerATOI {

    public int atoi(String str) {
        if (str == null || str.length() < 1)
            return 0;

        // trim white spaces
        str = str.trim();

        char flag = '+';

        // check negative or positive
        int i = 0;
        if (str.charAt(0) == '-') {
            flag = '-';
            i++;
        } else if (str.charAt(0) == '+') {
            i++;
        }
        // use double to store result
        double result = 0;

        // calculate value
        while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            result = result * 10 + (str.charAt(i) - '0');
            i++;
        }

        if (flag == '-')
            result = -result;

        // handle max and min
        if (result > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;

        if (result < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;

        return (int) result;
    }
    @Test
    public void doTest() {
        assertSame(123, atoi("123"));
        assertSame(123, atoi("   123"));
        assertSame(123, atoi("+123"));
        assertSame(-12, atoi("  -0012a42"));
        assertSame(0, atoi("   +0 123"));
        assertSame(0, atoi("   - 321"));
        assertEquals(2147483647, atoi("2147483648"));


    }

}
