package crackinginterview;


import junit.framework.Assert;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 *
 *
 */
public class FindBiggestRectangleInHistogram {

    public int getRec(int[] histogram) {
        int maxRectangle = 0;

        Deque<Integer> ascendingBars = new LinkedList<Integer>();
        Deque<Integer> startingIndex = new LinkedList<Integer>();

        ascendingBars.add(histogram[0]);
        startingIndex.add(0);

        for (int i = 1; i < histogram.length; i++) {

            // add bars if they are in ascending order
            if (histogram[i] >= histogram[i-1]) {
                ascendingBars.push(histogram[i]);
                startingIndex.push(i);
            }

            // if cur bar is less then previous
            // keep removing from 'ascendingBars' until there are left
            // only bars less then 'cur'
            else {
                int lastPoppedIdx = 0;
                while (ascendingBars.size() > 0 && ascendingBars.peek() > histogram[i]) {
                    int popedHigherThenCur = ascendingBars.pop();
                    lastPoppedIdx = startingIndex.pop();
                    maxRectangle = Math.max(maxRectangle, popedHigherThenCur * (i - lastPoppedIdx));
                }
                ascendingBars.push(histogram[i]);
                startingIndex.push(lastPoppedIdx);
            }
        }

        // if there are still some element, they can create rec with the last bar 'histogram.length'
        while (ascendingBars.size() > 0) {
            int popedHigherThenCur = ascendingBars.pop();
            int lastPoppedIdx = startingIndex.pop();
            maxRectangle = Math.max(maxRectangle, popedHigherThenCur * (histogram.length - lastPoppedIdx));
        }
        return maxRectangle;
    }


    @Test
    public void test() {
        Assert.assertEquals(1, getRec(new int[] {1}));
        Assert.assertEquals(18, getRec(new int[] {3, 4, 6, 9, 10, 3, 2}));
        Assert.assertEquals(8, getRec(new int[] {1, 2, 2, 3, 4}));
    }
}


