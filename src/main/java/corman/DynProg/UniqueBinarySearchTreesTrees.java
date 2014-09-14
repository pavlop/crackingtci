package corman.DynProg;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

 For example,
 Given n = 3, your program should return all 5 unique BST's shown below.

 1         3     3      2      1
 \       /     /      / \      \
 3     2     1      1   3      2
 /     /       \                 \
 2     1         2                 3
 */
public class UniqueBinarySearchTreesTrees {


    public List<TreeNode> generateTrees(int n) {
        List<Integer> numbers = new ArrayList<Integer>(n);
        for(int i = 1; i <= n; i++) {
            numbers.add(i);
        }
        return getTreesFromNumbersList(numbers);
    }

    private List<TreeNode> getTreesFromNumbersList( List<Integer> nums) {
        List<TreeNode> trees = new ArrayList<TreeNode>();
        if(nums.size() == 0) {
            trees.add(null);
            return trees;
        }
        if(nums.size() == 1) {
            trees.add(new TreeNode(nums.get(0)));
            return trees;
        }
        for (int i = 0; i < nums.size(); i++) {
            Integer curRootVal = nums.get(i);

            List<Integer> leftNumbers = nums.subList(0, i);
            List<Integer> rightNumbers = nums.subList(i+1, nums.size());

            List<TreeNode> leftRoots = getTreesFromNumbersList(leftNumbers);
            List<TreeNode> rightRoots = getTreesFromNumbersList(rightNumbers);

            List<TreeNode> allTreesWithCurRoot = makeCortesianProductOfAllSubrees(curRootVal, leftRoots, rightRoots);
            trees.addAll(allTreesWithCurRoot);
        }
        return trees;
    }

    private List<TreeNode> makeCortesianProductOfAllSubrees(Integer rootValue, List<TreeNode> leftRoots, List<TreeNode> rightRoots) {
        List<TreeNode> topRoots = new ArrayList<TreeNode>();
        for (TreeNode leftRoot: leftRoots) {
            for (TreeNode rightRoot: rightRoots) {
                TreeNode root = new TreeNode(rootValue);
                root.left = leftRoot;
                root.right = rightRoot;
                topRoots.add(root);
            }
        }
        return topRoots;
    }

    @Test
    public void test() {
        System.out.println();
        Assert.assertEquals(1, generateTrees(1).size());
        Assert.assertEquals(2, generateTrees(2).size());
        Assert.assertEquals(5, generateTrees(3).size());

    }



    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }

}