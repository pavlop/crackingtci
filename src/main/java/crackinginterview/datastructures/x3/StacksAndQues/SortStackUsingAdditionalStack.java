package crackinginterview.datastructures.x3.StacksAndQues;
import org.junit.Test;

import static junit.framework.TestCase.assertSame;

/**
 * Created by pavlop on 4/2/14.
 */
public class SortStackUsingAdditionalStack {

   public Stack<Integer> sortUsingOneAdditionalStack(Stack<Integer> stack) {
       Stack<Integer> sorted = new Stack<Integer>();
       while (stack.size > 0) {
           Integer toMove = stack.pop();
           pushToSorted(toMove, sorted, stack);
       }
       return sorted;
   }

    private void pushToSorted(Integer toMove, Stack<Integer> sorted, Stack<Integer> buffer) {
        int counter = 0;
        while (sorted.size >0 && sorted.peek() < toMove) {
            buffer.push(sorted.pop());
            counter++;
        }
        sorted.push(toMove);
        for (int i=0; i<counter; i++) {
            sorted.push(buffer.pop());
        }
    }

    @Test
    public void doTestsortUsingOneAdditionalStack() {
        Stack<Integer> mstack= new Stack<Integer>();
        mstack.push(2);mstack.push(3);mstack.push(1);mstack.push(4);
        Stack soreted = sortUsingOneAdditionalStack(mstack);
        System.out.println("sorted:"+soreted.toString());
        assertSame(1, soreted.pop());
        assertSame(2, soreted.pop());
        assertSame(3, soreted.pop());
        assertSame(4, soreted.pop());
        assertSame(null, soreted.pop());
    }
}

