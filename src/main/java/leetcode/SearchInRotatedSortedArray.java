package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 Suppose a sorted array is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 You are given a target value to search. If found in the array return its index, otherwise return -1.

 You may assume no duplicate exists in the array.
 */
public class SearchInRotatedSortedArray {

    public int search(int[] A, int target) {
        return search(A, target, 0, A.length -1);
    }

    public int search(int[] A, int target, int startAt, int endAt) {
       if (endAt - startAt <= 1) {
            if (A[startAt] == target) return startAt;
            if (A[endAt] == target) return endAt;
            else return -1;
        }
        int mid = (endAt+startAt) / 2;


        if (A[startAt] < A[mid]) {                              // if left part is normal
            if (target >= A[startAt] && target <= A[mid] )
            return search(A, target, startAt, mid);
        } else if  (target <= A[startAt] && target <= A[mid]) { // if left part is rotaed somewehre in the middle, the target is less then both
            return search(A, target, startAt, mid);
        } else if  (target >= A[startAt] && target >= A[mid]) { // if left part is rotaed somewehre in the middle, the target is bigger then both
            return search(A, target, startAt, mid);
        }

        // if not in the left half - then look in the right
            return search(A, target, mid + 1, endAt);
    }

    @Test
    public void test() {
        Assert.assertEquals(0, search(new int[]{1,3,5}, 1));
        Assert.assertEquals(2, search(new int[]{5,1,3}, 3));
        Assert.assertEquals(0, search(new int[]{5,1,3}, 5));

    }
    //

}
