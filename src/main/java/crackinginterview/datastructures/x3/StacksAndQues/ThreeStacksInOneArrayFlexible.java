package crackinginterview.datastructures.x3.StacksAndQues;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;

/**
 * Created by pavlop on 3/19/14.
 */
public class ThreeStacksInOneArrayFlexible<T> {
    private T[] array;
    private final int STACKS_COUNT = 3;
    private int[] sizes = new int[STACKS_COUNT];
    private int maxSize;

    public ThreeStacksInOneArrayFlexible() {
    }

    public ThreeStacksInOneArrayFlexible(int totalSize) {
        this.array = (T[]) new Object[totalSize];
        maxSize = array.length/STACKS_COUNT;
    }

    public void push(T element, int stackID) {
        if (stackID > STACKS_COUNT || stackID <1) throw new IndexOutOfBoundsException("incorrect stack id");
        if (sizes[stackID] >= maxSize) throw new ArrayIndexOutOfBoundsException();

        sizes[stackID]++;
        int position = sizes[stackID]*stackID;
        array[position] = element;
    }

    public T pop (int stackID) {
        int position = sizes[stackID]*stackID;
        T value =  array[position];
        sizes[stackID]--;
        array[position]=null;
        return value;
    }



}
