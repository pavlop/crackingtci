package crackinginterview.approaches;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by pavlop on 2/16/14.
 */
public class ShifterArrayMinimum {
    public static int perform(int array[]) throws Exception {
        return identifyRecursively(array, 0, array.length - 1);
    }

    private static int identifyRecursively(int array[], int start, int end) {
        if (start == end) {
            return array[start];
        } else {
            int mid = start + (end - start) / 2;
            if (array[end] < array[mid]) {
                return identifyRecursively(array, mid + 1, end);
            } else {
                return identifyRecursively(array, start, mid);
            }
        }
    }

    @Test
    public void positive1() throws Exception {
        assertEquals(1, perform(new int[]{7, 8, 9, 1, 2, 3}));
        assertEquals(1, perform(new int[]{1}));
        assertEquals(1, perform(new int[]{7, 8, 1, 2, 3}));
        assertEquals(1, perform(new int[]{1, 2, 3, 4}));
        assertEquals(0, perform(new int[]{4, 3, 2, 1, 0}));
        assertEquals(1, perform(new int[]{4, 3, 2, 1}));
    }
}
