package corman.Trees;

import java.util.Collection;

public interface Tree<Key> {



    void put(Key value);

    int height();

    int size();

    /**
     * leetcode
     */
    Iterable<Key> inOrderTraversal();

    /**
     * leetcode
     */
    Iterable<Key> preOrderTraversal();

    /**
     * leetcode
     */
    Iterable<Key> postOrderTraversal();

    Key lowestCommonAccessor(Key k1, Key k2);

    /**
     * leetcode
     */
    BinaryTree largestBST();

    /**
     * leetcode
     */
    Collection<Key> edgesNodes();
}