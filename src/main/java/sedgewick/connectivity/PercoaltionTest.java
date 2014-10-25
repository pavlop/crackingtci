package sedgewick.connectivity;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 *
 *
 */
public class PercoaltionTest {

    @Test
    public void testPositive() {

        Percolation p = new Percolation(1);
        Assert.assertFalse(p.percolates());
        Assert.assertFalse(p.isOpen(1, 1));
        Assert.assertFalse(p.isFull(1, 1));
        p.open(1,1);
        Assert.assertTrue(p.isOpen(1, 1));
        Assert.assertTrue(p.isFull(1, 1));
        Assert.assertTrue(p.percolates());

     /* 0 0 1 0
        1 1 X 0
        1 0 0 1
        1 1 0 Y
     */
        System.out.println("P2");
        Percolation p2 = new Percolation(4);
        p2.open(1,3);
        Assert.assertTrue(p2.isFull(1, 3));

        p2.open(2,1);p2.open(2,2);
        p2.open(3,1);p2.open(3,4);
        p2.open(4,1);p2.open(4,2);
        Assert.assertFalse(p2.percolates());
        Assert.assertTrue(p2.isOpen(4, 2));
        Assert.assertFalse(p2.isFull(4,2));

        p2.open(2,3);
        Assert.assertTrue(p2.isOpen(2, 3));
        Assert.assertTrue(p2.isFull(2, 3));

        Assert.assertTrue(p2.isOpen(4, 2));
        Assert.assertTrue(p2.isFull(4, 1));
        Assert.assertTrue(p2.isFull(4, 2));
        Assert.assertTrue(p2.percolates());

        p2.open(4,4);
        Assert.assertTrue(p2.isOpen(4,4));
        Assert.assertFalse(p2.isFull(4, 4));

    }
}
