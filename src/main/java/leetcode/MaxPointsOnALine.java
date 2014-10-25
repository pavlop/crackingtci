package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for a point.

 * }
 */
public class MaxPointsOnALine {

    public int maxPoints(Point[] points) {
        if(points.length == 0) return 0;
        int maxPointsSameSlop = 0;
        for (int i = 0; i < points.length; i++) {
            Point base = points[i];
            Map<Double, Integer> slopes = new HashMap<Double, Integer>();
            int samePointCounter = 0;
            int maxWithoutSamePoint = 0;
            for (int j = i+1; j<points.length; j++) {
                Double curSlope = slopeBetween(base, points[j]);
                int curCount = 0;
                if (curSlope == Double.NEGATIVE_INFINITY )  {
                    samePointCounter++;
                } else {
                    curCount = smartPutAndGetCount(slopes, curSlope);
                }
                maxWithoutSamePoint = Math.max(maxWithoutSamePoint, curCount);
            }
            maxPointsSameSlop = Math.max(maxPointsSameSlop, maxWithoutSamePoint + samePointCounter);
        }
        return maxPointsSameSlop + 1;
    }

    int smartPutAndGetCount(Map<Double, Integer> map, Double key) {
        Integer curCount;
        if (map.containsKey(key)) {
            curCount = map.get(key) + 1;
        } else {
            curCount = 1;
        }
        map.put(key, curCount);
        return curCount;
    }

    public double slopeBetween(Point a, Point b) {
        if (a.x == b.x && a.y == b.y) return Double.NEGATIVE_INFINITY;
        if (a.y == b.y) return 0;
        if (a.x == b.x) return Double.POSITIVE_INFINITY;
        return (1.0*(b.y - a.y))/(b.x - a.x);
    }
}


class Point {
    int x;
    int y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(int a, int b) {
        x = a;
        y = b;
    }
}