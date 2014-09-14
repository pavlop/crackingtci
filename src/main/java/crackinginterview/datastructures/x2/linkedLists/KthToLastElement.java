package crackinginterview.datastructures.x2.linkedLists;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

/**
 * Created by pavlop on 3/8/14.
 */
public class KthToLastElement{
    public static <T> T perform (SingleNode<T> head, int kToLaast) {
        SingleNode<T> fast = head;

        //fast pointer moves K positions forward
        for (int i = 0; i< kToLaast && fast != null; i++) {
            fast = fast.next;
        }

        //less than K elements in the list
        if(fast == null) return null;

        //asume there are no loops in the list
        SingleNode<T> slow = head;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow.value;
    }


    @Test
    public void testFidingKToLastEelment() {
        MySinglyLinkedList<Integer> input = new MySinglyLinkedList<Integer>();
        input.add(1);input.add(2);input.add(3);
        //int kToLastValue =  KthToLastElement.perform(input.head, 1);
        assertEquals(3, (int)perform(input.head, 0));
        assertEquals(2, (int)perform(input.head, 1));
        assertEquals(1, (int)perform(input.head, 2));
        assertNull(perform(input.head, 4));



    }
}
