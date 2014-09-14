package leetcode;


import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Given an integer, convert it to a roman numeral.
 * Input is guaranteed to be within the range from 1 to 3999
 *
 * I 1
 * V 5
 * X 10
 * L 50
 * C 100
 * D 500
 * M 1000
 *
 * I,II,III,IV,V,VI,VII,VIII,....
 *
 *
 * 375
 *
 * 300: CCC
 * 70: LXX
 * 5: V
 *
 *
 * 0-3 III
 * 3-4 IV - V
 * 5 - 5+3 V - VIII
 * 9-10 IX - X
 *
 */
public class IntegerToRoman {
    private static final java.util.Map<Integer, Character > romaDigits = new java.util.HashMap<Integer, Character >();

    public String intToRoman(int num) {
        romaDigits.put(1, 'I');
        romaDigits.put(5, 'V');
        romaDigits.put(10, 'X');
        romaDigits.put(50, 'L');
        romaDigits.put(100, 'C');
        romaDigits.put(500, 'D');
        romaDigits.put(1000, 'M');

        String n = num+"";
        //System.out.println("n="+n);
        StringBuilder res = new StringBuilder();

        for (int i = 0; i<n.length(); i++ ) {
            int grade = n.length() - i - 1;
            grade = (int)Math.pow(10, grade);
            char digit = n.charAt(i);
            //System.out.println("digit="+digit+" grade="+grade);
            switch(digit) {
                case '0': break;
                case '1':res.append(romaDigits.get(grade)); break;
                case '2':res.append(romaDigits.get(grade) + "" + romaDigits.get(grade));break;
                case '3':res.append(romaDigits.get(grade) + "" + romaDigits.get(grade) + "" + romaDigits.get(grade));break;
                case '4':res.append(romaDigits.get(grade)).append(romaDigits.get(grade * 5));break;
                case '5':res.append(romaDigits.get(grade * 5));break;
                case '6':res.append(romaDigits.get(grade * 5)).append(romaDigits.get(grade));break;
                case '7':res.append(romaDigits.get(grade * 5)).append(romaDigits.get(grade)).append(romaDigits.get(grade));break;
                case '8':res.append(romaDigits.get(grade * 5)).append(romaDigits.get(grade)).append(romaDigits.get(grade)).append(romaDigits.get(grade));break;
                case '9':res.append(romaDigits.get(grade)).append(romaDigits.get(grade * 10));break;
            }
        }
        return res.toString();
    }

    @Test
    public void doTest() {
        assertEquals("I", intToRoman(1));
        assertEquals("XX", intToRoman(20));
        assertEquals("XXXV", intToRoman(35));
        assertEquals("CVI", intToRoman(106));
        System.out.println("123="+intToRoman(123));
        System.out.println("99="+intToRoman(99));
        System.out.println("3999="+intToRoman(3999));

    }

}
