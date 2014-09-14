package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

/**
 *
 *
 *
 */
public class MedianTwoSortedArrays2 {

    public double findMedianSortedArrays(int A[], int B[]) {
        if ((A.length + B.length)%2 == 1)  {
            int k = (A.length + B.length)/2;
            return findKthElement(k, A, 0, B, 0);
        } else {
            int k1 = (A.length + B.length)/2;
            int num1 = findKthElement(k1, A, 0, B, 0);

            int k2 = (A.length + B.length)/2 - 1;
            int num2 = findKthElement(k2, A, 0, B, 0);

            return (num1+num2)*0.5;
        }
    }

    int calculateMid(int k) {
        if (k/2 > 0) return k/2;
        return 1;
    }

    int findKthElement(int k, int[] A, int start1, int[] B, int start2) {
        if(A.length == 0) return B[k];
        if(B.length == 0) return A[k];

        if (k == 0) {
            return Math.min(A[start1], B[start2]);
        }
        if (start1 == A.length -1) {
            if(k < B.length && A[start1] >= B[start2+k]) return B[start2+k];
            else return Math.max(A[start1], B[start2+k-1]);
        }
        if (start2 == B.length- 1) {
            if(k < A.length &&  B[start2] >= A[start1+k]) return A[start1+k];
            else return Math.max(B[start2], A[start1+k-1]);
        }

        if(k == 1) {
            if(A[start1] < B[start2]) return Math.min(A[start1+1],B[start2]);
            else return Math.min(A[start1],B[start2+1]);
        }

//        int mid = Math.round(k / 2.0f);
        int mid = calculateMid(k);
        int sub1 = Math.min(mid, A.length -1 - start1);
        int sub2 = Math.min(mid, B.length -1 - start2);
        if (A[start1+sub1] < B[start2+sub2]) {
            return findKthElement(k-sub1 ,A, start1+sub1, B, start2);
        } else {
            return findKthElement(k-sub2 ,A, start1, B, start2+sub2);
        }
    }

    @Test
    public void doTest() {
        int[] numbers1 = {1};
        int[] numbers2 = {};
        assertEquals(1.0, findMedianSortedArrays(numbers1, numbers2));

        numbers1 = new int[]{};
        numbers2 = new int[]{1};
        assertEquals(1.0, findMedianSortedArrays(numbers1, numbers2));


        numbers1 = new int[]{1,3};
        numbers2 = new int[]{3,5};
        assertEquals(3.0, findMedianSortedArrays(numbers1, numbers2));

        numbers1 = new int[]{1,2,3};
        numbers2 = new int[]{2,3,5};
        assertEquals(2.5, findMedianSortedArrays(numbers1, numbers2));

        numbers1 = new int[]{1,2};
        numbers2 = new int[]{1,2,3};
        assertEquals(2.0, findMedianSortedArrays(numbers1, numbers2));

        // all combinations 1-4
        numbers1 = new int[]{1};
        numbers2 = new int[]{2,3,4};
        assertEquals(2.5, findMedianSortedArrays(numbers1, numbers2));

        numbers1 = new int[]{2};
        numbers2 = new int[]{1,3,4};
        assertEquals(2.5, findMedianSortedArrays(numbers1, numbers2));

        numbers1 = new int[]{1,2,6};
        numbers2 = new int[]{3,4,5};
        assertEquals(3.5, findMedianSortedArrays(numbers1, numbers2));

        numbers1 = new int[]{1,2,3,8};
        numbers2 = new int[]{4,5,6,7};
        assertEquals(4.5, findMedianSortedArrays(numbers1, numbers2));

        numbers1 = new int[]{1,2,5,7,8};
        numbers2 = new int[]{1,4,4,5,9};
        assertEquals(4.5, findMedianSortedArrays(numbers1, numbers2));

        System.out.println("HERE");
        numbers1 = new int[]{1,4};
        numbers2 = new int[]{2,3,5,6};
        assertEquals(3.5, findMedianSortedArrays(numbers1, numbers2));

    }

}
