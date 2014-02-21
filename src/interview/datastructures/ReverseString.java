package interview.datastructures;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by pavlop on 2/20/14.
 */
public class ReverseString {
    public static String  perform(String str) throws Exception {
        if (str == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        int len = str.length();
        for (int i =0; i<len; i++){
            sb.append(str.charAt(len-i-1));
        }

        return sb.toString();

    }

    @Test
    public void positive1() throws Exception {
        assertEquals("dsa",perform("asd"));
        assertEquals("",perform(""));
        assertEquals("121",perform("121"));
    }
}
