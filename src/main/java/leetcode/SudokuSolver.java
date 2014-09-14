package leetcode;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 Write a program to solve a Sudoku puzzle by filling the empty cells.

 Empty cells are indicated by the character '.'

 You may assume that there will be only one unique solution.
 */
public class SudokuSolver {

    boolean isSolved;
    private static int fieldSize;

    Set<Character> possibleValues = new HashSet<Character>();
    {
        possibleValues.addAll(Arrays.asList(new Character[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'}));
    }

    public void solveSudoku(char[][] board) {
        isSolved = false;
        fieldSize = board.length;
        solveSudoku(board, 0, 0);
    }

    public void solveSudoku(char[][] board, int startX, int startY) {
//        System.out.println("Atart for: " + Arrays.deepToString(board));

        if (isSolved) {
//            System.out.println("sol. found: " + Arrays.deepToString(board));
            return;
        }

        int[] curIds = new int[]{startX, startY};

        while (curIds[0] < fieldSize) {
            int i = curIds[0];
            int j = curIds[1];
            int[] nexIds = nextIndex(i, j);

            if(board[i][j] == '.') {
                Set<Character> possibleForCurPosition = getPossibleValidNumbers(board, i, j);
                // no suitable canditates for current dot
                if(possibleForCurPosition.isEmpty()) return;

                // try each possible
                for (char c : possibleForCurPosition ) {
                    board[i][j] = c;
                    solveSudoku(board,  nexIds[0], nexIds[1]);
                    if (isSolved) return;
                }

                // if we are here - the solution was not found
                if(!isSolved) {
                    board[i][j] = '.';
                    return;
                }
            }
            curIds = nexIds;
        }

//        System.out.println("normal return startX="+startX+ " startY="+startY);
        // if we came here - we were able to find suitable numbers for all dots
        isSolved = true;
    }

    private static int[] nextIndex(int x, int y) {
        if(y < fieldSize - 1) return new int[]{x, y+1};
        else return new int[]{x+1, 0};
    }

    public Set<Character> getPossibleValidNumbers(char[][] board, int x, int y) {

        Set<Character> res = new HashSet<Character>(possibleValues);

        //validate line and column
        for(int i = 0; i < fieldSize; i++) {
            if(res.contains(board[x][i])) res.remove(board[x][i]);
            if(res.contains(board[i][y])) res.remove(board[i][y]);
        }

        //validate square
        int squareXStart = x - x%3;
        int squareYStart = y - y%3;

        for (int i = squareXStart; i < squareXStart + 3; i++)
            for (int j = squareYStart; j < squareYStart + 3; j++)
                if(res.contains(board[i][j])) res.remove(board[i][j]);

        return res;
    }

    @Test
    public void test() {
        char[][] input = {
                "1..".toCharArray(),
                "4.6".toCharArray(),
                "98.".toCharArray()
        };

//        solveSudoku(input);
//        Assert.assertEquals("[[1, 2, 3], [4, 5, 6], [9, 8, 7]]", Arrays.deepToString(input));

        solveSudoku(input);
        char[][] input2 = {
                "..9748...".toCharArray(),
                "7........".toCharArray(),
                ".2.1.9...".toCharArray(),
                "..7...24.".toCharArray(),
                ".64.1.59.".toCharArray(),
                ".98...3..".toCharArray(),
                "...8.3.2.".toCharArray(),
                "........6".toCharArray(),
                "...2759..".toCharArray()
        };


        System.out.println("Before:"+Arrays.deepToString(input2));
        solveSudoku(input2);
        System.out.println("After :"+Arrays.deepToString(input2));

    }
}
