package leetcode;

/**

 Sort a linked list in O(n log n) time using constant space complexity.

 SOLUTION:

 this is a bottom up merge sort
 e.g. 4->3->2->9->5

 - first we split to lists with len = 1 (ngroup).
 - we split by pairs
 4->null
 3->null
 2->9->5

 - then we merge a group
 3->4

 - split next pair
 3->4->null
 2->null
 9->null
 5->null

 - then we merge  the second group
 3->4->null
 2->9->null
 5->null

 tail of the previous group now points to the head of the next group
 3->4 -> 2->9-> 5->null

 now set ngroup = 2 (each pair of elements is sorted)
 3->4->null
 2->9->null
 5->null

 merge first two and second two
 2->3->4->9 -> null
 5->null

 now set ngroup = 4 (each four elements are sorted)
 merge
 2->3->4->9 -> null
 with
 5->null

 result: 2-3-4-5-9


 */
public class SortList {
    public ListNode sortList(ListNode head) {
        int ngroup = 1;
        int totalSize = 0;

        //calculate total size
        ListNode cur = head;
        while (cur != null) {totalSize++; cur = cur.next;}
        if(totalSize <= 1) return head;

        ListNode newGlobalHead = head;

        while (ngroup <= totalSize) {
            ListNode head1 = newGlobalHead;
            ListNode previousTail = null;
            boolean newLoop = true;

            //while not reached null
            while(true) {
                if(head1 == null) {
                    break;
                }
                ListNode tail1 = getNextNthElement(ngroup - 1, head1);

                ListNode head2 = tail1.next;
                if(head2 == null) {
                    if(previousTail != null) previousTail.next = head1;
                    break;
                }
                ListNode tail2 = getNextNthElement(ngroup - 1, head2);

                ListNode nextUsortedNode = tail2.next;
                tail1.next = null;
                tail2.next = null;

                //MERGE
                ListNode mergedHead = mergeTwoLists(head1, head2);
                if(newLoop) {
                    newGlobalHead = mergedHead;
                    newLoop = false;
                }
                if(previousTail != null) {
                    previousTail.next = mergedHead;
                }

                previousTail = (tail1.next == null)?tail1:tail2;
                head1 = nextUsortedNode;
            }

            ngroup = ngroup*2;

        }
        return newGlobalHead;
    }

    public ListNode getNextNthElement(int n, ListNode head) {
        ListNode cur = head;
        for (int i = 0; cur != null && cur.next != null && i < n; i++) cur = cur.next;
        return cur;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode head;
        if (l1.val < l2.val) {
            head = l1;
            l1 = l1.next;
        } else {
            head = l2;
            l2 = l2.next;
        }

        ListNode cur = head;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        if(l1 == null) cur.next = l2;
        else cur.next = l1;

        return head;
    }
}
