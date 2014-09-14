package leetcode;

import org.junit.Test;

import java.util.*;

/**
 *
 *
 *
 */
public class LetterCombinationsPhone {

    public static final Map <Character, List<Character>> digitsMapping = new HashMap <Character, List<Character>>();
    static {
        digitsMapping.put('0', Arrays.asList('\0'));
        digitsMapping.put('1', Arrays.asList('\0'));
        digitsMapping.put('2', Arrays.asList('a','b','c'));
        digitsMapping.put('3', Arrays.asList('d','e','f'));
        digitsMapping.put('4', Arrays.asList('g','h','i'));
        digitsMapping.put('5', Arrays.asList('k','j','l'));
        digitsMapping.put('6', Arrays.asList('m','n','o'));
        digitsMapping.put('7', Arrays.asList('p','q','r','s'));
        digitsMapping.put('8', Arrays.asList('t','u','v'));
        digitsMapping.put('9', Arrays.asList('w','x','y','z'));
    }

    public List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<String>();
        letterCombinations(digits, 0, null, results);
        return results;
    }

    public static void letterCombinations(String digits, int cursor, StringBuilder curRes, List<String> results) {
        if (cursor == 0) curRes = new StringBuilder();
        if (cursor >= digits.length()) {
            results.add(curRes.toString());
            return;
        }

        char curDigit = digits.charAt(cursor);
        List <Character> chars = digitsMapping.get(curDigit);
        for (char c : chars) {
            letterCombinations(digits, cursor+1, (new StringBuilder(curRes.toString())).append(c), results);
        }
    }

    @Test
    public void doTest() {
        System.out.println(letterCombinations("12"));
        //System.out.println(letterCombinations("6505375627"));
    }
}
