package dynamicProgramming;

import org.junit.Test;

import static junit.framework.TestCase.assertSame;

/**
 * Created by pavlop on 3/27/14.
 * Найти количество всех N-битных двоичных чисел, у которых в двоичной записи нет подряд идущих К
 единиц. N и К — натуральные числа, К ≤ N ≤ 30. Входными параметрами являются числа N и К.
 Например, при N = 3, К = 2 количество таких чисел равно 5.

 000 *
 001 *
 010 *
 011
 100 *
 101 *
 110
 111

 */
public class NumbersWithoutOnesInARow {
    public int doUsingCalculation(int numOfBits, int onesInARow) {
        return 0;
    }

    public int doUsingMyFormula(int numOfBits, int onesInARow) {
        int totalNumbers = (int) Math.pow(2, numOfBits);
        int numbersWithOnesInARow = ((int) Math.pow(2, numOfBits - onesInARow)) * (numOfBits- onesInARow + 1);
        return totalNumbers - numbersWithOnesInARow;
    }

    public int doRecursion(int numOfBits, int onesInARow) {
        int[] previousElements = new int[numOfBits+1];

        for(int i = 0; i < onesInARow; i++) {
            previousElements[i] = (int) Math.pow(2, i);
        }

        for(int i = onesInARow; i <= numOfBits; i++) {
            previousElements[i] = getSumOfPreviousElements(previousElements, i, onesInARow);
        }

        return previousElements[numOfBits];
    }

    int getSumOfPreviousElements(int[] array, int curID, int K) {
        int summ = 0;
        for(int i = 1; i <=K; i++) {
            summ += array[curID - i];
        }
        return summ;
    }

    public int usingButForce (int numOfBits, int onesInARow) {
        int maxNumber = (int) Math.pow(2, numOfBits);
        int badNumbersCounter= 0;

        String onesInARowStr = "";
        for (int i=0; i < onesInARow; i++) {
            onesInARowStr += "1";
        }

        System.out.println("maxNumber="+maxNumber);
        System.out.println("onesInARowStr="+onesInARowStr);

        for (int i = 0; i <= maxNumber; i++) {
            String binaryNum = Integer.toBinaryString(i);
            System.out.println("comare binaryNum:"+binaryNum +" with:"+onesInARowStr);
            if (binaryNum.contains(onesInARowStr)) badNumbersCounter++;
        }
        return maxNumber - badNumbersCounter;
    }



    @Test
    public void testdoUsingMyFormulax() {
        assertSame(2, usingButForce(1, 2));
        assertSame(3, usingButForce(2, 2));
        assertSame(5, usingButForce(3, 2));
        assertSame(15, usingButForce(4, 4));
        assertSame(56, usingButForce(6, 4));
    }

    @Test
    public void testdodoRecursion() {
        assertSame(2, doRecursion(1, 2));
        assertSame(3, doRecursion(2, 2));
        assertSame(5, doRecursion(3, 2));
        assertSame(15, doRecursion(4, 4));
        assertSame(56, doRecursion(6, 4));
    }
}
