package leetcode;

/**
 Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

 For example,
 Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.


 A method that can solve this problem with one pass and constant space:
 - one point starts from left, another starts from right,
 - store the level at present
 - if new level is bigger then old one:
    - calculate the area of rectangle "all" (i.e. water which we would have if the walls were thin)
    - remember new level
 - move smaller wall and remember the blocks occupied by walls

 Answer = allWater - allBlocks

 */
public class TrappingRainWater {
    public int trap(int[] A) {
        int left = 0;
        int right = A.length - 1;
        int curLevel = 0;
        int allWater = 0;
        int allBlocks = 0;
        while (left <= right) {
            int newLevel = Math.min(A[left], A[right]);
            if (newLevel > curLevel) {
                allWater += (right - left + 1) * (newLevel - curLevel);
                curLevel = newLevel;
            }
            if (A[left] <= A[right]) {
                allBlocks += A[left];
                left++;
            } else {
                allBlocks += A[right];
                right--;
            }
        }
        return allWater -allBlocks;
    }
}