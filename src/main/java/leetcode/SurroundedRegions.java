package leetcode;

import junit.framework.Assert;
import org.junit.Test;

import java.util.*;

/**
 Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
 A region is captured by flipping all 'O's into 'X's in that surrounded region.

 X X X X
 X O O X
 X X O X
 X O X X

 After running your function, the board should be:

 X X X X
 X X X X
 X X X X
 X O X X




 SOLUTION:

1) Bredth first search starting from each not visited position where value is "O"
2) Store all values from area to Set<List>. List is a pair of int
3) cur node is "not surrounded" only if it is a boundary one, othervise it is surronded by other O or by X
4) If all nodes of the area are "surrounded" - mark the entire area with Xs

 */


/*+


 */
public class SurroundedRegions {
    boolean[][] visited;
    public void solve(char[][] board) {
        if(board.length == 0) return;
        visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) {
                    Set<List<Integer>> area = new HashSet<List<Integer>>();
                    area.add(createNode(i, j));
                    boolean isAreaSurrounded = investigateAreaStartingAt (board, area);
                    if(isAreaSurrounded)
                        for(List<Integer> node:area) board[node.get(0)][node.get(1)] = 'X';

                }

            }
        }

    }

    public boolean investigateAreaStartingAt( char[][] board, Set<List<Integer>> nodesInArea) {
        //if(i  < 0 || j < 0 || i >= board.length || j >= board[0].length) return false;
        Stack<List<Integer>> nodesToExplore = new Stack<List<Integer>>();
        nodesToExplore.addAll(nodesInArea);



        boolean isCurrentSurrounded = true;

        while (!nodesToExplore.isEmpty()) {

            List<Integer> curNode = nodesToExplore.pop();
            int i = curNode.get(0);
            int j = curNode.get(1);
            visited[i][j] = true;

            isCurrentSurrounded = isCurrentSurrounded && !isAtBoarder(i, j, board);

            if(!isOutOfBoarderOrX(i-1, j, board)) {
                List<Integer> node = createNode(i-1, j);
                if (!nodesInArea.contains(node)) {
                    nodesInArea.add(node);
                    nodesToExplore.add(node);
                }
            }

            if(!isOutOfBoarderOrX(i+1, j, board)) {
                List<Integer> node = createNode(i+1, j);
                if (!nodesInArea.contains(node)) {
                    nodesInArea.add(node);
                    nodesToExplore.add(node);
                }
            }

            if(!isOutOfBoarderOrX(i, j-1, board)) {
                List<Integer> node = createNode(i, j-1);
                if (!nodesInArea.contains(node)) {
                    nodesInArea.add(node);
                    nodesToExplore.add(node);
                }
            }

            if(!isOutOfBoarderOrX(i, j+1, board)) {
                List<Integer> node = createNode(i, j+1);
                if (!nodesInArea.contains(node)) {
                    nodesInArea.add(node);
                    nodesToExplore.add(node);
                }
            }
        }

        return isCurrentSurrounded;
    }

    public List<Integer> createNode(int i, int j) {
        return  new ArrayList<Integer>(Arrays.asList(new Integer[]{i, j}));
    }

    public boolean isAtBoarder(int i, int j, char[][]board) {
        return (i  == 0 || j == 0 || i == board.length - 1 || j == board[0].length - 1);
    }

    public boolean isOutOfBoarderOrX(int i, int j, char[][]board) {
        if(i  < 0 || j < 0 || i >= board.length || j >= board[0].length) return true;
        if (board[i][j] == 'X') return true;
        return false;
    }

    @Test
    public void test() {
        char[][]  input;
        input = new char[][]{
                "XXX".toCharArray(),
                "XOX".toCharArray(),
                "XXX".toCharArray()
        };
        solve(input);
        Assert.assertEquals("[[X, X, X], [X, X, X], [X, X, X]]", Arrays.deepToString(input));
        solve(input);


        input = new char[][]{{'O'}};
        solve(input);
        Assert.assertEquals("[[O]]", Arrays.deepToString(input));


        input = new char[][]{{'O', 'X'}};
        solve(input);
        Assert.assertEquals("[[O, X]]", Arrays.deepToString(input));


        input = new char[][]{{'X', 'X'},{'O', 'X'}};
        solve(input);
        Assert.assertEquals("[[X, X], [O, X]]", Arrays.deepToString(input));

    }

}



//    public boolean investigateAreaStartingAt(int i, int j, char[][] board, Set<List<Integer>> nodesInArea) {
//        //if(i  < 0 || j < 0 || i >= board.length || j >= board[0].length) return false;
//        visited[i][j] = true;
//        List<Integer> node = new ArrayList<Integer>(Arrays.asList(new Integer[]{i, j}));
//
//        //Already visited?
//        if(nodesInArea.contains(node)) return true;
//
//        nodesInArea.add(node);
//        boolean isCurrentSurrounded = true;
//        if(i  == 0 || j == 0 || i == board.length - 1 || j == board[0].length - 1) isCurrentSurrounded = false;
//
//        if(!isOutOfBoarderOrX(i-1, j, board)) isCurrentSurrounded = investigateAreaStartingAt(i-1, j, board, nodesInArea) && isCurrentSurrounded;
//        if(!isOutOfBoarderOrX(i+1, j, board)) isCurrentSurrounded = investigateAreaStartingAt(i+1, j, board, nodesInArea) && isCurrentSurrounded;
//        if(!isOutOfBoarderOrX(i, j-1, board)) isCurrentSurrounded = investigateAreaStartingAt(i, j-1, board, nodesInArea) && isCurrentSurrounded;
//        if(!isOutOfBoarderOrX(i, j+1, board)) isCurrentSurrounded = investigateAreaStartingAt(i, j+1, board, nodesInArea) && isCurrentSurrounded;
//
//        return isCurrentSurrounded;
//    }
