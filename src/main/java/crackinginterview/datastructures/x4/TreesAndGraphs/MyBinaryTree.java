package crackinginterview.datastructures.x4.TreesAndGraphs;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by pavlop on 4/5/14.
 */
public class MyBinaryTree {
    public TNode head;
    public void addToSearchSubtree(TNode parent, int value) {
        if(parent == null) {
            head = new TNode (null, null, value);
        } else if (value < parent.value) {
            if (parent.left == null) {
                parent.left = new TNode (null, null, value);
                return;
            } else {
                addToSearchSubtree(parent.left, value);
            }
        } else if (value > parent.value) {
            if (parent.right == null) {
                parent.right = new TNode (null, null, value);
                return;
            } else {
                addToSearchSubtree(parent.right, value);
            }
        }
    }

    public List<Integer> asSortedList(TNode startAt, List <Integer> toBeFilled) {
        System.out.println("cur:"+startAt);
        if (startAt != null) {
            asSortedList(startAt.left, toBeFilled);
            toBeFilled.add(startAt.value);
            asSortedList(startAt.right, toBeFilled);
        }
        System.out.println("buffer"  + toBeFilled);
        return toBeFilled;
    }

    @Override
    public String toString() {
        Queue<TNode> buffer = new LinkedList<TNode>();
        buffer.add(head);

        StringBuffer sb = new StringBuffer();
        while (!buffer.isEmpty()) {
            TNode cur = buffer.poll();
            if (cur == null) continue;
            sb.append(cur.value);
            if (cur.left != null) buffer.add(cur.left);
            if (cur.right != null) buffer.add(cur.right);
        }
        return sb.toString();

    }

}
