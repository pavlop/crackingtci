package leetcode;

import junit.framework.Assert;
import org.junit.Test;

/**
 *
 *
 *
 */
public class SqrtSquareRoot {
    public int sqrt(int x) {
        long target = x;
        long prvious = target;
        long res = target/2;
        long resSq = res*res;
        while (Math.abs(res - prvious) > 1 || resSq > target ) {
            //System.out.println(res);
            long delta = Math.abs(prvious-res);
            long deltaHalf = delta / 2;
            if (deltaHalf == 0) deltaHalf = 1;
            prvious = res;
            if (resSq <  target) {
                res = res + deltaHalf;
            } else {
                res = res - deltaHalf;
            }
            resSq = res*res;
        }

        //add some ones until we are bigger then target
        while (res*res <= target) res++;
        return  (int)res -1;
    }

    @Test

    public void test() {
        Assert.assertEquals(1, sqrt(1));
        Assert.assertEquals(1, sqrt(2));
        Assert.assertEquals(1, sqrt(3));
        Assert.assertEquals(5, sqrt(25));
        Assert.assertEquals(5, sqrt(26));
        Assert.assertEquals(4, sqrt(21));
        Assert.assertEquals(4, sqrt(20));
        Assert.assertEquals(46339, sqrt(2147395599));
        Assert.assertEquals(46340, sqrt(2147483647));
        Assert.assertEquals(45944, sqrt(2110890896));

    }
}
