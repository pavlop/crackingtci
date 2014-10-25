package dynamicProgramming;

import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertSame;
import static junit.framework.TestCase.assertTrue;

/**
 * 4.5
 Задан массив натуральных чисел А[1..N]. Требуется разбить эти числа на два подмножества таким обра-
 зом, чтобы сумма элементов в подмножествах отличалась на минимальную величину.
 */
public class SplitSetInTwoWithClosestSumms {
    MyHashSet<Integer> leftIDs = new MyHashSet<Integer>();

    int totalSum = 0;

    public int splitSetInTwoWithMinDeltaSum(int curID, int[] array) {
        //inicialization
        if (curID == 0) {
            leftIDs = new MyHashSet<Integer>();
            System.out.println("IDs in left: after clearence:"+ leftIDs);
            totalSum=MyArrayUtils.getSum(array);
            System.out.println("totalSum="+totalSum);
        }

        if (curID == array.length) return Math.abs(totalSum - 2*leftIDs.getSum());

        //skip current
        int deltaIfSkip = splitSetInTwoWithMinDeltaSum(curID+1, array);

        //include current
        leftIDs.add(array[curID]);
        System.out.println(" add element id:" + curID);
        int deltaIfinclude = splitSetInTwoWithMinDeltaSum(curID+1, array);
        leftIDs.remove(array[curID]);

        System.out.println("returning min of:"+deltaIfinclude +" and "+ deltaIfSkip);

        int minDelta = MyArrayUtils.getMinElement(deltaIfinclude, deltaIfSkip);
        return minDelta;
    }

    @Test
    public void test() {
        assertSame(0, splitSetInTwoWithMinDeltaSum(0, new int[]{3, 1, 2}));
        System.out.println(" *** left="+leftIDs);

        assertSame(1, splitSetInTwoWithMinDeltaSum(0, new int[]{2, 3, 4}));
        System.out.println(" *** left="+leftIDs);

        assertSame(2, splitSetInTwoWithMinDeltaSum(0, new int[]{3, 7, 11, 13}));
        System.out.println(" *** left="+leftIDs);
    }

}

class MyHashSet<I> extends HashSet<Integer> {
    public int cutSumm = 0;

    public MyHashSet() {
        super();
    }

    public MyHashSet(Collection c) {
        super(c);
    }


    @Override
    public boolean add (Integer o) {
        cutSumm += o;
        return super.add(o);
    }

    @Override
    public boolean remove (Object o) {
        cutSumm -=(Integer) o;
        return super.remove(o);
    }

    @Override
    public void clear () {
        cutSumm =0;
        super.clear();
    }

    public int getSum () {
        return cutSumm;
    }
}
