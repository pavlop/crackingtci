package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

/**
 * C
 * Assumption: The inputs are right. i.e. k is in the range [0, len(a)+len(b)]

 Base cases:

 If length of one of the arrays is 0, the answer is kth element of the second array.
 Reduction steps:

 If mid index of a + mid index of b is less than k
    If mid element of a is greater than mid element of b, we can ignore the first half of b, adjust k.
    else ignore the first half of a, adjust k.
 Else if k is less than sum of mid indices of a and b:
    If mid element of a is greater than mid element of b, we can safely ignore second half of a
    else we can ignore second half of b
 */
public class FindKMinTwoSortedArrays {
    public static int findKMinElement(int[]a, int[]b, int k) {
        System.out.println("findKMinElement: A:"+Arrays.toString(a) +" B:"+Arrays.toString(b)+" k="+k);
        int i = a.length;
        int j = b.length;

       // if (i==1 && j ==1)

        if (i == 0) return b[k-1];
        if (j == 0) return a[k-1];
        if(i+j == k) return max(a[i - 1], b[j - 1]);

        if (k == 1) return min(a[0], b[0]);
        if (i == 1) return a[0]<b[k-1]?max(a[0],b[k-2]):b[k-1];
        if (j == 1) return b[0]<a[k-1]?max(b[0],a[k-2]):a[k-1];

        j = j/2 ;
        i = i/2 ;

        System.out.println("i="+i+" j="+j+" .. comparing a[i] and b[j]");
        if (i + j  < k-1) {
            if (a[i]>b[j]) {
                return findKMinElement(Arrays.copyOfRange(a, i, a.length ), b, k-i);
            } else {
                return findKMinElement(a, Arrays.copyOfRange(b, j, b.length ), k-j);
            }
        } else {
            if (a[i] > b[j]) {
                return findKMinElement(Arrays.copyOfRange(a, 0, i), b, k);
            } else {
                return findKMinElement(a, Arrays.copyOfRange(b, 0, j), k);
            }
         }

    }

    private static int max(int x, int y) {
        return x < y? y : x;
    }


    static int min(int x, int y)
    {
        return x > y? y : x;
    }

    @Test
    public void doTest() {

        int[] numbers1 = {1,4,6,7};
        int[] numbers2 = {2,3,4,9};
        assertEquals(1, findKMinElement(numbers1, numbers2, 1));
        assertEquals(2, findKMinElement(numbers1, numbers2, 2));
        assertEquals(3, findKMinElement(numbers1, numbers2, 3));
        assertEquals(4, findKMinElement(numbers1, numbers2, 4));


        numbers1 = new int[] {1,4,6,7,10};
        numbers2 = new int[] {2,3,4,9};
        assertEquals(4, findKMinElement(numbers1, numbers2, 4));

        numbers1 = new int[] {1,4,6};
        numbers2 = new int[] {2,3,4,9};
        assertEquals(1, findKMinElement(numbers1, numbers2, 1));
        assertEquals(2, findKMinElement(numbers1, numbers2, 2));
        assertEquals(3, findKMinElement(numbers1, numbers2, 3));
        assertEquals(4, findKMinElement(numbers1, numbers2, 4));

        numbers1 = new int[] {1};
        numbers2 = new int[] {2,3,4,9};
        assertEquals(1, findKMinElement(numbers1, numbers2, 1));
        assertEquals(2, findKMinElement(numbers1, numbers2, 2));
        assertEquals(3, findKMinElement(numbers1, numbers2, 3));
        assertEquals(4, findKMinElement(numbers1, numbers2, 4));
        assertEquals(9, findKMinElement(numbers1, numbers2, 5));

        numbers1 = new int[] {1,2,3,3};
        numbers2 = new int[] {5,5,5,5,6,6,6,6};
        assertEquals(1, findKMinElement(numbers1, numbers2, 1));
        assertEquals(2, findKMinElement(numbers1, numbers2, 2));
        assertEquals(3, findKMinElement(numbers1, numbers2, 3));


    }
}
