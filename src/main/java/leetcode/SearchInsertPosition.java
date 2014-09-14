package leetcode;

/**
 Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

 You may assume no duplicates in the array.

 Here are few examples.
 [1,3,5,6], 5 → 2
 [1,3,5,6], 2 → 1
 [1,3,5,6], 7 → 4
 [1,3,5,6], 0 → 0
 */
public class SearchInsertPosition {
    public int searchInsert(int[] A, int target) {
        return searchInsert(A, 0, A.length - 1, target);
    }

    public int searchInsert(int[] A, int start, int end, int target) {
        if(end - start <= 1) {
            if(target <= A[start]) return start;
            if(target > A[end]) return end+1;
            //either equals to end or benween first and second element
            return end;
        }

        int mid =start + (end - start)/2;
        if(target <= A[mid]) return searchInsert(A, start, mid, target);
        else return searchInsert(A, mid+1, end, target);
    }
}
