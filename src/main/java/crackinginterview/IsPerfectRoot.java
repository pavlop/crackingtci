package crackinginterview;

import junit.framework.Assert;
import org.junit.Test;

/**
 *
 *
 *
 */
public class IsPerfectRoot {
    public boolean isPerfectSquare(int num) {
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.toBinaryString(-Integer.MIN_VALUE));
        if (num < 0) return false;
        if (num == 0) return true;
        // check not negative

        // sqrt (num) solutions:
        // 1) (x = 0 … num; if (x*x == num -> true; else if x*x > num ->false)

        // log(n) solutions:
        // 1) Newton method: x*x - num = 0
        // 2) binary search: choose x between [L; x] [x, R]
// "true" x is either in left or right parts

        int left = 1;
        int right = num;

        int res = num;
        while(left < right) {
            int mid = left + (right - left) / 2;
            int midSquare = mid*mid;

            if (midSquare > num) {
                right = mid;
            } else if (midSquare < num) {
                left = mid + 1;
            } else {
                res = mid;
                break;
            }
        }
        return res*res == num;
    }

    @Test
    public void testPositive() {
        Assert.assertEquals(0, sqrt(0));
        Assert.assertEquals(1, sqrt(1));

        Assert.assertTrue(isPerfectSquare(1));
        Assert.assertTrue(isPerfectSquare(4));
        Assert.assertTrue(isPerfectSquare(9));
        Assert.assertTrue(isPerfectSquare(16));
        Assert.assertTrue(isPerfectSquare(25));
        Assert.assertTrue(isPerfectSquare(36));
        Assert.assertTrue(isPerfectSquare(49));
        Assert.assertTrue(isPerfectSquare(144));
    }


    @Test
    public void testNegative() {
        Assert.assertFalse(isPerfectSquare(2));
        Assert.assertFalse(isPerfectSquare(3));
        Assert.assertFalse(isPerfectSquare(5));

    }


    //  MADE by Vova
    public int sqrt(int x) {
        int s = 1, e = x, res = x;
        int max_res = (int)Math.pow(2, 16);

        while (s < e) {
            int mid = (s + e) >>> 1;
            int sqr = mid * mid;
            res = mid;

            if (mid >= max_res || sqr < 0 || sqr > x) {
                e = mid;
            } else if (sqr < x) {
                int sqr2 = (mid+1)*(mid+1);
                if (sqr2 < 0 || sqr2 > x) break;
                s = mid + 1;
            } else {
                break;
            }
        }

        return res;
    }

    //Made by me
}
