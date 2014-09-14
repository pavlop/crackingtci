package crackinginterview.datastructures.x4.TreesAndGraphs;

import org.junit.Before;
import org.junit.Test;

import static crackinginterview.datastructures.x4.TreesAndGraphs.TreeCommonParent.findClosestParent;
import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertEquals;

/**
 * Created by pavlop on 4/11/14.
 */
public class TreeCommonParentTest {

    TNode n100, n1, n2, n3, n4, n5, unexistingNode;

    @Before
    public void steup() {
        //       3
        //  1         5
        //    2    4   100

         n100 = new TNode( null, null, 100);
         n2 = new TNode( null, null, 2);
         n4 = new TNode( null, null, 4);
         n1 = new TNode( null, n2, 1);
         n5 = new TNode( n4, n100, 5);
         n3 = new TNode(n1, n5, 3);
    }

    @Test
    public void testPositive() {



        System.out.println("postitive testing");
        assertSame(n3, findClosestParent(n3, n1, n5));
        assertSame (n3, findClosestParent(n3, n2, n4));
        assertSame (n3, findClosestParent(n3, n3, n100));
        assertSame (n3, findClosestParent(n3, n3, n4));
        assertSame (n1, findClosestParent(n3, n1, n2));
        assertSame (n5, findClosestParent(n3, n4, n100));
        assertSame(n5, findClosestParent(n3, n4, n5));
        assertSame (n5, findClosestParent(n3, n5, n5));

    }

    @Test
    public void testNegative() {
        System.out.println("negative testing");
        assertSame (null, findClosestParent(n3, n5, unexistingNode));
        assertSame (null, findClosestParent(n3, n5, null));
        assertSame (null, findClosestParent(null, n5, n1));
    }
}
