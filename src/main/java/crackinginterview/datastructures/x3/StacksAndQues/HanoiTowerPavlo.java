package crackinginterview.datastructures.x3.StacksAndQues;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by pavlop on 3/23/14.
 */
public class HanoiTowerPavlo {

    Stack<Integer>[] towers = new Stack[]{new Stack<Integer>(), new Stack<Integer>(),new Stack<Integer>()};

    public  void solveHanoiProblem(int numOfDisks){

        //inicializing 1st tower
        for(int i = 0; i < numOfDisks; i++) {
            towers[0].push(numOfDisks - i);
        }
        System.out.println("towers:"+ Arrays.deepToString(towers));
        move(numOfDisks, 0, 2, 1);
        System.out.println("towers:"+ Arrays.deepToString(towers));

    }

     boolean canMove (int fromID, int toID) {
        Stack<Integer> from = towers[fromID];
        Stack<Integer> to = towers[toID];

        if (from == null || from.size == 0 || to == null) return false;
        if (to.size == 0 || from.peek() < to.peek()) return true;
        return false;
    }

   void performMove (int fromID, int toID) {
        Stack<Integer> from = towers[fromID];
        Stack<Integer> to = towers[toID];
        Integer toMove = from.pop();
        System.out.println("Moving:" + toMove + " from: " + fromID + " to:"+ toID);
        to.push(toMove);
    }

    void move (int diskNumber, int fromID, int toID, int tmpID) {
        if (diskNumber > 0) {
            move (diskNumber-1, fromID, tmpID, toID);
            //System.out.println("fromID:"+fromID+" to:"+toID);
            performMove(fromID, toID);
            move(diskNumber-1, tmpID, toID, fromID);
        }
    }

    @Test
    public void doTheTowers() {
        System.out.println("starting...");
        solveHanoiProblem(3);
        assertTrue(true);
    }
}
