package crackinginterview.x8.dynamic;

import java.util.*;

/**
 * Given an infinite number of
 * quarters (25 cents),
 * dimes (10 cents),
 * nickels (5 cents)
 * and pennies (1 cent),
 * write code to calculate
 * the number of ways of representing n cents.

 */
public class CoinsInfiniteUNRESOLVED {
    public List<StackOfCoins> stacks ;

    public void printAllCombinations (Stack curRes, int targetSumLeft, int skipFirstStacks) {
        if (!curRes.isEmpty()) {
            if (targetSumLeft == 0) System.out.println(curRes);
            if (targetSumLeft <= 0) return;
        }

        for (int i = skipFirstStacks; i<stacks.size(); i++ ) {
            StackOfCoins stack = stacks.get(i);
            if(stack.hasMore() && stack.getNomenal() <= targetSumLeft) {
                //include coins form stack and continue
                // check thhat it will make sence
                curRes.push(stack.pop());
                printAllCombinations(curRes, targetSumLeft - stack.getNomenal(), i);
                curRes.pop();
            }
            printAllCombinations(curRes, targetSumLeft, i+1);
            stack.push();
        }

    }

    public static void main (String argsp[]) {
        System.out.println("TEST INFINITE");
        //TEST INFINITE
        CoinsInfiniteUNRESOLVED coinsInfinite = new CoinsInfiniteUNRESOLVED();
        coinsInfinite.stacks = Arrays.asList(
                (StackOfCoins) new InfinitStackOfCoins(50),
                (StackOfCoins) new InfinitStackOfCoins(25),
                (StackOfCoins) new InfinitStackOfCoins(10),
                (StackOfCoins) new InfinitStackOfCoins(5) );
        //coinsInfinite.printAllCombinations(new StackWithSum(), 100, 0);

        System.out.println("TEST LIMITED");

        //TEST LIMITED
        CoinsInfiniteUNRESOLVED coinsLimited = new CoinsInfiniteUNRESOLVED();
        coinsLimited.stacks = Arrays.asList(
                (StackOfCoins) new LimitedStackOfCoins(1).push(),
                (StackOfCoins) new LimitedStackOfCoins(50).push().push());
                //(StackOfCoins) new LimitedStackOfCoins(25).push().push(),
                //(StackOfCoins) new LimitedStackOfCoins(5).push().push().push().push().push().push());
        coinsLimited.printAllCombinations(new StackWithSum(), 100, 0);

    }

}

interface StackOfCoins {
    public boolean hasMore();
    public int getNomenal();
    public int pop();
    public StackOfCoins push();
    public int getSum();
}

class InfinitStackOfCoins implements StackOfCoins{
    private int nomenal = 0;
    public InfinitStackOfCoins (int nomenal) {
        this.nomenal = nomenal;
    }

    @Override
    public boolean hasMore() {
        return true;
    }

    @Override
    public int getNomenal() {
        return nomenal;
    }

    @Override
    public int getSum() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int pop() {
        return nomenal;
    }

    @Override
    public StackOfCoins push() {
        return this;
    }
}

class StackWithSum extends Stack<Integer> {
    private int sum = 0;

    @Override
    public Integer push(Integer integer) {
        sum += integer;
        return super.push(integer);
    }

    @Override
    public synchronized Integer pop() {
        Integer el = super.pop();
        sum -= el;
        return el;
    }

    public int getSum() {
        return sum;
    }
}

class LimitedStackOfCoins implements StackOfCoins{
    private int nomenal = 0;
    private int coutnt = 0;
    public LimitedStackOfCoins (int nomenal) {
        this.nomenal = nomenal;
    }

    @Override
    public boolean hasMore() {
        return coutnt>0;
    }

    @Override
    public int getNomenal() {
        return nomenal;
    }

    @Override
    public int getSum() {
        return coutnt*nomenal;
    }

    @Override
    public int pop() {
        if (coutnt >0 ) {
            coutnt--;
            return nomenal;
        } else {
            throw new RuntimeException("Trying to pop in empty collection");
        }

    }

    @Override
    public StackOfCoins push() {
        coutnt++;
        return this;
    }
}

