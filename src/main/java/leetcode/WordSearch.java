package leetcode;

import junit.framework.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 *
 *
 */
public class WordSearch {

    public boolean exist(char[][] board, String word) {
        for (int i = 0 ; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] == word.charAt(0)) {
                    boolean[][] visited = new boolean[board.length][board[0].length];
                    boolean res =  canFindTheWord(i, j, board, word, 0, visited);
                    if(res) return true;
                }
            }
        }
        return false;
    }

    boolean canFindTheWord(int i, int j, char[][] board, String word, int startAt, boolean[][] visited) {
        if (startAt == word.length()) return true;
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) return false;
        if (board[i][j] != word.charAt(startAt)) return false;

        if (visited[i][j]) return false;
        visited[i][j] = true;

        boolean result = false;
        result = result || canFindTheWord(i-1, j, board, word, startAt+1, visited);
        result = result || canFindTheWord(i+1, j, board, word, startAt+1, visited);
        result = result || canFindTheWord(i, j-1, board, word, startAt+1, visited);
        result = result || canFindTheWord(i, j+1, board, word, startAt+1, visited);

        visited[i][j] = false;
        return result;
    }



    @Test
    public void test() {
        char[][] board;


        board = new char[][] {"ab".toCharArray()};
        Assert.assertTrue(exist(board, "ba"));


        board = new char[][] {
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(),
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaX".toCharArray(),
        };
        String word =
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        Assert.assertFalse(exist(board, word));

    }
}
