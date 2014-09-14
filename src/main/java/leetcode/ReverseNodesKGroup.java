package leetcode;

import org.junit.Test;

import java.util.*;

/**

 Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

 If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

 You may not alter the values in the nodes, only nodes itself may be changed.

 Only constant memory is allowed.

 For example,
 Given this linked list: 1->2->3->4->5

 For k = 2, you should return: 2->1->4->3->5

 For k = 3, you should return: 3->2->1->4->5

 */
public class ReverseNodesKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null) return null;

        ListNode[] newHaedAndTail = swapAndReturnFirst(head, k);
        ListNode newGlobalHead = newHaedAndTail[0];
        if(newGlobalHead == head) return head;

        ListNode previous = head;
        ListNode headOfKGropu = head.next;
        while (headOfKGropu != null) {
            newHaedAndTail = swapAndReturnFirst(headOfKGropu, k);
            ListNode newHeadOfKGroup = newHaedAndTail[0];
            ListNode newTailOfKGroup = newHaedAndTail[1];

            if (newHeadOfKGroup == headOfKGropu) break;
            previous.next = newHeadOfKGroup;
            previous = newTailOfKGroup;
            headOfKGropu = newTailOfKGroup.next;
        }
        return newGlobalHead;
    }

    public static ListNode[] swapAndReturnFirst(ListNode first, int k) {
        //check if swappable
        ListNode cur = first;
        Stack<ListNode> nodes = new Stack<ListNode>();
        for (int i = 0; i < k;  i++) {
            if (cur == null) return new ListNode[]{first, null};
            nodes.push(cur);
            cur = cur.next;
        }
        ListNode firstAfterKGroup = cur;
        ListNode newFirst = nodes.pop();
        ListNode previous = newFirst;
        while(nodes.size()>0) {
            ListNode node = nodes.pop();
            previous.next = node;
            previous = node;
        }
        previous.next = firstAfterKGroup;
        return new ListNode[]{newFirst, previous};
    }

    @Test
    public void doTest() {
        ListNode head = new ListNode(1);
        ListNode e2 = new ListNode(2);
        ListNode e3 = new ListNode(3);
        head.next=e2;
        e2.next=e3;

        //reverseKGroup(head, 1);
        ListNode res = reverseKGroup(head,3 );
        System.out.println(res + " " + res.next + " "+res.next.next);
    }
}
