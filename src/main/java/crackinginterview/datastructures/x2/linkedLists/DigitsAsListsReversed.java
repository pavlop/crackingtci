package crackinginterview.datastructures.x2.linkedLists;

import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

/**
 * Input:(7-> 1 -> 6) + (5 -> 9 -> 2).Thatis,617 + 295.
 * Output: 2 -> 1 -> 9.That is, 912.
 */
public class DigitsAsListsReversed {

    public static SingleNode<Integer> sumReverseDigits (SingleNode<Integer> head1, SingleNode<Integer> head2) {
        // digits are stored in reverse:
        // 2 -> 1 -> 9.That is, 912.
        MySinglyLinkedList<Integer> sumResult = new MySinglyLinkedList<Integer>();
        SingleNode<Integer> digit1 = head1;
        SingleNode<Integer> digit2 = head2;

        int fromPreviousSumm = 0;
        while (digit1 != null || digit2 != null) {
            int sum = getSum(fromPreviousSumm, digit1, digit2);
            int newValue = sum % 10;
            fromPreviousSumm = sum / 10;
            sumResult.addToHead(newValue);

            digit1 = digit1==null?null:digit1.next;
            digit2 = digit2==null?null:digit2.next;

        }

        if (fromPreviousSumm > 0 ) sumResult.addToHead(fromPreviousSumm);

        return sumResult.head;

    }

    private static int getSum(int baseNumber, SingleNode<Integer>... nodes) {
        int result = baseNumber;
        for (SingleNode<Integer> node: nodes) {
            if (node != null) {
                result += node.value;
            }
        }
        return result;
    }

    @Test
    public void testWhenNumbersRewersed() {
        MySinglyLinkedList<Integer> input = new MySinglyLinkedList<Integer>();
        input.add(1);input.add(2);input.add(3);

        List result = MySinglyLinkedList.subList(sumReverseDigits(input.head, input.head),99);
        assertEquals("[6, 4, 2]", result+"");

        input.add(9);
        List result2 = MySinglyLinkedList.subList(sumReverseDigits(input.head, input.head),99);
        assertEquals("[1, 8, 6, 4, 2]", result2+"");
    }


}
