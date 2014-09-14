package leetcode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given two linked lists representing two non-negative numbers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 */
public class AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Queue<Integer> result = new LinkedList<Integer>();
        int previousReminings = 0;

        while (l1 != null && l2 != null) {
            //System.out.println("increment both: l1:"+l1.val+" l2:"+l2.val);
            int curSum = (l1.val+l2.val + previousReminings);
            int curNodeVal = curSum % 10;
            previousReminings = curSum>=10?1:0;
            result.add(curNodeVal);
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int curSum = (l1.val + previousReminings);
            int curNodeVal = curSum % 10;
            previousReminings = curSum>=10?1:0;
            result.add(curNodeVal);
            l1 = l1.next;
        }

        while (l2 != null) {
            int curSum = (l2.val + previousReminings);
            int curNodeVal = curSum % 10;
            previousReminings = curSum>=10?1:0;
            result.add(curNodeVal);
            l2 = l2.next;
        }

        if (previousReminings != 0) {
            result.add(previousReminings);
        }

        //System.out.println("stack res "+result);
        if (result.isEmpty()) return new ListNode(0);


        ListNode resHead = new ListNode(result.poll());
        ListNode curNode = resHead;
        while (!result.isEmpty()) {
            curNode.next = new ListNode(result.poll());
            curNode = curNode.next;
        }
        return resHead;
    }




//    Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//    Output: 7 -> 0 -> 8
    @Test
    public void doTest() {
        ListNode head1 = new ListNode(2);
        head1.next =  new ListNode(4);
        head1.next.next =  new ListNode(3);

        ListNode head2 = new ListNode(5);
        head2.next =  new ListNode(6);
        head2.next.next =  new ListNode(4);

        ListNode res = addTwoNumbers(head1, head2);
        System.out.println("addTwoNumbers head val :"+ res.val+""+res.next.val+""+res.next.next.val);
    }

    //    {7,0,3,6,7,3,2,1,5}, {9,2,5,5,6,1,2,2,4}
    @Test
    public void doTest2() {
        ListNode head1 = new ListNode(7);
        head1.next =  new ListNode(0);
        head1.next.next =  new ListNode(3);
        head1.next.next.next  =  new ListNode(6);
        head1.next.next.next.next  =  new ListNode(7);
        head1.next.next.next.next.next  =  new ListNode(3);
        head1.next.next.next.next.next.next =  new ListNode(2);
        head1.next.next.next.next.next.next.next =  new ListNode(1);
        head1.next.next.next.next.next.next.next.next =  new ListNode(5);

        ListNode head2 = new ListNode(9);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(5);
        head2.next.next.next = new ListNode(5);
        head2.next.next.next.next = new ListNode(6);
        head2.next.next.next.next.next =  new ListNode(1);
        head2.next.next.next.next.next.next = new ListNode(2);
        head2.next.next.next.next.next.next.next = new ListNode(2);
        head2.next.next.next.next.next.next.next.next = new ListNode(4);
        ListNode res = addTwoNumbers(head1, head2);
        System.out.println("addTwoNumbers head val :"+ res.val+""+res.next.val+""+res.next.next.val);
    }

    @Test
    public void doTest3() {
        ListNode head1 = new ListNode(5);
        ListNode head2 = new ListNode(5);
        ListNode res = addTwoNumbers(head1, head2);
        System.out.println("addTwoNumbers head val :"+res.val+""+res.next.val );
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}
