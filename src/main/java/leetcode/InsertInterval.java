package leetcode;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

 You may assume that the intervals were initially sorted according to their start times.

 Example 1:
 Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

 Example 2:
 Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

 This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 */

public class InsertInterval {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
          return insert2(intervals, newInterval);
//        if(intervals.size() == 0) {
//            intervals.add(newInterval);
//            return intervals;
//        }
//
//        //cases when add new node to the end or at the beginning
//        if(newInterval.start > intervals.get(intervals.size()-1).end) {intervals.add(newInterval); return intervals;}
//        if(newInterval.end < intervals.get(0).start) {intervals.add(0, newInterval); return intervals;}
//
//        int firstIntervalWithBiggerStartOrWithin = 0;
//
//        //find First interval that have bigger start then newInterval.start
//        while(firstIntervalWithBiggerStartOrWithin < intervals.size()){
//            if(isWithin(newInterval.start, intervals.get(firstIntervalWithBiggerStartOrWithin))) break;
//            if(intervals.get(firstIntervalWithBiggerStartOrWithin).start > newInterval.start) break;
//            firstIntervalWithBiggerStartOrWithin++;
//        }
//
//        //find last interval that have smaller end then newInterval.end
//        int lastInervalWithSmallerEndOrWithin = firstIntervalWithBiggerStartOrWithin;
//        while(lastInervalWithSmallerEndOrWithin < intervals.size()){
//            if(isWithin(newInterval.end, intervals.get(lastInervalWithSmallerEndOrWithin))) break;
//            if(intervals.get(lastInervalWithSmallerEndOrWithin).end > newInterval.end) {
//                lastInervalWithSmallerEndOrWithin--;
//                break;
//            }
//            lastInervalWithSmallerEndOrWithin++;
//        }
//        if(lastInervalWithSmallerEndOrWithin == intervals.size()) lastInervalWithSmallerEndOrWithin--;
//
////        System.out.println("firstIntervalWithBiggerStartOrWithin="+firstIntervalWithBiggerStartOrWithin + " lastInervalWithSmallerEndOrWithin="+lastInervalWithSmallerEndOrWithin);
//
//        //new combined interval
//        int superStart = Math.min(newInterval.start, intervals.get(firstIntervalWithBiggerStartOrWithin).start);
//        int superEnd = Math.max(newInterval.end, intervals.get(lastInervalWithSmallerEndOrWithin).end);
//        Interval superInterval = new Interval(superStart, superEnd);
//
//        // remove all nodes between start and end
//        int numOfIntervalsToRemove = lastInervalWithSmallerEndOrWithin - firstIntervalWithBiggerStartOrWithin + 1;
//        for (int i = 0; i < numOfIntervalsToRemove; i++)
//            intervals.remove(firstIntervalWithBiggerStartOrWithin);
//        intervals.add(firstIntervalWithBiggerStartOrWithin, superInterval);
//
//        return intervals;
    }

    public boolean  isWithin(int i, Interval inter) {
        return i<=inter.end && i >= inter.start;
    }

    public List<Interval> insert2(List<Interval> intervals, Interval newInterval) {
        List<Interval> ret = new ArrayList<Interval>();
        for (int i = 0; i < intervals.size(); i++) {

            if (newInterval.start > intervals.get(i).end) // If newInterval is BEHIND the current interval
                ret.add(intervals.get(i)); // then the current interval goes to the result

            else if (newInterval.end < intervals.get(i).start)  { // If new Interval is BEFORE the current interval
                ret.add(newInterval); // then new interval goes to the result
                newInterval = intervals.get(i); // and save the current interval for later
            }

            else { // If newInterval OVERLAPS WITH the current interval Then simply merge the two intervals.
                newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
                newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            }
        }
        ret.add(newInterval);  // In the end, there will be one interval that is still not stored in the result; and this interval, regardless where it comes from the vector or the newInterval, must be stored in newInterval at this point.
        return ret;
    }

    @Test
    public void test() {
        List<Interval> intervals = new ArrayList<Interval>();
        intervals.add(new Interval(1, 5));
        intervals.add(new Interval(6, 8));
        Assert.assertEquals("[[1,8]]", ""+insert(intervals, new Interval(5,6)));

        intervals = new ArrayList<Interval>();
        intervals.add(new Interval(1, 5));
        Assert.assertEquals("[[1,7]]", ""+insert(intervals, new Interval(2,7)));

        intervals = new ArrayList<Interval>();
        intervals.add(new Interval(1, 5));
        Assert.assertEquals("[[0,6]]", ""+insert(intervals, new Interval(0,6)));

        intervals = new ArrayList<Interval>();
        intervals.add(new Interval(1, 5));
        Assert.assertEquals("[[1,5], [6,8]]", ""+insert(intervals, new Interval(6,8)));

        intervals = new ArrayList<Interval>();
        intervals.add(new Interval(0, 4));
        intervals.add(new Interval(7, 12));
        Assert.assertEquals("[[0,5], [7,12]]", ""+insert(intervals, new Interval(0,5)));

    }
}





class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }

    @Override
    public String toString() {
        return "[" +start + ","+end+']';
    }
}

