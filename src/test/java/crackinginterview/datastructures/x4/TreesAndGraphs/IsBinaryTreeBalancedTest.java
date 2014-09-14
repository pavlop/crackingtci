package crackinginterview.datastructures.x4.TreesAndGraphs;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;

/**
 * Created by pavlop on 4/5/14.
 */
public class IsBinaryTreeBalancedTest {
    @Test
    public void testSmall() {
        /*
            5
          3
            4
        */
        MyBinaryTree tree = new MyBinaryTree();
        tree.addToSearchSubtree(tree.head, 5);
        tree.addToSearchSubtree(tree.head, 3);
        tree.addToSearchSubtree(tree.head, 4);
        System.out.println("tree:"+tree);
        assertFalse(IsBinaryTreeBalanced.checkIfBalanced(tree.head));

        /*
           4
          3 5
        */
        MyBinaryTree tree2 = new MyBinaryTree();
        tree2.addToSearchSubtree(tree2.head, 4);
        tree2.addToSearchSubtree(tree2.head, 3);
        tree2.addToSearchSubtree(tree2.head, 5);
        System.out.println("tree2:"+tree2);
        assertTrue(IsBinaryTreeBalanced.checkIfBalanced(tree2.head));

    }

    @Test
    public void testBig() {

        MyBinaryTree tree = new MyBinaryTree();
        tree.addToSearchSubtree(tree.head, 15);
        tree.addToSearchSubtree(tree.head, 14);
        tree.addToSearchSubtree(tree.head, 13);
        tree.addToSearchSubtree(tree.head, 12);
        tree.addToSearchSubtree(tree.head, 11);
        tree.addToSearchSubtree(tree.head, 10);
        tree.addToSearchSubtree(tree.head, 9);
        tree.addToSearchSubtree(tree.head, 8);
        tree.addToSearchSubtree(tree.head, 7);
        tree.addToSearchSubtree(tree.head, 6);
        tree.addToSearchSubtree(tree.head, 5);
        tree.addToSearchSubtree(tree.head, 4);
        tree.addToSearchSubtree(tree.head, 3);
        tree.addToSearchSubtree(tree.head, 2);
        tree.addToSearchSubtree(tree.head, 1);
        System.out.println("tree:" + tree);
        assertFalse(IsBinaryTreeBalanced.checkIfBalanced(tree.head));

        /*
                5
            3       7
          2   4   6   8
        */
        MyBinaryTree tree2 = new MyBinaryTree();
        tree2.addToSearchSubtree(tree2.head, 5);
        tree2.addToSearchSubtree(tree2.head, 3);
        tree2.addToSearchSubtree(tree2.head, 4);
        tree2.addToSearchSubtree(tree2.head, 2);
        tree2.addToSearchSubtree(tree2.head, 7);
        tree2.addToSearchSubtree(tree2.head, 6);
        tree2.addToSearchSubtree(tree2.head, 8);
        System.out.println("tree2:"+tree2);
        assertTrue(IsBinaryTreeBalanced.checkIfBalanced(tree2.head));

    }
}
