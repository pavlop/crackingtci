package crackinginterview.datastructures.x4.TreesAndGraphs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 * Write an algorithm to find the 'next'node (i.e., in-order successor)
 * of a given node in
 a binary search tree. You may assume that each node has a link to it sparent.
 *
 */

public class FindNextNodeOfTree {

    public static TNode findNextNode(TNode t) {
        TNode next;

        // first check the right subtree of current node
        // the element will be in the very left position of subtree
        //   X
        // 1   3
        // need to return 3
        next = getRcursivelyNextInSubTree(t.right);
        if (next != null) return next;


        // we do binary search and memorize the smallest element
        //      3
        //  1      5
        //   2   X   8
        TNode parent = t.parent;
        TNode child = t;
        System.out.println("Befor searching in parents: child = " + child);
        while (parent != null && parent.right == child) {
            System.out.println("looking in parents: child = " + child);
            child = parent;
            parent = parent.parent;
        }
        return parent;
    }

    private static TNode getRcursivelyNextInSubTree (TNode node) {
        System.out.println("getRcursivelyNextInSubTree:"+node);
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
