package corman.DynProg;

import org.junit.Test;
import java.util.*;

/**
 *
 */
public class HanoiTowers {

    List<Stack<Integer>> towers = new ArrayList<Stack<Integer>>();
    int moves = 0;
    HashMap<String, Integer> subProblemCalls = new HashMap<String, Integer>();

    public void start (int height) {
        towers.add(new Stack<Integer>());
        towers.add(new Stack<Integer>());
        towers.add(new Stack<Integer>());

        for (int i = 0; i < height; i++) {
            towers.get(0).push(height - i);
        }

        printTowers();
        doHanoiRecursive (height, 0, 2, 1);
        printTowers();
    }

    private void printTowers() {
        System.out.println("0:" + towers.get(0));
        System.out.println("1:" + towers.get(1));
        System.out.println("2:" + towers.get(2));
        System.out.println("moves " + moves);
        System.out.println("subproblems:" + subProblemCalls);

    }

    public void doHanoiRecursive(int numToMove, int from, int to, int stack) {

        if(numToMove == 1) {
            singleMove(from, to);
            return;
        }

        String key = numToMove+":"+from+"->"+to;
        if(subProblemCalls.containsKey(key)) {
            subProblemCalls.put(key, subProblemCalls.get(key) + 1);
        } else {
            subProblemCalls.put(key, 1);
        }

        doHanoiRecursive(numToMove - 1, from, stack, to);
        singleMove(from, to);
        doHanoiRecursive(numToMove - 1, stack, to, from);
    }

    private void singleMove (int idFrom, int idTo) {
        moves++;
        int elemntToMove = towers.get(idFrom).pop();
        towers.get(idTo).add(elemntToMove);
    }

    @Test
    public void testHanoi() {
        HanoiTowers ht = new HanoiTowers();
        ht.start(3);

        ht = new HanoiTowers();
        ht.start(10);

        ht = new HanoiTowers();
        ht.start(20);
    }

}
