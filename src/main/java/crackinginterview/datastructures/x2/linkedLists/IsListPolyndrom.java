package crackinginterview.datastructures.x2.linkedLists;

import org.junit.Test;

import java.util.Stack;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by pavlop on 3/12/14.
 */
public class IsListPolyndrom {
      static class RecursiveResult<T> {
         SingleNode<T> processedNode;
         boolean comparisonResult;
         public RecursiveResult(SingleNode<T> processedNode, boolean comparisonResult) {
             this.processedNode = processedNode;
             this.comparisonResult = comparisonResult;
         }
     }
;

    public static <T>  boolean isPolyndrom (SingleNode<T> head) {
        System.out.println("START isPolyndrom");

        //one element check
        if (head.next==null) return true;

        Stack<T> firstFalf =  new Stack<T>();
        SingleNode<T> slow = head;
        SingleNode<T> fast = head;
        while(fast!=null&&fast.next!=null) {
            firstFalf.push(slow.value);
            slow = slow.next;
            fast = fast.next.next;
        }

        System.out.println("middle reached: firstFalf="+firstFalf.size());
        //is even or odd?
        //if fatt != null - odd
        if (fast!=null) {
            slow = slow.next;
        }

        // continue loop for slow
        // slow is pointing to the middle now
        while(slow != null) {
            System.out.println("comparing list:"+slow.value+" stack:"+ firstFalf.peek());
            if(!slow.value.equals(firstFalf.pop())) return false;
            slow = slow.next;

        }
        return true;
    }


    public static <T>  boolean isPolyndromRecursive (SingleNode<T> head) {
        System.out.println("START isPolyndromRecursive");
        calculatePoyndromRecursive(head, MySinglyLinkedList.getLength(head));
        return true;
    }

    public static <T> RecursiveResult<T> calculatePoyndromRecursive(SingleNode<T> curNode, int remainLen) {
        SingleNode<T> leftNode = curNode;
        RecursiveResult<T> result = new RecursiveResult<T>(null, false);
        System.out.println("Recursive: remain len="+ remainLen);
        if (remainLen > 1) {
            result = calculatePoyndromRecursive(curNode.next, remainLen -2);
        } else if (remainLen == 1) {
            return new RecursiveResult<T>(curNode, true);
        } else if (remainLen < 1 ) {
            return new RecursiveResult<T>(curNode, false);
        }

        if (result.comparisonResult == false) return result;
        SingleNode<T> rightNode = curNode.next;
        if(leftNode.value != rightNode.value) {
            return new RecursiveResult<T>(null, false);
        } else {
            return new RecursiveResult<T>(curNode.next, true);
        }

    }

    @Test
    public void test() {
        MySinglyLinkedList<Integer> input = new MySinglyLinkedList<Integer>();
        input.add(1);
        assertTrue(isPolyndrom(input.head));
        assertTrue(isPolyndromRecursive(input.head));

        input.add(2);
        assertFalse(isPolyndrom(input.head));
        assertTrue(isPolyndromRecursive(input.head));

        input.add(1);
        assertTrue(isPolyndrom(input.head));
        assertTrue(isPolyndromRecursive(input.head));

        input.add(1);
        input.add(2);
        input.add(1);
        assertTrue(isPolyndrom(input.head));
        assertTrue(isPolyndromRecursive(input.head));

    }
}
