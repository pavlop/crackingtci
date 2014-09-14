import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class TeamBuilder {

    public int[] specialLocations(String[] paths) {
        int n = paths.length;
        float[][] distances = new float[n][n];

        //by default distance is infinite
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (i == j) {
                    distances[i][j] = 0.0f;
                } else if (paths[i].charAt(j) == '0') {
                    distances[i][j] = Float.POSITIVE_INFINITY;
                } else {
                    distances[i][j] = 1;
                }

        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);

        printArrays(distances);

        int countCanReachAll = 0;
        for (int i = 0; i < n; i++) {
            boolean canReachAll = true;
            for (int j = 0; j < n; j++) {
                if (distances[i][j] == Float.POSITIVE_INFINITY) canReachAll = false;
            }
            if (canReachAll) countCanReachAll++;
        }

        int countReachableFromAll = 0;
        for (int j = 0; j < n; j++) {
            boolean reachableFromAll = true;
            for (int i = 0; i < n; i++) {
                if (distances[i][j] == Float.POSITIVE_INFINITY) reachableFromAll = false;
            }
            if (reachableFromAll) countReachableFromAll++;
        }

        return new int[]{countCanReachAll, countReachableFromAll};
    }



    public static void printArrays(float[] ... arrays) {
        for (float[] arr : arrays) {
            for (int i = 0; i < arr.length; i++) System.out.format("%05f ", arr[i]);
            System.out.println("");
        }
    }
}
