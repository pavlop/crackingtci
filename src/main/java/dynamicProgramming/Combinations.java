package dynamicProgramming;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertSame;

/**
 * Created by pavlop on 3/24/14.
 */
public class Combinations {

    static int[][] intermediateResults;

    // calculate using straight formula
    public int doDirectFormula (int n, int k) {
        if (n < k ) return 0;
        return (int) (factorial(n)/((factorial(n-k)*factorial(k))));
    }

    // calculate using straight formula
    public int doDirectFormulaOptimized (int n, int k) {
        if (n < k ) return 0;
        if (k > n - k) {
            return (int) (factorial2numbers(n, k+1)/factorial(n-k));
        } else {
            return (int) (factorial2numbers(n, n-k+1)/factorial(k));
        }
    }

    private long factorial(int num) {
        return factorial2numbers(num, 1);
    }

    private long factorial2numbers (int num, int startFrom) {
        System.out.println("factorial for num="+num +", from="+startFrom);
        long res = 1;
        for (int i = startFrom ; i <= num; i++) {
            if (res > Long.MAX_VALUE/i) throw new RuntimeException("Too big bnumber");
            res *=i;
        }

        return res;
    }

    // use recurrent formula
    // C(n, k)=C(n-1, k-1)+C(n-1, k)
    public int doRecurrentFormula(int n, int k) {
        if (n==k) return 1;
        if (k==0) return 1;
        //System.out.println("n,k="+n+","+k);
        return doRecurrentFormula(n-1, k-1) + doRecurrentFormula(n - 1, k);
    }

    // save tmp results
    // C(n, k)=C(n-1, k-1)+C(n-1, k)
    public int doRecurrentFormulaSaveResults(int n, int k) {

        if (n==k) {intermediateResults[k][n] = 1; return 1;}
        if (k==0) {intermediateResults[k][n] = 1; return 1;}
        //System.out.println("n,k="+n+","+k);
        int recurrent1 = intermediateResults[k-1][n-1]>0?intermediateResults[k-1][n-1]:doRecurrentFormulaSaveResults(n-1, k-1);
        int recurrent2 = intermediateResults[k][n-1]>0?intermediateResults[k][n-1]:doRecurrentFormulaSaveResults(n-1, k);
        int sum = recurrent1 + recurrent2;
        intermediateResults[k][n] = sum;

        System.out.println("intermediateResults:" + Arrays.deepToString(intermediateResults));
        return sum;
    }
      
    // build pascal triangle
    public int doUsingPascalTriangle(int n, int k){
        int[][] pascal = new int[n+1][n+1];
        for (int i=0; i<pascal.length; i++) {
            for (int j = 0; j<=i; j++) {
                if (i == 0 || j==0||i == j) {
                    pascal[i][j]=1;
                } else {
                    pascal[i][j]=pascal[i-1][j-1]+pascal[i-1][j];
                }
            }
        }
        //System.out.println("pascal"+Arrays.deepToString(pascal));
        return pascal[n][k];
    }








    @Test (expected = Exception.class)
    public void testDirectFormula() {
        long start = System.currentTimeMillis();
        assertSame(5, doDirectFormula(5, 1));
        assertSame(6, doDirectFormula(4, 2));
        assertSame(15, doDirectFormula(6, 2));
        assertSame(20, doDirectFormula(6, 3));
        for (int i = 0 ; i< 1000; i++) {
            doDirectFormula(50, 5);
        }
        System.out.println("direct formula time:"+(System.currentTimeMillis()-start));
    }

    @Test
    public void testDirectFormulaOptimized() {
        long start = System.currentTimeMillis();
        assertSame(5, doDirectFormulaOptimized(5, 1));
        assertSame(6, doDirectFormulaOptimized(4, 2));
        assertSame(15, doDirectFormulaOptimized(6, 2));
        assertSame(20, doDirectFormulaOptimized(6, 3));
        for (int i = 0 ; i< 1000; i++) {
            doDirectFormulaOptimized(50, 5);
        }
        System.out.println("direct formula time:"+(System.currentTimeMillis()-start));
    }


    @Test
    public void testRecursiveFormula() {
        long start = System.currentTimeMillis();
        assertSame(5, doRecurrentFormula(5, 1));
        assertSame(6, doRecurrentFormula(4, 2));
        assertSame(15, doRecurrentFormula(6, 2));
        assertSame(20, doRecurrentFormula(6, 3));
        for (int i = 0 ; i< 1000; i++) {
            doRecurrentFormula(50, 5);
        }
        System.out.println(" recurrent time:"+(System.currentTimeMillis()-start));
    }

    //@Test
    public void testRecursiveFormulaSaveResults() {
        long start = System.currentTimeMillis();
        //assertSame(5, doRecurrentFormulaSaveResults(5, 1, null));
        //assertSame(6, doRecurrentFormulaSaveResults(4, 2, null));
        //assertSame(15, doRecurrentFormulaSaveResults(6, 2, null));
        intermediateResults = new int[5][7];
        assertSame(20, doRecurrentFormulaSaveResults(6, 3));
        intermediateResults = new int[501][251];
        for (int i = 0 ; i< 1000; i++) {
        //    doRecurrentFormulaSaveResults(500, 250);
        }
        System.out.println(" recurrent time save results:"+(System.currentTimeMillis()-start));
    }

    @Test
    public void testPascalTriangle() {
        long start = System.currentTimeMillis();
        assertSame(20, doUsingPascalTriangle(6, 3));
        for (int i = 0 ; i< 1000; i++) {
            doUsingPascalTriangle(50, 5);
        }
        System.out.println("time pascal:"+(System.currentTimeMillis()-start));
    }
}
