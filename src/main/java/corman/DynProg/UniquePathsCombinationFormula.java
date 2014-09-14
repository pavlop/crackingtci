package corman.DynProg;

/**
 *
 *
 *
 */
public class UniquePathsCombinationFormula {
    public int uniquePaths(int m, int n) {
        return combination(m + n - 2, n - 1);
    }

    private int combination(int m, int n){
        if(n > m / 2)   return combination(m, m - n);
        else{
            double result = 1;
            for(int i = 1; i <= n; i++){
                result *= m - n + i;
                result /= i;
            }
            return (int)result;
        }
    }
}
