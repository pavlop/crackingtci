package crackinginterview.datastructures.x4.TreesAndGraphs;

/**
 * 4.7 Design an algorithm and write code to find
 * the first common ancestor of two nodes in a binary tree.
 * Avoid storing additional nodes in a datastructure.
 * NOTE:This is not necessarily a binary search tree.
    Note2: No links to parent
 */
public class TreeCommonParent {
    public static TNode findClosestParent(TNode startAt, TNode n1, TNode n2) {
        if (n1 == n2) return n1;
        return findCommonParentRecursively(startAt, n1, n2, 0).commonParent;
    }

    private static ClosesParentResult findCommonParentRecursively(TNode startAt, TNode n1, TNode n2, int alreadyFound) {
        if (alreadyFound == 2) return new ClosesParentResult(null, true);
        if (startAt == null) return new ClosesParentResult(null, false);
        boolean currentlyInNode = false;
        ClosesParentResult result = new ClosesParentResult(null, false);
        if (startAt == n1 || startAt == n2) {
            alreadyFound++;
            currentlyInNode = true;
            result.nodeFound = true;
            if (alreadyFound == 2) return result;
        }

        ClosesParentResult left = findCommonParentRecursively(startAt.left, n1, n2, alreadyFound);
        ClosesParentResult right = findCommonParentRecursively(startAt.right, n1, n2, alreadyFound);

        if (left.commonParent != null) return left;
        if (right.commonParent != null) return right;

        if ((left.nodeFound && right.nodeFound || (currentlyInNode && (left.nodeFound||right.nodeFound )))) {
            result.commonParent = startAt;
            result.nodeFound = true;
        } else if (left.nodeFound||right.nodeFound||currentlyInNode) {
            result.nodeFound = true;
        }

        return result;

    }
}

class ClosesParentResult {
    public TNode commonParent;
    public boolean nodeFound;

    ClosesParentResult(TNode commonParent, boolean nodeFound) {
        this.commonParent = commonParent;
        this.nodeFound = nodeFound;
    }
}
