package leetcode;

import org.junit.Test;


import static junit.framework.TestCase.assertEquals;
import static leetcode.FindKMinTwoSortedArrays.findKMinElement;

/**
 * There are two sorted arrays A and B of size m and n respectively.
 * Find the median of the two sorted arrays.
 * The overall run time complexity should be O(log (m+n)).
 *
 * SOLUTION: http://leetcode.com/2011/03/median-of-two-sorted-arrays.html
 *
 * My solution
 * find K-1 element

 */
public class MedianTwoSortedArraysUnresolved {

    public double findMedianSortedArrays(int A[], int B[]) {
        if (A.length == 0) return getMedian(B);
        if (B.length == 0) return getMedian(A);
         if ((A.length+B.length)%2 != 0) {
             return findKMinElement(A, B, (A.length+B.length)/2);
        } else {
             int m1 = findKMinElement(A, B, (A.length+B.length)/2);
             int m2 =  findKMinElement(A, B, 1+ (A.length+B.length)/2);
             return (m1+m2)/2.0;
         }
    }

    public static double getMedian(int A[]) {
        if(A.length == 0) throw new RuntimeException("Array size is 0");
        if (A.length == 1) return A[0];
        int midElemen = A.length/2;
        if(A.length%2!=0)
            return A[midElemen];
        else
            return (A[midElemen-1]+A[midElemen])/2.0;
    }

//    public static int getMedianID(int A[], boolean roundBigger) {
//        if(A.length == 0) throw new RuntimeException("Array size is 0");
//        if (A.length == 1) return 1;
//
//        if(A.length%2 != 0)
//            return A.length/2;
//        else
//            if(roundBigger) return A.length/2 + 1;
//            else return A.length/2;
//    }

    @Test
    public void doTest() {
        MedianTwoSortedArraysUnresolved clz = new MedianTwoSortedArraysUnresolved();
        int[] numbers1 = {1,2,3,3,4,5,6};
        int[] numbers2 = {4,4,4,4,5,6,7};
        double res = 4.0;
        assertEquals(res, clz.findMedianSortedArrays(numbers1, numbers2));

        numbers1 = new int[]{1,2,5,7,8};
        numbers2 = new int[]{1,4,4,5,9};
        //112 44 55 789
        res = 4.5;
        assertEquals(res, clz.findMedianSortedArrays(numbers1, numbers2));

    }
}
