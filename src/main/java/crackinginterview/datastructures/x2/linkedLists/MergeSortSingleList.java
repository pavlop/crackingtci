package crackinginterview.datastructures.x2.linkedLists;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by pavlop on 3/1/14.
 */
public class MergeSortSingleList {
    public static void perform(SingleNode<Integer> head, int len) {
        divide(head,len);
    }

    public static SingleNode<Integer> divide(SingleNode<Integer> head, int len) {
        if(len <= 1 ) {
            System.out.println("listOfOneNode :"+head.value);
            return head;
        }

        int leftLength = len/2;
        int rightLength = len - leftLength;
        int midStartsAt = leftLength;
        // since we start numeration from 0

        SingleNode<Integer> rightHead = MySinglyLinkedList.getNodeAtPosition(head, midStartsAt);

        //continue for left part of the list
        head = divide(head, leftLength);
        rightHead = divide(rightHead, rightLength);
        return merge (head, leftLength, rightHead, rightLength);
    }



    public static SingleNode<Integer> merge(SingleNode<Integer> head1, int len1, SingleNode<Integer> head2, int len2) {
        System.out.println("doing merge "+MySinglyLinkedList.subList(head1, len1)+" with "+MySinglyLinkedList.subList(head2, len2));

            SingleNode<Integer> cur1 = head1;
            SingleNode<Integer> cur2 = head2;
            SingleNode<Integer> newLocalHead;
            SingleNode<Integer> newLocalPrevious;
            //java.lang.Integer.
            int i1=0;
            int i2=0;

            if (head1.value < head2.value) {
                newLocalHead = head1;
                cur1 = cur1.next;
                i1++;
            } else {
                newLocalHead = head2;
                i2++;
                cur2 = cur2.next;
            }

            newLocalPrevious = newLocalHead;
            System.out.println("local head = "+newLocalHead.value);

            while(newLocalPrevious != null && (i1<len1 || i2<len2)) {
                if ((i1<len1) && (cur2 == null || cur1.value  <= cur2.value)) {
                    newLocalPrevious.next = cur1;
                    cur1 = cur1.next;
                    i1++;
                } else if ((i2<len2)) {
                    newLocalPrevious.next = cur2;
                    cur2 = cur2.next;
                    i2++;
                }
                newLocalPrevious = newLocalPrevious.next;
            }
            System.out.println("ResultList afte merge:"+MySinglyLinkedList.subList(newLocalHead, len1+len2));
            return newLocalHead;
        }


    @Test
    public void doTest() {
        MySinglyLinkedList<Integer> input = new MySinglyLinkedList<Integer>();
        input.add(1); input.add(3); input.add(4);  input.add(2); input.add(5);
        perform(input.head, 5);
        assertTrue(true);
    }
}
