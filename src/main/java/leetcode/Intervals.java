package leetcode;

import junit.framework.Assert;
import org.junit.Test;

import java.util.*;

public class Intervals {

    List intervals = new LinkedList<Interval>();
    int covered = 0;

    public void addInteval(int from, int to) {
        Interval newInterval = new Interval(from, to);
        int posToInsert = 0;
        for (Iterator<Interval> it = intervals.iterator(); it.hasNext();) {
            Interval cur = it.next();
            if (cur.to < newInterval.from) {
                posToInsert++;
                continue;// cur goes before new. skip
            } else if (cur.from > newInterval.to) {
                break;
            } else {
                newInterval.from = Math.min(newInterval.from, cur.from);
                newInterval.to = Math.max(newInterval.to, cur.to);
                it.remove(); // remove cur element as it is covered by new
                covered -= (cur.to - cur.from);
            }
        }

        intervals.add(posToInsert, newInterval);
        covered += newInterval.to - newInterval.from;

    }

    int getTotalCoveredLength() {
        return covered;
    }

    public static class Interval {
        public int from;
        public int to;
        public Interval(int from, int to) {this.from = from; this.to = to;}
        public String toString() {
            return "["+from+" "+to+"]";
        }
    }



    @Test
    public void test() {
        Intervals testInter = new Intervals();
        Assert.assertEquals("[]", ""+testInter.intervals);

        testInter.addInteval(5, 7);
        testInter.addInteval(3, 4);
        testInter.addInteval(9, 20);
        Assert.assertEquals("[[3 4], [5 7], [9 20]]", "" + testInter.intervals);
        Assert.assertEquals(1+2+11, testInter.getTotalCoveredLength());

        testInter.addInteval(6, 10);
        Assert.assertEquals("[[3 4], [5 20]]", ""+testInter.intervals);
        Assert.assertEquals(16, testInter.getTotalCoveredLength());

    }

}