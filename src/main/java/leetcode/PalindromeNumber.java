package leetcode;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;

/**
 * Determine whether an integer is a palindrome. Do this without extra space.
 * http://leetcode.com/2012/01/palindrome-number.html
 *
 */
public class PalindromeNumber {
    boolean isPalindrome(int x) {
        if (x < 0) return false;
        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }
        while (x != 0) {
            int l = x / div;
            int r = x % 10;
            if (l != r) return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    @Test
    public  void doTest() {
        //isPalindrome(123);
        assertFalse(isPalindrome(-1));
        assertFalse(isPalindrome(123));

        assertTrue(isPalindrome(1));
        assertTrue(isPalindrome(11));
        assertTrue(isPalindrome(121));
        assertTrue(isPalindrome(1221));
        assertTrue(isPalindrome(12321));
        assertTrue(isPalindrome(1515665151));
        System.out.println("lol");
    }
}
