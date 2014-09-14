package crackinginterview.datastructures.x5.BitOperations.interview.datastructures.x5.BitOperations;

import crackinginterview.datastructures.x5.BitOperations.DoubleAsBinary;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by pavlop on 4/24/14.
 */
public class DoubleAsBinaryTest {
    @Test
    public void test() {
        assertEquals("0.1", DoubleAsBinary.getDoubleAsBinary(0.5));
        assertEquals("0.01", DoubleAsBinary.getDoubleAsBinary(0.25));
        assertEquals("0.001", DoubleAsBinary.getDoubleAsBinary(0.125));
        assertEquals("0.101", DoubleAsBinary.getDoubleAsBinary(0.625));
        assertEquals("0.", DoubleAsBinary.getDoubleAsBinary(0));
    }

}
