package crackinginterview.datastructures.x3.StacksAndQues;

import org.junit.Test;

import static junit.framework.TestCase.assertSame;

/**
 * Created by pavlop on 3/19/14.
 */
public class StackKeepingMinimalTest {



    @Test
    public void positive() {
        StackKeepingMinimal stack = new StackKeepingMinimal();
        stack.push(3);
        assertSame(stack.getMin(), 3);
        stack.push(2);
        assertSame(stack.getMin(), 2);
        stack.push(5);
        assertSame(stack.getMin(), 2);
        stack.push(2);
        assertSame(stack.getMin(), 2);
        stack.push(1);
        assertSame(stack.getMin(), 1);

        stack.pop();
        assertSame(stack.getMin(), 2);
        stack.pop();
        assertSame(stack.getMin(), 2);
        stack.pop();
        assertSame(stack.getMin(), 2);
        stack.pop();
        assertSame(stack.getMin(), 3);

    }




}
