package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 *
 */
public class ValidSudoku {
    Set<Character> nums = new HashSet<Character>();
    {
        nums.addAll(Arrays.asList(new Character[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '.'}));
    }

    public boolean isValidSudoku(char[][] board) {
        if (board.length != 9 || board[0].length != 9 ) return false;
        for (int i = 0; i < 9; i++) {
            //VALIDATE LINES
            Set<Character> localNumsForLines = new HashSet<Character>(nums);
            for(char c : board[i]) {
                if(!localNumsForLines.contains(c)) return false;
                if(c != '.') localNumsForLines.remove(c);
            }

            //VALIDATE COLUMNS
            Set<Character> localNumsForCols = new HashSet<Character>(nums);
            for(int j = 0; j < 9; j++) {
                if(!localNumsForCols.contains(board[j][i])) return false;
                if(board[j][i] != '.') localNumsForCols.remove(board[j][i]);
            }

            //VALIDATE SQUARES
            if(i%3 == 0) for(int j = 0; j < 9; j+= 3) if(!validateSquare3x3(board, i, j)) return false;
        }
        return true;
    }

    public boolean validateSquare3x3(char[][] board, int i, int j) {
        Set<Character> localNumsForSq1 = new HashSet<Character>(nums);
        for (int x = 0; x < 3; x ++) {
            for (int y = 0; y < 3; y ++) {
                if(!localNumsForSq1.contains(board[i+x][j+y])) return false;
                if(board[i+x][j+y] != '.') localNumsForSq1.remove(board[i+x][j+y]);
            }
        }
        return true;
    }
}
