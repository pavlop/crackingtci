package interview.arraysAndStrings;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by pavlop on 2/25/14.
 */
public class RemoveDuplicatesNoUseDataStructures {
    public static String perform(String str) {

        // this is a brute force solution
        // Optimization:
        // if the order can be changed
        // you can always move chars from the tail

        char[] chars = str.toCharArray();
        int newLength = chars.length;

        for(int i = 0; i < newLength; i++) {
            for(int j = i+1; j <newLength;){
                if (chars[i] == chars[j]) {
                    //shift the array
                    for (int k = j; k < newLength -1; k++) {
                        chars[k] = chars[k+1];
                    }
                    newLength--;
                } else {
                    j++;
                }
            }
        }

        return new String(Arrays.copyOf(chars, newLength));
    }

    @Test
    public void positive() {
        assertEquals("a",perform("aaa"));
        assertEquals(perform("aabbcc"), "abc");
        assertEquals(perform("aa"), "a");
        assertEquals(perform("aa"), "a");
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();
    @Test
    public void negative(){
        assertEquals("",perform(""));
        assertEquals("",perform(""));
        exception.expect(Exception.class);
        perform(null);
    }



}
