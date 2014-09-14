package crackinginterview.datastructures.x5.BitOperations;

/**
 *
 * Youaregiven two 32-bit numbers,N andM, andtwobitpositions, i andj.
 * Write a method to insert M into N such that M starts at bit j and ends at bit i.
 * You can assume that the bits j through i have enough space to fit all ofM.
 * That is,if M= 10011, you can assume that there are at least 5 bits between j and i.
 * You would not, for example,have j-3 andi=2,because M could not fully fitbetweenbit3andbit2.
 EXAMPLE:
 Input:N=10000000000, M=10011, i =2, j =6 Output: N = 1000100110
 *
 *
 * PAVLO:
 * Ill change the interface:
 * source
 *
 */
public class InsertOneNumberIntoAnother {
    public static int placeNumberInsideAnoter(int N, int M, int startAtFromRight) {
        System.out.println("N:"+Integer.toBinaryString(N));
        System.out.println("M:"+Integer.toBinaryString(M));
        System.out.println("startAtFromRight:"+startAtFromRight);
        int len = Integer.toBinaryString(M).length();

        // create a MASK that will have zeros at the
        // postitons between startAt and startAt + len111110000111


        int manyOnes = Integer.MAX_VALUE*2+1;
        System.out.println("manyOnes:"+manyOnes);
        System.out.println("manyOnes:"+Integer.toBinaryString(manyOnes));

        // from 11111111 will do 1110000
        int leftPart = manyOnes<<startAtFromRight;
        // from 11111111 will do 0000011
        int rghtPart = ~(manyOnes<<(startAtFromRight-len));
        int mask =leftPart|rghtPart;
        System.out.println("leftPart:"+Integer.toBinaryString(leftPart));
        System.out.println("rghtPart:"+Integer.toBinaryString(rghtPart));
        System.out.println("mask    :"+Integer.toBinaryString(mask));

        // set the area with zeros
        // clear the bits between startAt and startAt + len
        int nCleared = N & mask;
        int mWithZeros = M << (startAtFromRight-len);
        System.out.println("nCleared:"+Integer.toBinaryString(nCleared));
        System.out.println("mWithZeros:"+Integer.toBinaryString(mWithZeros));

        // complete
        int result = nCleared|mWithZeros;
        System.out.println("result:"+Integer.toBinaryString(result));
        return result;
    }
}
