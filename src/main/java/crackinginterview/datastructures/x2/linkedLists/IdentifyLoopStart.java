package crackinginterview.datastructures.x2.linkedLists;

import org.junit.Test;

import static junit.framework.Assert.assertSame;
import static junit.framework.TestCase.assertNull;

/**
 * Created by pavlop on 3/11/14.
 */
public class IdentifyLoopStart {
    public static <T> SingleNode<T> findLoopStart(SingleNode<T> head) {
        //has only two elements
        if(head == null || head.next == null) {
            return null;
        }

        SingleNode<T> fast = head;
        SingleNode<T> slow = head;

        while (fast !=null&&fast.next!=null) {
            fast = fast.next.next;
            slow = slow.next;
            // passedSlow++;
            if(fast == slow) break;
        }

        if(fast == null || fast.next == null) {
            return null;
        }

        /*
         Move slow to Head. Keep fast at Meeting Point.
         Each are k * steps from the Loop Start.
         If they move at the same pace, * they must meet at Loop Start.
         */
        slow = head;
        while (slow !=fast){
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }

    @Test
    public void haveNoLoop() {
        MySinglyLinkedList<Integer> input = new MySinglyLinkedList<Integer>();
        input.add(1);
        assertNull(findLoopStart(input.head));

        input.add(2);input.add(3);
        assertNull(findLoopStart(input.head));
    }

    @Test
    public void haveLoop() {
        MySinglyLinkedList<Integer> input = new MySinglyLinkedList<Integer>();
        SingleNode<Integer> first = new SingleNode<Integer>(1);
        SingleNode<Integer> loopingNode = new SingleNode<Integer>(9, first);
        input.add(first);input.add(2); input.add(3); input.add(loopingNode);
        assertSame(first, findLoopStart(input.head));
    }
}
