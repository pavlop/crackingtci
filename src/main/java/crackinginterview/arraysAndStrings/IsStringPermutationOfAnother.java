package crackinginterview.arraysAndStrings;

import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by pavlop on 2/21/14.
 */
public class IsStringPermutationOfAnother {
    public static boolean  perform(String str1, String str2) throws Exception {
        if(str1==null || str2==null) return false;
        if(str1.length() != str2.length()) return false;

        //Put chars form the first str to has table
        Map<Character, Integer> charsOfFirstString = new HashMap<Character,Integer>();
        for(int i =0 ; i<str1.length(); i++) {
            Character c = str1.charAt(i);
            if (charsOfFirstString.containsKey(c)) {
                charsOfFirstString.put(c, charsOfFirstString.get(c)+1);
            } else {
                charsOfFirstString.put(c,1);
            }
        }

        //Check that all chars form the second str are in the table
        for(int i =0 ; i<str2.length(); i++) {
            Character c = str2.charAt(i);
            if(!charsOfFirstString.containsKey(c) || charsOfFirstString.get(c) == 0) {
                return false;
            } else {
                charsOfFirstString.put(c, charsOfFirstString.get(c)-1);
            }
        }

        return true;

    }

    @Test
    public void positive1() throws Exception {
        assertTrue(perform("asd", "sda"));
        assertTrue(perform(" asd", "s da"));
        assertFalse(perform("asdf", "asd"));
        assertFalse(perform("asd", "asf"));
    }

    @Test
    public void stringPermutationCheck()  throws Exception {
        // true cases
        assertThat(perform("abc", "acb"), is(true));
        assertThat(perform("bac", "bca"), is(true));
        assertThat(perform("cab", "cba"), is(true));

        // false cases
        assertThat(perform("cab", "acba"), is(false));
        assertThat(perform("cab", "acbb"), is(false));

        // corner cases
        assertThat(perform("", ""), is(true));
        assertThat(perform("", null), is(false));
        assertThat(perform(null, ""), is(false));
        assertThat(perform(null, null), is(false));
    }
}
