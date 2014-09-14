package crackinginterview.x8.dynamic;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

/**
 * Write an algorithm to prim all ways of arranging
 * eight queens on an 8x8 chess board so that none of them share the same row,
 * column or diagonal. In this case, "diagonal" means all diagonals,
 * not just the two that bisect the board.
 */
public class Queens {
    // pass current string occupation as bits of a byte
    // 0 - position is free
    // 1 - position is occupied
    public static void doQueensTask(int cutLineNum, final int[][] desk) {
        //System.out.println(cutLineNum + " Input:" + Arrays.deepToString(desk));
        if (cutLineNum >= desk.length) {
            //System.out.println("Success:"+cutLineNum);
            System.out.println("Success:"+Arrays.deepToString(desk));
            return;
        }


        //try to put queen on any free position in cur line
        for (int i = 0; i<desk[0].length; i++) {
            if (desk[cutLineNum][i] == 0) {

                //copy array
                int[][] newDesk = cloneArray(desk);

                placeQueen(cutLineNum, i, newDesk);
                doQueensTask(cutLineNum+1, newDesk);

                //cleanPastCurLine(cutLineNum, desk);
            }
        }

     }

    private static void cleanPastCurLine(int line, int[][] desk) {
        for (int i = line; i < desk.length; i++) {
            for (int j = 0; j < desk[0].length; j++) {
                desk[i][j] = 0;
            }
        }
    }

    private static void placeQueen(int line, int row,  int[][] desk) {
        desk[line][row] = 2;
        // no next line
        if (line == desk.length-1) return ;

        // create next line based on current one
        // mark next squares as occupied
        // 00000x00
        // 0000xxx0
        for (int i = line+1, diagonal = 1; i < desk.length; i++, diagonal++) {
            desk[i][row] = 1;
            if (row - diagonal >= 0) desk[i][row - diagonal] = 1;
            if (row + diagonal < desk[0].length) desk[i][row + diagonal] = 1;
        }

    }

    public static int[][] cloneArray(int[][] src) {
        int length = src.length;
        int[][] target = new int[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }



    @Test
    public void resolutionForQueen() {
        doQueensTask(0, new int[8][8]);
    }

    @Test
    public void placeCheckQueen() {
        System.out.println("TEST placeCheckQueen");
        int[][] desk = new int [3][3];

        placeQueen(0,0, desk);
        assertEquals("[[2, 0, 0], [1, 1, 0], [1, 0, 1]]", Arrays.deepToString(desk));

        cleanPastCurLine(1, desk);
        assertEquals("[[2, 0, 0], [0, 0, 0], [0, 0, 0]]", Arrays.deepToString(desk));

        cleanPastCurLine(0, desk);
        assertEquals("[[0, 0, 0], [0, 0, 0], [0, 0, 0]]", Arrays.deepToString(desk));

        placeQueen(0,2, desk);
        assertEquals("[[0, 0, 2], [0, 1, 1], [1, 0, 1]]", Arrays.deepToString(desk));

        int[][] desk4 = new int [4][4];
        placeQueen(0,1, desk4);
        assertEquals("[[0, 2, 0, 0], [1, 1, 1, 0], [0, 1, 0, 1], [0, 1, 0, 0]]", Arrays.deepToString(desk4));

    }
}
