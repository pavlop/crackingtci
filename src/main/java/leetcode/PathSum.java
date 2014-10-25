package leetcode;

/**
 Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

 For example:
 Given the below binary tree and sum = 22,
 5
 / \
 4   8
 /   / \
 11  13  4
 /  \      \
 7    2      1
 return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

 */
public class PathSum {
    public boolean hasPathSum(TreeNodePavel root, int sum) {
        TreeNodePavel tnp = new TreeNodePavel(0);
        if(sum == 0) return true;
        boolean result = false;
        if(root != null && sum > 0) {
            result = (hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val));
        }
        return result;
    }

    public  class TreeNodePavel {
        int val;
        TreeNodePavel left;
        TreeNodePavel right;
        TreeNodePavel(int x) { val = x; }
    }

    public static void main(String s[]) {
        PathSum ps = new PathSum();
        ps.hasPathSum(ps.new TreeNodePavel(0), 0);
    }
}



