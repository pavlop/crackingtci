/**
 * Created by pavlop on 4/5/14.
 */
package crackinginterview.datastructures.x4.TreesAndGraphs;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class MyBinaryTreeTest {
    @Test
    public void test() {
        MyBinaryTree tree = new MyBinaryTree();
        tree.addToSearchSubtree(tree.head, 5);
        tree.addToSearchSubtree(tree.head, 3);
        tree.addToSearchSubtree(tree.head, 4);
        assertEquals("[3, 4, 5]", ""+tree.asSortedList(tree.head, new ArrayList<Integer>()));
    }

}
