package crackinginterview.datastructures.x3.StacksAndQues;

import org.junit.Test;

import static junit.framework.TestCase.assertSame;

/**
 * Created by pavlop on 3/19/14.
 */
public class ThreeStacksInOneArrayFlexibleTest<T> {



    @Test
    public void positive() {
        ThreeStacksInOneArrayFlexible<Integer> stack = new ThreeStacksInOneArrayFlexible<Integer>(10);
        stack.push(11,1);stack.push(12,1);stack.push(13,1);
        assertSame(13, stack.pop(1));
        assertSame(12, stack.pop(1));
        assertSame(11, stack.pop(1));

        stack.push(11,1);stack.push(12,1);stack.push(13,1);
        stack.push(21,2);stack.push(22,2);stack.push(23,2);
        stack.push(31,3);stack.push(32,3);stack.push(33,3);

    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void negative() {
        ThreeStacksInOneArrayFlexible<Integer> stack = new ThreeStacksInOneArrayFlexible<Integer>(10);
        stack.push(11,1);stack.push(12,1);stack.push(13,1);stack.push(14,1);
    }



}
