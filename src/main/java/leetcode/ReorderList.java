package leetcode;

/**
 *
 *
 *
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        // split the list into two (1, 2, .. mid   and  mid+1, mid+2, ...)
        // revert second list
        // merge two lists

        if(head == null||head.next == null) return;
        int len = 0;

        for (ListNode cur = head; cur!= null;  cur = cur.next) len++;

        int middle = (len%2 == 1) ? len/2 + 1: len/2 ;

        ListNode midNode = head;
        for (int i = 0; i < middle - 1; i++)
            midNode = midNode.next;

        ListNode head1 = head;
        ListNode head2 = midNode.next;
        midNode.next = null;
        head2 = revertListAndReturnHead(head2);

        //MERGE
        ListNode cur1 = head1;
        ListNode cur2 = head2;
        while (cur1 != null && cur2 != null) {
            ListNode nextCur1 =  cur1.next;
            ListNode nextCur2 =  cur2.next;
            cur1.next = cur2;
            cur2.next = nextCur1;
            cur1 = nextCur1;
            cur2 = nextCur2;
        }

    }

    ListNode revertListAndReturnHead(ListNode cur) {
        ListNode previous = null;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = previous;
            previous = cur;
            cur = next;
        }
        return previous;
    }
}
