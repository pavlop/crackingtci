package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example1: x = 123, return 321
 Example2: x = -123, return -321


 My solution
 x=123

 result = x % 10 = 123%10 = 3
 x = x/10 = 12

 result = result*10 + x%10 = 30+2 = 32
 x = x/10 = 1

 result = result*10 + x%10 = 320 + 1= 321
 x = x/10 =




 */
public class ReverseInteger {

    public int reverse(int x) {
        int res = x%10;
        x = x/10;
        while (x != 0) {
            res = res*10 + x%10;
            x = x/10;
        }
        return res;
    }

    @Test
    public void doTest() {
        assertEquals(321, reverse(123));
        assertEquals(-321, reverse(-123));
        assertEquals(1, reverse(1));
    }

}
