package corman.Trees;

import java.util.List;

public interface BinaryTree<Key extends Comparable> {

    void put(Key value);

    Key next(Key key);

    Key previous(Key key);

    void delete(Key value);

    Key max();

    Key min();

    BinaryTree<Key> merge(BinaryTree<Key> tree);

    boolean equals(BinaryTree<Key> tree);

    Object serialize();

    /**
     * leetcode
     */
    BinaryTree<Key> deserializer(Object o);

    /**
     * leetcode
     */
    BinaryTree<Key> subTree(Key key);

    void balance();

    void constructFromInOrderAndPreOrder(List<Key> in, List<Key> pre);

    void constructFromInOrderAndPostOrder(List<Key> in, List<Key> post);

    /*
            _______7______
       /              \
    __10__          ___2
   /      \        /
   4       3      _8
            \    /
             1  11
The preorder and inorder traversals for the binary tree above is:

preorder = {7,10,4,3,1,2,8,11}
inorder = {4,10,3,1,7,11,8,2}

     */


    /*
    Print Edge Nodes (Boundary) of a Binary Tree


Print all edge nodes of a complete binary tree anti-clockwise.
That is all the left most nodes starting at root, then the leaves left to right and finally all the rightmost nodes.
In other words, print the boundary of the tree.

Variant: Print the same for a tree that is not complete.
     */


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

    /**
     * leetcode
     */
    List<List<Key>>  levelOrderTraversalToLists();

    int height();

    /**
     * leetcode
     */
    int maxPassSum();

    /**
     * leetcode
     */
    boolean isBalanced();

/*
    Sum Root to Leaf Numbers
    For example,

        1
       / \
      2   3

    The root-to-leaf path 1->2 represents the number 12.
    The root-to-leaf path 1->3 represents the number 13.

    Return the sum = 12 + 13 = 25.
*/

    int sumRootToLeafNumbers();

    /* leetcode */
    void sortedArraytoBST(Key[] num);


}