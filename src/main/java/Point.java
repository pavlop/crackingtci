/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            double diffSlope = (Point.this.slopeTo(o1) - Point.this.slopeTo(o2));
            if(diffSlope > 0.0) return 1;
            if(diffSlope < 0.0) return -1;
            return 0;
        }
    };

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    // The slopeTo() method should return the slope between the invoking point (x0, y0)
    // and the argument point (x1, y1), which is given by the formula (y1 − y0) / (x1 − x0).
    // Treat the slope of a horizontal line segment as positive zero;
    // treat the slope of a vertical line segment as positive infinity;
    // treat the slope of a degenerate line segment (between a point and itself) as negative infinity.
    public double slopeTo(Point that) {
        if (this.x == that.x && this.y == that.y) return Double.NEGATIVE_INFINITY;
        if (this.y == that.y) return 0;
        if (this.x == that.x) return Double.POSITIVE_INFINITY;
        return (1.0*(that.y - this.y))/(that.x - this.x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    // The compareTo() method should compare points by their y-coordinates,
    // breaking ties by their x-coordinates.

    // Formally, the invoking point (x0, y0) is less than the argument point (x1, y1)
    // if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
    public int compareTo(Point that) {
        if (this.y == that.y)
            return this.x - that.x;
        return this.y - that.y;
    }

    // return string representation of this point
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        Point p, q, r;
        p = new Point(87, 479);
        q = new Point(87, 479);
        assert p.slopeTo(q) == Double.NEGATIVE_INFINITY;
        p = new Point(25681, 22210);
        q = new Point(25681, 22210);
        assert p.slopeTo(q) == Double.NEGATIVE_INFINITY;
        p = new Point(3, 4);
        q = new Point(3, 4);
        assert p.slopeTo(q) == Double.NEGATIVE_INFINITY;
        p = new Point(8, 5);
        q = new Point(9, 4);
        r = new Point(8, 5);
        assert p.SLOPE_ORDER.compare(q, r) == 1;
        assert p.slopeTo(q)    == -1.0;
        assert p.slopeTo(r)    == Double.NEGATIVE_INFINITY;
    }
}