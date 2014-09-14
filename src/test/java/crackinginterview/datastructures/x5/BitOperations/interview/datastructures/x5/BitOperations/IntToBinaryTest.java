package crackinginterview.datastructures.x5.BitOperations.interview.datastructures.x5.BitOperations;

import org.junit.Test;

import static crackinginterview.datastructures.x5.BitOperations.IntToBinary.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by pavlop on 4/21/14.
 */


public class IntToBinaryTest {
    @Test
    public void test() {
        assertEquals("111", intToBinary(7));
        assertEquals("1000", intToBinary(8));
        assertEquals(7, binaryToInt("111"));
        assertEquals(8, binaryToInt("1000"));
    }
}
