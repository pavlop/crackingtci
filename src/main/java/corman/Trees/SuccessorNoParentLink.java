package corman.Trees;

import crackinginterview.datastructures.x4.TreesAndGraphs.TNode;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 */
public class SuccessorNoParentLink {

    public static TNode findNextNode(TNode root, TNode target) {
        System.out.println("root "+root+ " target "+target );
        TNode next, cur, minNodeGraterThenTarget;

        // first check the right subtree of current node
        // the element will be in the very left position of subtree
        //   X
        // 1   3
        // need to return 3
        next = getRcursivelyNextInSubTree(target.right);
        if (next != null) return next;

        // if there is no right node
        // we need to build path to the target element
        // and choose the smallest element form the path  which is >= target
        minNodeGraterThenTarget = null;
        cur = root;

        while ( cur != target) {
            if(cur.value > target.value && (minNodeGraterThenTarget == null || cur.value < minNodeGraterThenTarget.value)) {
                minNodeGraterThenTarget = cur;
            }
            if(cur.value > target.value) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return  minNodeGraterThenTarget;
    }

    private static TNode getRcursivelyNextInSubTree (TNode node) {
        if (node == null) return null;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Test
    public void testPositive() {
        //      3
        //  1     5
        TNode n1 = new TNode( null, null, 1);
        TNode n5 = new TNode( null, null, 5);
        TNode n3 = new TNode( n1, n5, 3);

        assertEquals (n5, findNextNode(n3, n3));
        assertEquals(null, findNextNode(n3, n5));

        //     3
        // 1      5
        //  2   4   100
        TNode n2 = new TNode( null, null, 2);
        TNode n4 = new TNode( null, null, 4);
        TNode n100 = new TNode( null, null, 100);
        n1.right = n2;
        n5.left = n4;
        n5.right = n100;

        assertEquals (n2, findNextNode(n3, n1));
        assertEquals (n3, findNextNode(n3, n2));
        assertEquals (n4, findNextNode(n3, n3));
        assertEquals (n5, findNextNode(n3, n4));
        assertEquals (n100, findNextNode(n3, n5));
        assertEquals (null, findNextNode(n3, n100));

    }
}
