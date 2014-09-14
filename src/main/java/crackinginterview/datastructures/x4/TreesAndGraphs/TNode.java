package crackinginterview.datastructures.x4.TreesAndGraphs;

public class TNode {
    public TNode left, right, parent;
    public int value;

    public TNode(TNode left, TNode right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    @Override
    public String toString() {
        return "TNode{" +
                //"left=" + left +
                //", right=" + right +
                "value=" + value +
                '}';
    }
}
