package sedgewick.connectivity;

import org.junit.*;


/**
 *
 *
 *
 */
public class QuickUnionWeightedTest {
    @Test
    public void doTesting() {
        QuickUnionWeighted connectivity = new QuickUnionWeighted(8);

        connectivity.connect(1, 2);
        connectivity.connect(3, 4);
        connectivity.connect(5, 6);
        connectivity.connect(6,7);

        Assert.assertTrue(connectivity.isConnected(1, 2));
        Assert.assertTrue(connectivity.isConnected(5, 7));
        Assert.assertTrue(connectivity.isConnected(7, 5));
        Assert.assertFalse(connectivity.isConnected(7, 1));

        connectivity.connect(4,5);
        connectivity.connect(1,4);
        Assert.assertTrue(connectivity.isConnected(7, 1));
    }
}
