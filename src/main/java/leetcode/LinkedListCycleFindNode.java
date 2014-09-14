package leetcode;

/**
 *
 *Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

 Follow up:
 Can you solve it without using extra space?
 *
 */
public class LinkedListCycleFindNode {
    public ListNode detectCycle(ListNode head) {
        ListNode quick = head;
        ListNode slow = head;

        boolean hasCycle = false;
        while (quick!= null && quick.next != null) {
            quick = quick.next.next;
            slow = slow.next;
            if(slow == quick) {
                hasCycle = true;
                break;
            }
        }

        if(!hasCycle) return null;

        //the slow pointer is K positions to the cycle start
        //so slow and ehad will meet at the same point - cycle start
        while (head != slow) {
            head = head.next;
            slow = slow.next;
        }
        /*int cycleSize = 0;
        quick = quick.next.next;
        slow = slow.next;
        while (quick != slow) {
            quick = quick.next.next;
            slow = slow.next;
            cycleSize++;
        }

        int cycleStartPosition = positionsTillMeet - cycleSize;
        for (int i = 0; i < cycleStartPosition; i++) {
            head = head.next;
        }*/

        return head;
    }

}
