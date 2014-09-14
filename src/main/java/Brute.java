import java.util.Arrays;

public class Brute {
    public static void main(String[] args) {

        In inputFile = new In(args[0]);
        // How many points do we have in the input file?
        int n = inputFile.readInt();
        Point[] points = new Point[n];
        // Rescale coordinate system for proper visualization.
        StdDraw.setXscale(0, 32768); StdDraw.setYscale(0, 32768);

        for (int i = 0; !inputFile.isEmpty(); i++) {
            int x = inputFile.readInt();
            int y = inputFile.readInt();
            points[i] = new Point(x, y);
        }

        for (int l1 = 0; l1 < n; l1++) {
            points[l1].draw();
            for (int l2 = 0; l2 < n; l2++) {
                if (points[l1].compareTo(points[l2]) >= 0) continue; // first should be smaller then second
                double slopeprFirstTwo = points[l1].slopeTo(points[l2]);

                for (int l3 = 0; l3 < n; l3++) {
                    if (slopeprFirstTwo != points[l1].slopeTo(points[l3])
                            || points[l2].compareTo(points[l3]) >= 0) continue;

                    for (int l4 = 0; l4 < n; l4++) {
                        if (slopeprFirstTwo != points[l1].slopeTo(points[l4])
                                || points[l3].compareTo(points[l4]) >= 0) continue;
                        points[l1].drawTo(points[l4]);
                        StdOut.println(points[l1] + " -> " + points[l2] + " -> " + points[l3] + " -> " + points[l4]);
                    }
                }
            }
        }

    }
}