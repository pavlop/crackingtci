package interview.datastructures.linkedLists;

import org.junit.Test;

import java.util.Stack;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by pavlop on 3/12/14.
 */
public class IsListPolyndrom {

    public static <T>  boolean isPolyndrom (Node<T> head) {
        //one element check
        if (head.next==null) return true;

        Stack<T> firstFalf =  new Stack<T>();
        Node<T> slow = head;
        Node<T> fast = head;
        while(fast!=null&&fast.next!=null) {
            firstFalf.push(slow.value);
            slow = slow.next;
            fast = fast.next.next;
        }

        System.out.println("middle reached: firstFalf="+firstFalf.size());
        //is even or odd?
        //if fatt != null - odd
        if (fast!=null) {
            //skip middle element
            slow = slow.next;
        }


        // continue loop for slow
        // slow is pointing to the middle now
        while(slow != null) {
            System.out.println("comparing list:"+slow.value+" stack:"+ firstFalf.peek());
            if(!slow.value.equals(firstFalf.pop())) return false;
            slow = slow.next;

        }


        return true;
    }

    @Test
    public void test() {
        MySinglyLinkedList<Integer> input = new MySinglyLinkedList<Integer>();
        input.add(3);
        assertTrue(isPolyndrom(input.head));

        input.add(2);
        assertFalse(isPolyndrom(input.head));

        input.add(3);
        assertTrue(isPolyndrom(input.head));

    }
}
