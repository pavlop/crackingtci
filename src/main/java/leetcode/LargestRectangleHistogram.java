package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 *
 *Solution:
 Use Stack to keep track of height and start indexes
 Compare current height with previous one

 #1: current Larger than previous (top of height stack)
 Push current height & index as candidate rectangle start position

 #2: current EQUALS previous
 Ignore, as largest rectangle will start from previous with same height

 #3: current is less than previous
 Need keep popping out previous heights, and compute the candidate rectangle with height and width (current index MINUS previous index)
 Push the height and index (appropriate position) to stacks

 DEMO: https://www.youtube.com/watch?v=E5C5W6waHlo
 *
 */
public class LargestRectangleHistogram {
    public int largestRectangleArea(int[] height) {

        Stack<Integer> heightsStack = new Stack<Integer>();
        Stack<Integer> indexesStack = new Stack<Integer>();
        int bestArea = 0;

        for (int i = 0; i < height.length; i++) {

            // case 1
            // cur height is larger so can be candidate
            // for rectanle start
            if (heightsStack.isEmpty() || heightsStack.peek() < height[i]) {
                heightsStack.push(height[i]);
                indexesStack.push(i);


            // need to pop all the previous heights
            // which are larger then current one
            // and calculate rectanles
            } else if (heightsStack.peek() > height[i]) {

                //remember the last popped index, as the next
                // rectangle will start from it
                int lastIndex = 0;
                while (!heightsStack.isEmpty() && heightsStack.peek() > height[i]) {
                    lastIndex = indexesStack.pop();
                    bestArea = Math.max(heightsStack.pop() * (i - lastIndex), bestArea);
                }
                indexesStack.push(lastIndex);
                heightsStack.push(height[i]); //not I but last popped index
            }
        }

        // if there are still items left
        // in case we had increading order at the end
        // need to tels all of them assuming the the rectangle with them will be at the end of array
        while (!heightsStack.isEmpty()) {
            int curHeight = heightsStack.pop();
            int curWidth = (height.length - indexesStack.pop()); // end of array - last big index
            bestArea = Math.max(curHeight * curWidth, bestArea);
        }
        return bestArea;
    }


    @Test
    public void test() {
        int[] input = new int[]{0, 0};
        Assert.assertEquals(0, largestRectangleArea(input));

        input = new int[]{0, 0, 0};
        Assert.assertEquals(0, largestRectangleArea(input));

        input = new int[]{1};
        Assert.assertEquals(1, largestRectangleArea(input));

        input = new int[]{1, 1};
        Assert.assertEquals(2, largestRectangleArea(input));

        input = new int[]{1, 2};
        Assert.assertEquals(2, largestRectangleArea(input));

        input = new int[]{2, 1};
        Assert.assertEquals(2, largestRectangleArea(input));


        input = new int[]{2, 4, 2, 1};
        Assert.assertEquals(6, largestRectangleArea(input));

        input = new int[]{2, 3, 5, 2, 9, 1};
        Assert.assertEquals(10, largestRectangleArea(input));
    }

}
