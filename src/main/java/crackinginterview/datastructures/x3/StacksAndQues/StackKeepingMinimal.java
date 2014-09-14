package crackinginterview.datastructures.x3.StacksAndQues;

/**
 * Created by pavlop on 3/20/14.
 */
public class StackKeepingMinimal extends Stack<Integer> {
    Stack<Integer> minimums = new Stack<Integer>();

    public void push(int value) {
        if (minimums.peek() == null || value <= minimums.peek()) {
            minimums.push(value);
        }
        super.push(value);

    }

    public Integer pop() {
        if (this.peek() == minimums.peek()) {
            minimums.pop();
        }
        return super.pop();
    }


    public int getMin() {
        return minimums.peek();
    }


}
