package leetcode;

import org.junit.Assert;
import org.junit.Test;

/*
    Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
    find the area of largest rectangle in the histogram.

    For example,
    Given height = [2,1,5,6,2,3],
    return 10.
 */

/**

 O(Nlog(N) - average
 O(N^2) in case of 1,2,3.... sequence

a) Maximum area in left side of minimum value (Not including the min value)
b) Maximum area in right side of minimum value (Not including the min value)
c) Number of bars multiplied by minimum value.
The areas in left and right of minimum value bar can be calculated recursively.
 If we use linear search to find the minimum value, then the worst case time complexity of this algorithm becomes O(n^2).
 In worst case, we always have (n-1) elements in one side and 0 elements in other side and if the finding minimum takes O(n) time,
 we get the recurrence similar to worst case of Quick Sort.

 How to find the minimum efficiently? Range Minimum Query using Segment Tree can be used for this.
 We build segment tree of the given histogram heights.
 Once the segment tree is built, all range minimum queries take O(Logn) time. So over all complexity of the algorithm becomes.

Overall Time = Time to build Segment Tree + Time to recursively find maximum area

Time to build segment tree is O(n). Let the time to recursively find max area be T(n). It can be written as following.
T(n) = O(Logn) + T(n-1)
The solution of above recurrence is O(nLogn). So overall time is O(n) + O(nLogn) which is O(nLogn).


 SEGMENT TREE;
 http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
     */

public class LargestRectangleHistogramDividaAndConquer {
    public int largestRectangleArea(int[] height) {
        if( height.length == 0) return 0;
        return calculateBestSquare(height, 0, height.length-1);
    }

    int calculateBestSquare(int[] height, int start, int end) {
        if(start > end) return 0;
        if(start == end) return height[start];

        int minHeightId = getMinId(start, end, height);
        int squareThroughMin = height[minHeightId]*(end-start+1);
        int squareLeft = calculateBestSquare(height, start, minHeightId-1);
        int squareRight = calculateBestSquare(height, minHeightId+1, end);
        return getMaxValue(squareLeft, squareRight, squareThroughMin);
    }

    public static int getMinId(int start, int end, int ... x) {
        if (x.length == 0 || start > end ) return Integer.MIN_VALUE;
        int minId = start;
        for (int curId = start+1; curId < end; curId++)
            if (x[curId] < x[minId]) minId = curId;
        return minId;
    }

    public static int getMaxValue(int ... x) {
        if (x.length == 0) return Integer.MIN_VALUE;
        int max = x[0];
        for (int curId = 1; curId < x.length; curId++)
            if (x[curId] > max) max = x[curId];
        return max;
    }

    @Test
    public void test() {
        int[] input = new int[]{0, 0};
        Assert.assertEquals(0, largestRectangleArea(input));

        input = new int[]{0, 0, 0};
        Assert.assertEquals(0, largestRectangleArea(input));


    }
}
