package crackinginterview.datastructures.x4.TreesAndGraphs;

import org.junit.Test;

import static crackinginterview.datastructures.x4.TreesAndGraphs.FindNextNodeOfTree.findNextNode;
import static org.junit.Assert.assertEquals;

/**
 * Created by pavlop on 4/7/14.
 */
public class FindNextNodeOfTreeTest {
    @Test
    public void testPositive() {
        //      3
        //  1     5
        //

        TNode n1 = new TNode( null, null, 1);
        TNode n5 = new TNode( null, null, 5);
        TNode n3 = new TNode( n1, n5, 3);
        n1.parent = n3; n5.parent = n3;

        findNextNode(n5);

        assertEquals (n5, findNextNode(n3));
        assertEquals (null, findNextNode(n5));

        //      3
        //  1     5
        //   2  4   6
        TNode n2 = new TNode( null, null, 2);
        TNode n4 = new TNode( null, null, 4);
        TNode n100 = new TNode( null, null, 100);
        n1.right = n2; n2.parent  = n1;
        n5.left = n4; n5.right = n100;
        n4.parent = n5; n100.parent = n5;


        assertEquals (n2, findNextNode(n1));
        assertEquals (n3, findNextNode(n2));
        assertEquals (n4, findNextNode(n3));
        assertEquals (n5, findNextNode(n4));
        assertEquals (n100, findNextNode(n5));
        assertEquals (null, findNextNode(n100));


    }
}
