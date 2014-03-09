package interview.datastructures.linkedLists;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by pavlop on 2/27/14.
 */
public class RemoveNodeInTheMiddle {
    public static<TYPE> void perform(MySinglyLinkedList<TYPE> list) {

        Node<TYPE> head = list.head;
        if(head == null) return;

        //remove the only element
        if(head.next == null) {
            list.head = null;
            return;
        }

        //2 elements only. remove head
        if(head.next.next == null) {
            list.head = head.next;
            return;
        }

        //finding middle element
        Node<TYPE> fast = head;
        Node<TYPE> slow = head;
        Node<TYPE> previousToDeleted = null;
        while (fast.next != null && fast.next.next != null) {
            previousToDeleted = slow;
            //couter++;
            fast = fast.next.next;
            slow = slow.next;
        }

        //remove element after previousToDeleted
        previousToDeleted.next = previousToDeleted.next.next;

    }


    @Test
    public void doTest() {
        MySinglyLinkedList<Integer> input = new MySinglyLinkedList<Integer>();

        //input = Arrays.asList(new int[]{1, 2, 3});
        input.add(1); input.add(2); input.add(3); input.add(4); input.add(5); input.add(6);
        perform(input);
        assertEquals("[1, 2, 4, 5, 6]", MySinglyLinkedList.toJavaList(input.head)+"");

        perform(input);
        assertEquals("[1, 2, 5, 6]", MySinglyLinkedList.toJavaList(input.head)+"");

        perform(input);
        assertEquals("[1, 5, 6]", MySinglyLinkedList.toJavaList(input.head)+"");

        perform(input);
        assertEquals("[1, 6]", MySinglyLinkedList.toJavaList(input.head)+"");

        perform(input);
        assertEquals("[6]", MySinglyLinkedList.toJavaList(input.head)+"");

        perform(input);
        assertEquals("[]a", MySinglyLinkedList.toJavaList(input.head)+"");

    }
}


