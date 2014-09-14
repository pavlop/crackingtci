package leetcode;

/**
 * Created by pavlop on 5/25/14.
 */
public class MergeSortedArray {

    /*
    Given two sorted integer arrays A and B, merge B into A as one sorted array.
    Note:
    You may assume that A has enough space
    (size that is greater or equal to m + n) to hold additional elements from B.
    The number of elements initialized in A and B are m and n respectively.
     */
    public void merge(int A[], int m, int B[], int n) {
       for(int i = m-1, j = n-1, global = A.length-1; global >= 0;  global--) {
           if (i >= 0 && j >= 0) {
               if (A[i] > B[j] ) {
                   A[global] = A[i];
                   i--;
               } else {
                   A[global] = B[j];
                   j--;
               }
           } else if (i >= 0 ) {
               A[global] = A[i];
               i--;
           } else {
               A[global] = B[j];
               j--;
           }
       }
    }

    //NOT leetcode isse
    // result is in new array
    public static int[] merge(int A[], int B[]) {
        int[] result = new int[A.length + B.length];
         for (int i=0,j =0; (i+j) < result.length;) {
             if (i < A.length && j < B.length) {
                if (A[i]<B[j]) {
                    result[i+j] = A[i];
                    i++;
                } else {
                    result[i+j] = B[j];
                    j++;
                }
             } else if (i < A.length ) {
                 result[i+j] = A[i];
                 i++;
             } else {
                 result[i+j] = B[j];
                 j++;
             }

        }
        return result;
    }
}
