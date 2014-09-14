package crackinginterview.datastructures.x4.TreesAndGraphs;

import dynamicProgramming.MyArrayUtils;

import java.util.Arrays;

/**
 * Created by pavlop on 4/5/14.
 *
 * Implement a function to check if a binary tree is balanced.
 * For the purposes of this question,
 * a balanced tree is defined to be a tree
 * such that the heights of the two subtrees
 * of any node never differ by more than one.
 *
 */
public class IsBinaryTreeBalanced {
    public static boolean checkIfBalanced(TNode head) {
        int[] minmax = getMinAndMaxDepth(head);
        System.out.println("minmax:"+ Arrays.toString(minmax));
        return (minmax[1] - minmax[0] <= 1);
    }

    private static int[] getMinAndMaxDepth(TNode curNode) {
        if (curNode == null) {
            return new int[]{0 /*Min*/, 0/*Min*/, 0/*1 if need to abort*/};
        } else {
            int[] minmaxLeft = getMinAndMaxDepth(curNode.left);
            //return if already clear that three is not balanced
            if (minmaxLeft[2] == 1) return minmaxLeft;

            int[] minmaxRight = getMinAndMaxDepth(curNode.right);
            //return if already clear that three is not balanced
            if (minmaxRight[2] == 1) return minmaxRight;

            int[] res = new int[3];
            res[0] = 1 + MyArrayUtils.getMinElement(minmaxLeft[0], minmaxRight[0]);
            res[1] = 1 + MyArrayUtils.getMaxElement(minmaxLeft[1], minmaxRight[1]);
            //set abort flag if already clear that three is not balanced
            res[2] = (res[1] - res[0] <= 1)?0:1;

            return res;
        }
    }
}
