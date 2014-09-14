import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class Dragons {
	
	public String snaug(int[] initialFood, int rounds) {

        BigDecimal[] prev = new BigDecimal[6];
//        for (int i = 0; i < 6; i++) prev[i] = initialFood[i];
        prev[0] = new BigDecimal(initialFood[2]);
        prev[1] = new BigDecimal(initialFood[1]);
        prev[2] = new BigDecimal(initialFood[5]);
        prev[3] = new BigDecimal(initialFood[3]);
        prev[4] = new BigDecimal(initialFood[0]);
        prev[5] = new BigDecimal(initialFood[4]);
//        System.out.println(Arrays.toString(prev));

        //UP, BACK, RIGHT, DOWN, FRONT, LEFT
        for (int i =0; i < rounds; i++) {
            BigDecimal[] newFood = new BigDecimal[6];
            newFood[0] = prev[1].add(prev[2]).add(prev[5]).add(prev[4]).multiply(new BigDecimal(0.25));
            newFood[1] = prev[2].add(prev[3]).add(prev[0]).add(prev[5]).multiply(new BigDecimal(0.25));
            newFood[2] = prev[3].add(prev[4]).add(prev[0]).add(prev[1]).multiply(new BigDecimal(0.25));
            newFood[3] = prev[1].add(prev[2]).add(prev[5]).add(prev[4]).multiply(new BigDecimal(0.25));
            newFood[4] = prev[2].add(prev[3]).add(prev[0]).add(prev[5]).multiply(new BigDecimal(0.25));
            newFood[5] = prev[3].add(prev[4]).add(prev[0]).add(prev[1]).multiply(new BigDecimal(0.25));
            prev = newFood;
        }
		return decimalToFraction(prev[0]);
	}

    public String decimalToFraction(BigDecimal bigDecimal) {
        BigInteger denominator = BigInteger.ONE;
        while (bigDecimal.remainder(BigDecimal.ONE).floatValue() != 0.0) {
            denominator = denominator.multiply(BigInteger.TEN);
            bigDecimal = bigDecimal.multiply(BigDecimal.TEN);
        }
        BigInteger numenator = bigDecimal.toBigInteger();

        //symplify fraction
        BigInteger previousGCD = gcd(numenator, denominator);
        while (!previousGCD.equals(BigInteger.ONE)) {
            numenator = numenator.divide(previousGCD);
            denominator = denominator.divide(previousGCD);
            previousGCD = gcd(numenator, denominator);
        }

        if (denominator.equals(BigInteger.ONE)) return "" + numenator;
        return numenator + "/" + denominator;
    }

    public BigInteger gcd(BigInteger a, BigInteger b) {
        if (BigInteger.ZERO.equals(b)) return a;
        return gcd(b, a.remainder(b));
    }
}