package leetcode;
import java.util.*;
/**
 *Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 *
 * N total elements
 * M - number of queues
 *
 * Complexity:
 * N * log(M)

 */
public class MergeSortedLists {
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists.size() == 0) return null;

        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(lists.size(), new LeetListComporator());
        for(ListNode curHead: lists) {
            if(curHead != null) pq.add(curHead);
        }

        ListNode newhead = pq.poll();
        ListNode newtail = newhead;
        if (newhead == null) return null;
        if(newhead.next != null) pq.add(newhead.next);

        while(pq.size() > 0) {
            ListNode curSmallest = pq.poll();
            if(curSmallest.next != null) pq.add(curSmallest.next);
            newtail.next = curSmallest;
            newtail = curSmallest;
        }
        return newhead;
    }
}

class LeetListComporator implements Comparator<ListNode> {

    @Override
    public int compare(ListNode o, ListNode o2) {
        return o.val- o2.val;
    }
}
