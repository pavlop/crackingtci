package crackinginterview.x8.dynamic;

/**
 * Created by pavlop on 5/4/14.
 */
public class RobotPaths {

    public static int calculatePaths(int sizeX, int sizeY) {
        if (sizeX == 1) return 1;
        if (sizeY == 1) return 1;
        return calculatePaths(sizeX-1, sizeY)+calculatePaths(sizeX, sizeY-1);
    }

    public static int calculatePathsMemorized(int sizeX, int sizeY) {
        return calculatePathsMemorized(new int[sizeX][sizeY], sizeX-1,sizeY-1);
    }

    public static int calculatePathsMemorized(int[][] previousRes, int posX, int posY) {
        if (previousRes[posX][posY] != 0) return previousRes[posX][posY];
        if (posX == 0) return 1;
        if (posY == 0) return 1;
        previousRes[posX][posY] = calculatePathsMemorized(previousRes, posX-1, posY) +
                calculatePathsMemorized(previousRes, posX, posY-1);
        return previousRes[posX][posY];
    }

    public static void main (String s[]) {
        System.out.println("calculatePaths:"+calculatePaths(2,2));
        System.out.println("calculatePaths:"+calculatePaths(3,2));

        System.out.println("calculatePathsMemorized:"+calculatePathsMemorized(2,2));
        System.out.println("calculatePathsMemorized:"+calculatePathsMemorized(3,2));

    }
}
