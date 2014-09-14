package crackinginterview.datastructures.x2.linkedLists;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by pavlop on 2/27/14.
 */
public class RemoveDuplicatesUnsortedList {
    public static<TYPE> void perform(SingleNode<TYPE> head) {
        //
        for(SingleNode<TYPE> cur = head; cur!= null && cur.next != null; cur = cur.next) {
            System.out.println("cur = "+cur);
            for (SingleNode next = cur.next, previous = cur; next != null ;  ) {
                if (cur.value.equals(next.value)) {
                    System.out.println("Found duplicate "+next +" after "+previous);
                    //remove
                    previous.next = next.next;
                    //go forward for next only
                    next = next.next;
                }
                else {
                    //go forward for next and prevoius
                    previous = next;
                    next = next.next;
                }
            }
        }
    }

    @Test
    public void doTest() {
        MySinglyLinkedList<Integer> input = new MySinglyLinkedList<Integer>();

        //input = Arrays.asList(new int[]{1, 2, 3});
        input.add(1); input.add(1); input.add(2); input.add(2); input.add(3); input.add(3);
        assertEquals("[1, 1, 2, 2, 3, 3]", MySinglyLinkedList.toJavaList(input.head)+"");
        perform(input.head);
        assertEquals("[1, 2, 3]", MySinglyLinkedList.toJavaList(input.head)+"");
    }
}


