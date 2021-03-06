package crackinginterview.datastructures.x2.linkedLists;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by pavlop on 2/27/14.
 */
public class SplitAroundSeparatorNode {

    SingleNode<Integer> headSmaller = null, tailSmaller = null, headBigger = null, tailBigger = null;

    public void perform(MySinglyLinkedList<Integer> list, SingleNode<Integer> separatorNode) {
        SingleNode<Integer> cur = list.head;
        while (cur != null) {
            if(cur == separatorNode) {
                cur = cur.next;
                continue;
            }
            SingleNode<Integer> next = cur.next;
            if(cur.value < separatorNode.value) {
                if(headSmaller == null) {
                    headSmaller = cur;
                    tailSmaller = cur;
                } else {
                    tailSmaller.next = cur;
                    tailSmaller = cur;
                }
            } else {
                if(headBigger == null) {
                    headBigger = cur;
                    tailBigger = cur;
                } else {
                    tailBigger.next = cur;
                    tailBigger = cur;
                }
            }
            cur.next = null;
            cur = next;
        }

        //TODO
        // NEED TO CHECK IF REFERENCES == NULL !
        list.head = headSmaller;
        tailSmaller.next = separatorNode;
        separatorNode.next = headBigger;

    }

    @Test
    public void doTest() {
        MySinglyLinkedList<Integer> input = new MySinglyLinkedList<Integer>();

        input.add(1); input.add(4); input.add(3);  input.add(5); input.add(6);input.add(2);
        SingleNode<Integer> separator = input.head.next.next;//3

        perform(input, separator);
        assertEquals("[1, 2, 3, 4, 5, 6]", MySinglyLinkedList.toJavaList(input.head)+"");
    }
}


