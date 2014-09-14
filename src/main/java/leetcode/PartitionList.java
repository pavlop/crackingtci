package leetcode;

/**

 Given a linked list and a value x,
 partition it such that all nodes less than x come before nodes greater than or equal to x.

 You should preserve the original relative order of the nodes in each of the two partitions.

 For example,
 Given 1->4->3->2->5->2 and x = 3,
 return 1->2->2->4->3->5.

 */
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode cur = head;
        ListNode lessFakeHead = new ListNode(0);
        ListNode lessTail = null;
        ListNode moreFakeHead = new ListNode(0);
        ListNode moreTail = null;
        while (cur != null) {
            if(cur.val < x) lessTail = appendAndReturnTail(lessFakeHead, lessTail, cur);
            else moreTail = appendAndReturnTail(moreFakeHead, moreTail, cur);
        }
        if(lessTail != null) {
            lessTail.next = moreFakeHead.next;
            return lessFakeHead.next;
        } else {
            return moreFakeHead.next;
        }
    }
    public ListNode appendAndReturnTail(ListNode fakehead, ListNode tail, ListNode toAppend) {
        if(tail == null) {
            fakehead.next = toAppend;
        } else {
            tail.next = toAppend;
        }
        return toAppend;
    }
}
