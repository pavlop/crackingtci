package interview.datastructures;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

/**
 * Created by pavlop on 2/23/14.
 */
public class SimpleStringCompression {
    public static String  perform(String str) throws Exception {
        if (str == null || str.length() < 1) {
            return null;
        }

        char oldChar = str.charAt(0);
        char newChar = str.charAt(0);
        int repetedTimes = 1;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i<str.length(); i++) {
            oldChar = newChar;
            newChar = str.charAt(i);
            if (i == 0) continue;
            if (oldChar == newChar) {
                repetedTimes++;
            }
            else {
                sb.append(oldChar).append(repetedTimes);
                repetedTimes = 1;
            }
        }
        sb.append(oldChar).append(repetedTimes);
        return sb.toString();
    }

    @Test
    public void positive1() throws Exception {
        assertEquals("a2b1c3d2",perform("aabcccdd"));
        assertEquals("a3",perform("aaa"));
        assertEquals("a1",perform("a"));
        assertNull(perform(""));
    }
}
