package interview.datastructures;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by pavlop on 2/18/14.
 */
public class StringHasAllUniqueNoStructures {
    public static boolean perform(String str) throws Exception {
        if (str == null) return true;
        int mask = 0;
        for (int i = 0; i < str.length(); i++) {
            int shortCharID = str.charAt(i) - 'a';
            if (!isByteZero(mask, shortCharID)) return false;
            mask = addByteToPosition(mask, shortCharID);
        }
        return true;
    }

    static boolean isByteZero(int number, int position) {
        System.out.println("position = "+position+ " isByteZero: input=" + Integer.toBinaryString(number));
        /* //shift to the needed position
        int shifted = number >> (position);
        System.out.println("isByteZero: after shifted=" + Integer.toBinaryString(shifted));
        return ((shifted % 2) == 0);
        */

        //imporvement
        System.out.println("(number & (1<<position) = " + (number & (1<<position)));
        return !((number & (1<<position)) >= 1);
    }

    static int addByteToPosition(int number, int position) {
        int withZeros = 1 << position;
        return number + withZeros;
    }

    @Test
    public void positive1() throws Exception {
        assertTrue(perform("asd"));
        assertTrue(perform("a b"));
        assertTrue(perform(""));
        assertTrue(perform(null));

        assertTrue(!perform("abca"));
        assertTrue(!perform("aa"));
    }

    @Test
    public void helpers() throws Exception {
        assertTrue(!isByteZero(8, 3));
        assertTrue(isByteZero(8, 2));
        assertTrue(isByteZero(1, 2));

        assertEquals(5, addByteToPosition(1, 2));
        assertEquals(2, addByteToPosition(1, 0));
    }

}
