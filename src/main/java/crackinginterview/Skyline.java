package crackinginterview;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *
 */
public class Skyline {
    public List<Integer> getOutline(int[][] tripples) {
        assert (tripples.length > 0);
        assert (tripples[0].length == 3);

        List<Integer> res = new ArrayList<>();

        for (int trIdx = 0; trIdx < tripples.length; trIdx++) {
            int curTripple ;
        }

        return res;
    }

    @Test
    public void test() {
        int[][] skyscraper = new int[][]{{0, 5, 3}, {3, 2, 5}};
        Assert.assertEquals("[0, 5, 3, 2, 5, 0]", "" + getOutline(skyscraper));
    }
}
