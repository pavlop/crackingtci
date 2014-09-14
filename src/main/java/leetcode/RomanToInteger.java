package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 * I 1
 * V 5
 * X 10
 * L 50
 * C 100
 * D 500
 * M 1000
 */
public class RomanToInteger {
    int romanToInt(String s) {
        java.util.HashMap<Character, Integer> charToInt = new java.util.HashMap<Character, Integer>();
        charToInt.put('I', 1 );
        charToInt.put('V', 5 );
        charToInt.put('X', 10 );
        charToInt.put('L', 50 );
        charToInt.put('C', 100 );
        charToInt.put('D', 500 );
        charToInt.put('M', 1000 );
        int result = 0;
        int preInt = 0;
        for(int i = s.length()-1; i >=0; i--)
        {
            char curChar = s.charAt(i);
            int curInt = charToInt.get(curChar);
            if (curInt >= preInt)
                result += curInt;
            else
                result -= curInt;

            preInt = curInt;
        }

        return result;
    }

    @Test
    public void doTest() {
        assertEquals(10, romanToInt("X"));
        assertEquals(90, romanToInt("XC"));
        assertEquals(3788, romanToInt("MMMDCCLXXXVIII"));
        assertEquals(1996, romanToInt("MCMXCVI"));
    }

}
