package crackinginterview.datastructures.heap;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by pavlop on 3/6/14.
 */
public class BinaryMinHeap {

    public int[] heap;

    public void heapify (int i) {
        heapify(i, heap.length);

    }

    public void heapify(int i, int length) {
        System.out.println("heapify: i= "+i + " len="+length+" heap="+Arrays.toString(heap));

        // if leaf - no headify is needed
        if (i+1 >= length/2) return;

        int leftChildID = 2*(i+1)-1;
        int rightChildID =2*(i+1);
        int parentID = i;


        int smallerChildID = (heap[leftChildID]<heap[rightChildID]) ? leftChildID:rightChildID;

        if(heap[parentID] > heap[smallerChildID]) {
            //swap parent and larger child
            swap(heap, parentID, smallerChildID);
            heapify(smallerChildID, length);
        }

    }

    public void arrayToHeap() {
        for (int i = 0; i<heap.length/2; i++) {
            heapify(i);
        }
    }

    public static void swap (int[] array, int id1, int id2) {
        int tmp = array[id1];
        array[id1] = array[id2];
        array[id2] = tmp;
    }

    public  void shortDesc(){
        for (int i = 0; i < heap.length ; i++) {
            System.out.println("[straigh]doSort: i="+i + " heap="+Arrays.toString(heap));
            swap(heap, 0, heap.length - i -1 );
            System.out.println("[swapped]doSort: i="+i + " heap="+Arrays.toString(heap));

            heapify(0, heap.length - i);
        }

    }

    @Test
    public void testHeapify() {
        int[] input = {1,2,3,4,5,6,7};
        this.heap = input;
        shortDesc();
        assertEquals(Arrays.toString(new int[]{1,2,3,4,5,6,7}), Arrays.toString(this.heap));
    }
}
