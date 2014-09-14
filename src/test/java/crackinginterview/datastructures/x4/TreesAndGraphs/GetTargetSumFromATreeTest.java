package crackinginterview.datastructures.x4.TreesAndGraphs;

import org.junit.Before;
import org.junit.Test;


import static junit.framework.TestCase.assertSame;

/**
 * Created by pavlop on 4/11/14.
 */
public class GetTargetSumFromATreeTest {
    TNode head;
    @Before
    public void steup() {
        //         1
        //    2         4
        //  3    2    3   1
        //-1  5
      TNode n5 = new TNode(null, null, 5);
      TNode min1 = new TNode(null, null, -1);
      TNode n3 = new TNode(min1, n5, 3);
      TNode n2 = new TNode(null, null, 2);
      TNode n2par = new TNode(n3, n2, 2);

      TNode n3right = new TNode(null, null, 3);
      TNode n1right = new TNode(null, null, 1);
      TNode n4 = new TNode(n3right, n1right, 4);

      head = new TNode(n2par, n4, 1);

    }

    @Test
    public void testNegative() {
        System.out.println("negative testing");
        GetTargetSumFromATree.printPathsToGetTargetSum(head, 5);
    }
}
