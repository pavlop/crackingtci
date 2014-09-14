package leetcode;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 *
 *
 *
 */
public class FindKElementTwoSortedArrays {
    int findKthElement(int k, int[] A, int start1, int[] B, int start2) {

        if (k ==0) {
            return Math.min(A[start1], B[start2]);
        }
        if (start1 == A.length -1) {
            if(k < B.length && A[start1] >= B[k]) return B[k];
            else return Math.max(A[start1], B[k-1]);
        }
        if (start2 == B.length- 1) {
            if(k < A.length &&  B[start2] >= A[k]) return A[k];
            else return Math.max(B[start2], A[k-1]);
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
    public void test() {
        int[] numbers1 = {1, 2};
        int[] numbers2 = {3, 4};
        assertEquals(2, findKthElement(1, numbers1, 0, numbers2, 0));
        assertEquals(3, findKthElement(2, numbers1, 0, numbers2, 0));

        numbers1 = new int[]{1, 2};
        numbers2 = new int[]{3};
        assertEquals(2, findKthElement(1, numbers1, 0, numbers2, 0));

        numbers1 = new int[]{1, 3, 5};
        numbers2 = new int[]{2, 4, 6};
        assertEquals(6, findKthElement(5, numbers1, 0, numbers2, 0));
        assertEquals(1, findKthElement(0, numbers1, 0, numbers2, 0));

        numbers1 = new int[]{1, 2};
        numbers2 = new int[]{1, 2, 3};
        assertEquals(2, findKthElement(2, numbers1, 0, numbers2, 0));

        numbers1 = new int[]{1,2,6};
        numbers2 = new int[]{3,4,5};
        assertEquals(4, findKthElement(3, numbers1, 0, numbers2, 0));

    }

    int calculateMid(int k) {
        if (k/2 > 0) return k/2;
        return 1;
    }
}
