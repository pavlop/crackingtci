package crackinginterview.datastructures.x4.TreesAndGraphs;

import crackinginterview.datastructures.x3.StacksAndQues.Stack;

/**
 * Created by pavlop on 4/11/14.
 *
 * You are given a binary tree in which each node contains a value.
 * Design an algorithm to print all paths which sum to a given value.
 * The path does not need to start or end at the root or a leaf.
 *
 */
public class GetTargetSumFromATree {

    public static void  printPathsToGetTargetSum (TNode treeStart, int targetSum) {
        if (treeStart != null) {
            printPathsToGetTargetSumRecursive(treeStart, targetSum, 0, new Stack<Integer>());
            printPathsToGetTargetSumRecursive(treeStart.left, targetSum, 0, new Stack<Integer>());
            printPathsToGetTargetSumRecursive(treeStart.right, targetSum, 0, new Stack<Integer>());
        }

    }

    private static void printPathsToGetTargetSumRecursive (TNode treeStart, int targetSum, int tmpSum, Stack<Integer> path) {
        if (treeStart == null)  return;
        int valueToAdd = treeStart.value;

        tmpSum += valueToAdd;
        path.push(valueToAdd);

        if (tmpSum == targetSum) {
            System.out.println("Path Found:"+path);
            path.pop();
            return;
        } else {
            printPathsToGetTargetSumRecursive(treeStart.left, targetSum, tmpSum, path);
            printPathsToGetTargetSumRecursive(treeStart.right, targetSum, tmpSum, path);
        }
        path.pop();
    }

}
