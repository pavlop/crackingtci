package interview.datastructures.linkedLists;

import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Input:(7-> 1 -> 6) + (5 -> 9 -> 2).Thatis,617 + 295.
 * Output: 2 -> 1 -> 9.That is, 912.
 */
public class DigitsAsListsStraight {

    private static class NodeAndSumFromPreviosu {
        public Node node;
        public int sumFromPreviousStep;

        private NodeAndSumFromPreviosu(Node node, int sumFromPreviousStep) {
            this.node = node;
            this.sumFromPreviousStep = sumFromPreviousStep;
        }
    }



    public static Node<Integer> sumStraightDigits (MySinglyLinkedList<Integer> lisst1, MySinglyLinkedList<Integer> lisst2) {
        if(lisst1.length > lisst2.length) {
            zeroPadding(lisst2, lisst1.length - lisst2.length);
        } else {
            zeroPadding(lisst1, lisst2.length - lisst1.length);
        }
        NodeAndSumFromPreviosu result = findSumRecursive(0, lisst1.head, lisst2.head);

        if (result.sumFromPreviousStep != 0) {
            Node<Integer> leadingNode = new Node<Integer>(result.sumFromPreviousStep,result.node);
            return leadingNode;
        } else {
            return result.node;
        }

    }

    private static NodeAndSumFromPreviosu  findSumRecursive(int fromPreviousSumm, Node<Integer> node1, Node<Integer> node2) {

        NodeAndSumFromPreviosu previousResult = new NodeAndSumFromPreviosu(null, 0);

        //continue till reached the end of both lists
        if(node1.next != null || node2.next != null) {
            previousResult = findSumRecursive(0, node1.next, node2.next);
        }
        System.out.println("findSumRecursive: node1="+node1+" node2="+node2 + " previousResult="+previousResult);
        int sum = getSum(fromPreviousSumm, node1, node2);
        int newValue = sum % 10;
        fromPreviousSumm = sum / 10;

        Node<Integer> newNode = new Node<Integer>(newValue);
        newNode.next = previousResult.node;

        NodeAndSumFromPreviosu result = new NodeAndSumFromPreviosu(newNode, fromPreviousSumm);
        return result;
    }

    //insert zreos before the number starts
    public static  void zeroPadding(MySinglyLinkedList<Integer> lisst, int numberOfZeros) {
        for(int i=0; i<numberOfZeros; i++) {
            lisst.addToHead(0);
        }

    }


    private static int getSum(int baseNumber, Node<Integer> ... nodes) {
        int result = baseNumber;
        for (Node<Integer>  node: nodes) {
            if (node != null) {
                result += node.value;
            }
        }
        return result;
    } 

    @Test
    public void testWhenNumbersRewersed() {
        MySinglyLinkedList<Integer> input = new MySinglyLinkedList<Integer>();
        input.add(5);input.add(2);input.add(3);

        List result = MySinglyLinkedList.subList(sumStraightDigits(input, input),99);
        assertEquals("[1, 0, 4, 6]", result+"");

        //input.add(9);
        //List result2 = MySinglyLinkedList.subList(sumReverseDigits(input.head, input.head),99);
        //assertEquals("[1, 8, 6, 4, 2]", result2+"");
    }


}
