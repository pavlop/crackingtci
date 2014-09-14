package leetcode;

import org.junit.Test;

/**
 *
 http://rleetcode.blogspot.com/2014/01/divide-two-integers.html
 */
public class DivideTwoIntegers {
    // 1) find n so that
    // a/b = 2^n*b + reminder



    public int divide(int dividend, int divisor) {
       /*
 Integer.MIN_VALUE is -2147483648, but the highest value a 32 bit integer can contain is +2147483647.
 Attempting to represent +2147483648 in a 32 bit int will effectively "roll over" to -2147483648.
 This is because, when using signed integers, the two's complement binary representations of
 +2147483648 and -2147483648 are identical. This is not a problem, however, as +2147483648
 is considered out of range.
 */
        // conver the diviedend and divisor to long before apply Math.abs()
        long a=Math.abs((long)dividend);
        long b=Math.abs((long)divisor);
        long result=0;

        while (a >= b){
            long c=b;
            int i=0;
            while(c<=a){
                a=a-c;
                c=c<<1;
                result +=   1<<i;
                i++;
            }

        }
        if (dividend<0  && divisor>0 || dividend>0 && divisor<0){
            result =-result;
        }
        return (int)result;

    }

    @Test
    public void dotest(){
       // System.out.println(divide(2147483647, 1));
        System.out.println("correct:"+divide(36, 5));
        System.out.println("my:"+dividePavel(36, 5));
    }

    public int dividePavel(int dividend, int divisor) {
        // a / b = b ^ n1 + b^n2 + ... + b

        long res = 0;
        long a=Math.abs((long)dividend);
        long b=Math.abs((long)divisor);
        int tmpRes = 1;

        while (tmpRes * b < a ) {

            long s1 =  b;

            while(a/2 > s1 ) {
                s1 = s1 << 1;
                tmpRes = 1<<tmpRes;
            }

            System.out.println("s1 "+s1);
            res = res + tmpRes;
            a = a - tmpRes*b;
            tmpRes = 1;
        }

        if (dividend<0  && divisor>0 || dividend>0 && divisor<0){
            res =-res;
        }
        return (int)res;
    }
}
