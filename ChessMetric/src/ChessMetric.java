import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class ChessMetric {
	
	public long howMany(int size, int[] start, int[] end, int numMoves) {
        LinkedList<long[][]> snapshots = new LinkedList<>();
        long[][] startSnapshot = new long[size][size];
        startSnapshot[start[0]][start[1]] = 1; // thre is one way to get to start point with 0 moves
        snapshots.add(startSnapshot);

        for(int move = 1; move <= numMoves; move++ ) {
            long[][] previousSnapshot = snapshots.getLast();
            long[][] newSnapshot = new long[size][size];
            for (int i =0; i < size; i++)
                for (int j = 0; j < size; j++)
                    newSnapshot[i][j] = waysToComeToThisPoint(i, j, previousSnapshot);
            snapshots.add(newSnapshot);
        }
        return snapshots.getLast()[end[0]][end[1]];
	}

    long waysToComeToThisPoint(int i, int j, long[][] snapshot) {
        Collection<int[]> pointsFrom = generatePointsForPosition (i, j);
        filterPoints(pointsFrom, snapshot.length);
        long numOfWays = 0;
        for (int[] point : pointsFrom) {
            numOfWays += snapshot[point[0]][point[1]];
        }
        return numOfWays;
    }

    Collection<int[]> generatePointsForPosition(int i, int j) {
        Collection<int[]> points = new ArrayList<>();

        for(int direction : new int[]{-1,1}) {
            // king moves
            points.add(new int[]{i, j+direction});
            points.add(new int[]{i+direction, j});
            points.add(new int[]{i+direction, j+direction});
            points.add(new int[]{i+direction, j-direction});

            //knight moves
            points.add(new int[]{i+2*direction, j+1});
            points.add(new int[]{i+2*direction, j-1});
            points.add(new int[]{i+1, j+2*direction});
            points.add(new int[]{i-1, j+2*direction});
        }
        return points;
    }

    void filterPoints(Collection<int[]> points, int size) {
        for (Iterator<int[]> it = points.iterator(); it.hasNext();) {
            int[] point = it.next();
            if(point[0] < 0 || point[0] >= size) it.remove();
            else if(point[1] < 0 || point[1] >= size) it.remove();
        }
    }
}
