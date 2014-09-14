package leetcode;

import org.junit.Test;

/**
 Given a linked list, remove the nth node from the end of list and return its head.

 For example,

 Given linked list: 1->2->3->4->5, and n = 2.

 After removing the second node from the end, the linked list becomes 1->2->3->5.
 Note:
 Given n will always be valid.
 Try to do this in one pass.

 */



public class RemoveNthNode {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        //skip first n-1 elements
        int skipCounter = 0;
        for (int i = 0; i < n; i++) {
            if(fast == null) return null;
            fast = fast.next;
            skipCounter++;
        }

        if(skipCounter < n -1) return head.next;

        ListNode slow = head;
        while (fast  != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        if (fast == null) return head.next;
        slow.next =  slow.next.next;
        return head;
    }


    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    @Test
    public void doTest() {
        ListNode head = new ListNode(1);
        ListNode el2 = new ListNode(2);
        head.next = el2;

        System.out.println(removeNthFromEnd(head, 2));
    }
}

