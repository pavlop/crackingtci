package crackinginterview.datastructures.x5.BitOperations;

/**
 *
 * Given a real number between 0 and 1 (e.g., 0.72)
 * that is passed in as a double, print the binary representation.
 * If the number cannot be represented accurately in binary with at most 32 characters,
 * print "ERROR."
 *
 *
 *
 SOLUTION
 NOTE: When otherwise ambiguous, we'll use the subscripts
 x(2) and x(0) to indicate whether x is in base 2 or base 10.
 First, let's start off by asking ourselves what a non-integer number in binary looks like.

 By analogy to a decimal number, the binary number 0.1012 would look like:
 0.101 (in 2)  =1*(1/2^1)+0*(1/2^2)+1*(1/2^3).
 To print the decimal part, we can multiply by 2

 and check if 2n is greater than or equal to 1.
 This is essentially "shifting" the fractional sum. That is:
 r = 2(10) * n
 = 2(10) * 0.101(2)
 = 1 * (1/2^0) + 0 * (1/2^1) + 1 * (1/2^2) = 1.01(2)
 If r >= 1, then we know that n had a 1 right after the decimal point.
 By doing this continuously, we can check every digit.
 *
 */
public class DoubleAsBinary {
    public static String getDoubleAsBinary(double num) {
        if (num >1 || num < 0) return "ERROR";
        StringBuffer sb = new StringBuffer();
        for (int i =0; i<32 ; i++) {
            num = num * 2;
            if (num >= 1) {
                sb.append(1);
                num = num -1;
            } else  if (num == 0)  {
                break;
            }
            else{
                sb.append(0);
            }
        }
        return "0."+sb.toString();
    }
}


