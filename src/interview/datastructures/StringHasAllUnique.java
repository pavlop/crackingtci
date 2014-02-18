package interview.datastructures;

import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by pavlop on 2/18/14.
 */
public class StringHasAllUnique {
    public static boolean perform(String str) throws Exception {
        if (str == null) {return true;}
        char[] chars = str.toCharArray();
        HashSet<Character> uniqueChars = new HashSet<Character>();

        for (char c : chars) {
            if (uniqueChars.contains(c)) {
                return false;
            } else {
                uniqueChars.add(c);
            }
        }
        return true;

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
}
