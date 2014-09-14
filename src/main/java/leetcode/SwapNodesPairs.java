package leetcode;

/**
 Given a linked list, swap every two adjacent nodes and return its head.

 For example,
 Given 1->2->3->4, you should return the list as 2->1->4->3.

 Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.

 */
public class SwapNodesPairs {
    public ListNode swapPairs(ListNode head) {
        if(head == null) return null;
        if(head.next == null) return head;

        head = swapAndReturnFirst(head, head.next);

        ListNode cur = head.next.next;
        ListNode previous = head.next;
        while (cur != null && cur.next != null) {
            previous.next = swapAndReturnFirst(cur, cur.next);
            previous = cur;
            cur = cur.next;
        }
        return head;
    }

    public static ListNode swapAndReturnFirst(ListNode first, ListNode second) {
        first.next = second.next;
        second.next = first;
        return second;
    }
}
