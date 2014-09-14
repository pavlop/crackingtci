package crackinginterview.datastructures.x3.StacksAndQues;
import org.junit.Test;

import static junit.framework.TestCase.assertSame;

/**
 *
 * Write a program to sort a stack in ascending order (with biggest items on top).
 * You may use at most one additional stack to hold items, but you may not copy
 * the elements into any other data structure (such as an array).
 * The stack supports the following operations: push, pop, peek, and isEmpty.
 */
public class SortStackQuickSort {

    public Stack<Integer> sortUsingQuickSort(Stack<Integer> stack, SortType type) {
        System.out.println("qs:"+stack.toString());

        if(stack.size <= 1) return stack;

        Stack<Integer> less = new Stack<Integer>();
        Stack<Integer> more = new Stack<Integer>();
        Integer pivotElement = stack.peek();
        while(stack.size > 0) {
            if((stack.peek() > pivotElement)) {
                more.push(stack.pop());
            } else {
                less.push(stack.pop());
            }
        }
        System.out.println("less:"+less.toString());
        System.out.println("more:"+more.toString());

        less = sortUsingQuickSort(less, SortType.ASC);
        more = sortUsingQuickSort(more, SortType.DESC);

        if (type == SortType.ASC)
            stack = merge (less, more);
        if (type == SortType.DESC)
            stack = merge(more, less);

        return stack;
    }

    private Stack<Integer> merge(Stack<Integer> left, Stack<Integer> right) {
        System.out.println("Merge " + left + " with "+ right);
        while (right.size > 0 ) {
            left.push(right.pop());
        }
        System.out.println("reult:"+ left);
        return left;
    }

    @Test
    public void doTestsortUsingOneAdditionalStack() {
        Stack<Integer> mstack= new Stack<Integer>();
        mstack.push(2);mstack.push(3);mstack.push(1);mstack.push(4);
        Stack soreted = sortUsingQuickSort(mstack, SortType.DESC);
        System.out.println("sorted:"+soreted.toString());
        assertSame(1, soreted.pop());
        assertSame(2, soreted.pop());
        assertSame(3, soreted.pop());
        assertSame(4, soreted.pop());
        assertSame(null, soreted.pop());
    }
}

enum SortType {
    ASC, DESC
}

