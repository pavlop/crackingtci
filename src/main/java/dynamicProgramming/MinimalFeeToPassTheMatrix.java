package dynamicProgramming;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertSame;

/**
 * Задача для самостоятельного решения 3.2
 Задана таблица натуральных чисел A[1..N, 1..M], N ≤ М. За каждый проход через ячейку таблицы с ко-
 ординатами (i, j) взимается штраф Ai,j. Найти минимальный суммарный штраф, с которым можно прой-
 ти из ячейки с координатами (1, 1) в ячейку с координатами (N, М). При этом из текущей ячейки можно
 переходить в любую из трех соседних ячеек, стоящих в строке с номером, на единицу большим текуще-
 го номера строки.

!Note. Edge cases: N = 2 or 1 ...
 */
public class MinimalFeeToPassTheMatrix {
    public int findMinimalFeePath (int[][] array) {
        int[][] minFees = new int[array.length][array[0].length];
        minFees[0][0] = array[0][0];
        //minFees[1][0] = minFees[0][0] + array[1][0];
        //minFees[1][1] = minFees[0][0] + array[1][1];
        for (int i = 1; i < minFees.length; i++) {
            for (int j = 0; j < minFees[0].length; j++) {
                if (j==0) {
                    minFees[i][0] = array[i][0]+ MyArrayUtils.getMinElement(minFees[i-1][0],minFees[i-1][1]);
                } else if (j == i) {
                    minFees[i][i] = array[i][i]+ minFees[i-1][i-1];
                } else {
                    System.out.println("i"+i+" j"+j);
                    minFees[i][j] = array[i][j]
                            + MyArrayUtils.getMinElement(minFees[i-1][j-1],minFees[i-1][j],getEmentOrInfinity(array, i-1,j+1));
                }

            }

        }
        System.out.println("minFees="+ Arrays.deepToString(minFees));
        return minFees[array.length-1][array[0].length-1];
    }

    private int getEmentOrInfinity(int[][] arr, int i, int j ) {
        if(arr.length <= i || arr[0].length <= j) return Integer.MAX_VALUE;
        return arr[i][j];
    }

    @Test
    public void test(){
        int[][] arr =
        {{1,3},{3,1},{1,3},{3,1},{1,3},{3,1}};
        assertSame(6, findMinimalFeePath(arr));

    }



}
