package leetcode;


import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern
 * on a given number of rows like this:
 *

 P   A   H   N
 A P L S I I G
 Y   I   R

 And then read line by line: "PAHNAPLSIIGYIR"


 Write the code that will take a string and make this conversion given a number of rows:
 convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".

 */
public class ZigZagConversion {
    public static String convert(String s, int nRows) {
        if(nRows == 1) return s;
        Map<Integer, StringBuffer> charsOfLevel = new HashMap<Integer, StringBuffer>();
        int level = 0;
        int increment = -1; // can be 1 and -1. Changes value when achieves top or bottm boundary

        for (int i = 0; i < s.length(); i++) {
            if(charsOfLevel.containsKey(level)) {
                charsOfLevel.get(level).append(s.charAt(i));
            } else {
                StringBuffer sb= new StringBuffer();
                sb.append(s.charAt(i));
                charsOfLevel.put(level, sb);
            }
            if(level == 0 || level+1 == nRows) increment = increment * (-1);
            level += increment;
        }

        StringBuffer res = new StringBuffer();
        for (int i = 0; i < nRows; i++) {
            StringBuffer chars = charsOfLevel.get(i);
            if(chars != null) res.append(chars);
        }
        return res.toString();
    }

    @Test
    public  void doTest() {
        assertEquals("PAHNAPLSIIGYIR", convert("PAYPALISHIRING", 3));
        assertEquals("ASD", convert("ASD", 1));

    }

}
