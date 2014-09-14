package crackinginterview.datastructures.x5.BitOperations;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by pavlop on 4/20/14.
 */
public class InsertOneNumberIntoAnoterTest {
    @Test
    public void test() {

        int base = Integer.parseInt("10111101", 2);
        int toInsert = Integer.parseInt("1001", 2);

        assertEquals(Integer.parseInt("10111001", 2), InsertOneNumberIntoAnother.placeNumberInsideAnoter(base, toInsert, 4));
        assertEquals(Integer.parseInt("10011101", 2), InsertOneNumberIntoAnother.placeNumberInsideAnoter(base, toInsert, 8));
        assertEquals(Integer.parseInt("10100101", 2), InsertOneNumberIntoAnother.placeNumberInsideAnoter(base, toInsert, 6));
    }
}
