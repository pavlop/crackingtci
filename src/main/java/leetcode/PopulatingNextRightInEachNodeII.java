package leetcode;

/**

 traverse left to right
 remember reference to the start of the next line

 */
public class PopulatingNextRightInEachNodeII {
    public void connect(TreeLinkNode root) {
        TreeLinkNode nextLevelStart = root;
        while (nextLevelStart!=null) {
            TreeLinkNode cur = nextLevelStart;  //1
            TreeLinkNode[] newCurAndNextChild = getNewCurAndNextChild(cur, null);   //2
            cur = newCurAndNextChild[0];
            TreeLinkNode curChild = newCurAndNextChild[1];
            nextLevelStart = curChild; //2
            while (curChild != null) {
                newCurAndNextChild = getNewCurAndNextChild(cur, curChild);   //2
                cur = newCurAndNextChild[0];
                TreeLinkNode nextChild = newCurAndNextChild[1];
                curChild.next = nextChild;
                curChild = nextChild;
            }
        }
    }

    TreeLinkNode[] getNewCurAndNextChild(TreeLinkNode cur, TreeLinkNode curChild) {
        while (cur!=null) {
            if(curChild == null && cur.left != null) return  new TreeLinkNode[]{cur, cur.left};
            if(curChild == null && cur.right != null) return new TreeLinkNode[]{cur, cur.right};

            if ( cur.left != null && curChild != cur.left && curChild != cur.right) return new TreeLinkNode[]{cur, cur.left};
            if ( cur.right != null && curChild != cur.right) return new TreeLinkNode[]{cur, cur.right};
            cur = cur.next;
        }
        return new TreeLinkNode[]{cur, null};
    }

     class TreeLinkNode {
         int val;
         TreeLinkNode left, right, next;
         TreeLinkNode(int x) { val = x; }
    }
}
