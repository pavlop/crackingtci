package crackinginterview.datastructures.x3.StacksAndQues;

import org.junit.Test;
import static junit.framework.TestCase.assertSame;

/**
 *
 *
 3.3 Imagine a (literal) stack of plates. If the stack gets too high,
 it migh t topple. Therefore, in real life, we would likely start
 a new stack when the previous stack exceeds some threshold.
 Implement a data structure SetOfStacks that mimics this.
 SetOf- Stacks should be composed of several stacks and should create a new stack once
 the previous one exceeds capacity.
 SetOfStacks.push() and SetOfStacks. pop() should behave
 identically to a single stack
 (that is,pop()should return the same values as it would if there were just a single stack).
 FOLLOW UP
 Implement a function popAt(int index) which perform sapopoperationona specific sub-stack
 *
 */
public class MultiStack<T>  {
    Stack<Stack<T>> stacks = new Stack<Stack<T>>();
    public final int MAX_SIZE_PER_STACK = 3;

    public MultiStack() {
        stacks.push(new Stack<T>());
    }

    public void push(T element) {
        Stack currentStack = stacks.peek();
        if (currentStack.size >= MAX_SIZE_PER_STACK) {
            currentStack = new Stack<T>();
            stacks.push(currentStack);
        }
        currentStack.push(element);
    }

    public T pop() {
        if (stacks.size == 0) return null;
        Stack<T> currentStack = stacks.peek();
        if (currentStack.size > 0) {
            return currentStack.pop();
        } else {
            stacks.pop();
            currentStack = stacks.peek();
            if (currentStack == null|| currentStack.size == 0) {
                return null;
            } else {
                return currentStack.pop();
            }
        }

    }

    public T peek() {
        System.out.println("peek():stacks.size="+stacks.size);
        if(stacks.size == 0)
            return null;
        if (stacks.peek().size > 0 ) {
            return  stacks.peek().peek();
        } else {
            stacks.pop();
            if(stacks.size == 0)
                return null;
            else
                return stacks.peek().peek();
        }
    }

    @Test
    public void checkStack() {
        MultiStack<Integer> mstack= new MultiStack<Integer>();
        mstack.push(1);mstack.push(2);mstack.push(3);
        assertSame(3, mstack.peek());
        mstack.push(4);
        assertSame(4, mstack.peek());
        assertSame(4, mstack.pop());
        assertSame(3, mstack.pop());
        assertSame(2, mstack.pop());
        assertSame(1, mstack.pop());
        assertSame(null, mstack.pop());
    }



}
