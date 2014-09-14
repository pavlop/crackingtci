package corman.DynProg;

import org.junit.Assert;
import org.junit.Test;

/**

 Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

 For example,
 Given n = 3, there are a total of 5 unique BST's.

1         3     3      2      1
 \       /     /      / \      \
  3     2     1      1   3      2
 /     /       \                 \
2     1         2                 3


 SOLUTION

 1 2 3

 root 1:
    - apply for [] and [2,3]
    - 1 option for []
    - 2 options for [2,3]

 toatal 1*2 = 2

 root 2:
 - apply for [1] and [3]
 - 1 option for [1]
 - 1 option for [3]

 toatal 1*1 = 1

 root 3:
 - apply for [1, 2] and []
 - 2 options for [1, 2]
 - 1 option for []

 total 1*1 = 1

 */


public class UniqueBinarySearchTrees {

    // TODO memorization of    numOfTreesRec(int sortedNumsSize)

    public int numTrees(int n) {
        return  numOfTreesRec(n);
    }

    public int numOfTreesRec(int sortedNumsSize) {
        if (sortedNumsSize == 0) return 1;
        if (sortedNumsSize == 1) return 1;
        int teesCounter = 0;
        for (int i = 0; i < sortedNumsSize; i++) {
            int leftSubtreeOptions = numOfTreesRec(i);
            int rightSubtreeOptions = numOfTreesRec(sortedNumsSize - i - 1);
            teesCounter += leftSubtreeOptions*rightSubtreeOptions;
        }
        return teesCounter;
    }

    @Test
    public void test() {
        Assert.assertEquals(5, numTrees(3));
        Assert.assertEquals(2, numTrees(2));
        Assert.assertEquals(1, numTrees(1));
        System.out.println(numTrees(5));
    }
}
