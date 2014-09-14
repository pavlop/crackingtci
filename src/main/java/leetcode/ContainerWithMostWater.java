package leetcode;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import static junit.framework.TestCase.assertSame;

/**
 *Given n non-negative integers a1, a2, ..., an, where each represents
 * a point at coordinate (i, ai). n vertical lines are drawn
 * such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container,
 * such that the container contains the most water.
 *
 * Note: You may not slant the container.
 *
 * For Input: [1,1] answer is 1
 * Width = P2.x - P1.x = 2 - 1 = 1
 * Height = min(P1.y, P2.y) = min(1,1) = 1
 * A = W*H = 1*1 = 1
 *
 *
 *
 * Here is the proof. Proved by contradiction:

 Suppose the returned result is not the optimal solution.
 Then there must exist an optimal solution, say a container with aol and aor (left and right respectively),
 such that it has a greater volume than the one we got.
 Since our algorithm stops only if the two pointers meet.
 So, we must have visited one of them but not the other.
 WLOG, let's say we visited aol but not aor.
 When a pointer stops at a_ol, it won't move until

 The other pointer also points to aol. In this case, iteration ends.
 But the other pointer must have visited aor on its way from right end to aol.
 Contradiction to our assumption that we didn't visit aor.

 The other pointer arrives at a value, say arr, that is greater than aol before it reaches aor.
 In this case, we does move aol. But notice that the volume of aol and arr is already
 greater than aol and aor (as it is wider and heigher),
 which means that aol and aor is not the optimal solution -- Contradiction!

 Both cases arrive at a contradiction.
 */
public class ContainerWithMostWater{

    public int maxArea(int[] height) {
        int maxSquare = 0;
        int l = 0;
        int r = height.length-1;

        while (l<r) {
            int leftEl = height[l];
            int rightEl = height[r];
            maxSquare = Math.max(maxSquare, (r-l)*Math.min(leftEl, rightEl));

            if(leftEl < rightEl) l++;
            else r--;
        }
        return maxSquare;
    }

    @Test
    public void doTest() {
        assertSame(1, maxArea(new int[]{1, 1}));
        assertSame(2, maxArea(new int[]{1, 1, 1}));
        assertSame(6, maxArea(new int[]{3, 1, 3}));
    }


}
