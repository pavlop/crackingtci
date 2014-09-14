package crackinginterview.arraysAndStrings;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by pavlop on 2/26/14.
 */
public class AreStringsRotation {
    public static boolean perform(String s1, String s2) {
        if(s1 == null || s2 == null || s1.length() != s2.length()) return false;

        String longString = s1+s1;
        return isSubstring(longString, s2);

    }

    private static boolean isSubstring(String bigstring, String substring) {
        return bigstring.contains(substring);
    }


    @Test
    public void testPositive() {
        assertTrue(perform("waterbottle", "erbottlewat"));
        assertTrue(perform("water", "water"));
    }

    @Test
    public void testNegative() {
        assertTrue(!perform(null, "erbottlewat"));
        assertTrue(!perform("12", "1"));
    }
}
