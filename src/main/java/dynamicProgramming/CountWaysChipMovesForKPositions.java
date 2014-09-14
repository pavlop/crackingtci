package dynamicProgramming;

import org.junit.Test;

import static junit.framework.TestCase.assertSame;

/**
 * Created by pavlop on 3/27/14.
 * Фишка может двигаться по полю длины N только вперед. Длина хода фишки не более К. Найти количе-
 ство различных путей, по которым фишка может пройти поле от позиции 1 до позиции N.
 */
public class CountWaysChipMovesForKPositions {

    public long countWaysWithCache(int endPosition, int maxMoveAtOneTime, long[] cache) {
        if (endPosition <= 1) return 1;
        long result = 0;
        for (int i = 1; i <= maxMoveAtOneTime && i<endPosition; i++ ) {

            result += cache[endPosition] != 0 ? cache[endPosition] : countWaysWithCache(endPosition - i, maxMoveAtOneTime, cache);
        }
        cache[endPosition] = result;
        return result;
    }

    public long countWays(int endPosition, int maxMoveAtOneTime) {
        System.out.println("countWays: endPos="+endPosition);
        return countWaysWithCache(endPosition, maxMoveAtOneTime, new long[endPosition+1]);
    }

    @Test
    public void testCountWays() {
        assertSame(3l, countWays(4, 2));
    }
}
