package leetcode;

/**
 *
 Given an array and a value, remove all instances of that value in place and return the new length.

 The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 *
 */
public class RemoveElement {
    public int removeElement(int[] A, int elemToBeRemoved) {
        if (A == null || A.length == 0) return 0;
        int i = 0;
        int j = A.length - 1;
        int removedCount = 0;

        while (i <= j) {
            if(A[i] != elemToBeRemoved) {
                i++;
                continue;
            }
            if(A[j] == elemToBeRemoved) {
                removedCount++;
                j--;
                continue;
            }
            A[i] = A[j];
            i++;
            j--;
            removedCount++;
        }
        return A.length - removedCount;
    }
}
