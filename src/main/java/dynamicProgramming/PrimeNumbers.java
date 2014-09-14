package dynamicProgramming;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 *
 *
 */
public class PrimeNumbers {
    int counterOperations = 0;
    public List<Integer> getTenPrimeNumbersReaterThen(int n, int numberOfNums) {
//        System.out.println(Math.log(n));
        TreeSet<Integer> primes = new TreeSet<Integer>();

        int count = 0;
        while (n < numberOfNums) {
            if(isPrimve(n, primes)) {
                primes.add(n);
                count++;
            }
            n += 2;
        }
        System.out.println(primes.size());
        return new ArrayList<Integer>(primes.tailSet(0));
    }

    public boolean isPrimve(int n, TreeSet<Integer> primes ){
        int i = 2;
        while (i < Math.sqrt(n)) {
            counterOperations ++;
            if (n % i == 0) {
                primes.add(i);
                return false;
            } else {
                i++;
            }
        }
        return true;
    }

    @Test
    public void test() {
        int m  = 1_000_000_001;
        System.out.println(getTenPrimeNumbersReaterThen(m, 10));
        System.out.println("counterOperations="+counterOperations);
    }
}
