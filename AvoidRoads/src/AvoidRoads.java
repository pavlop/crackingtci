import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class AvoidRoads {
	long[][] field;

	public long numWays(int width, int height, String[] bad) {


		field = new long[width*2+1][height*2+1];
        for (String badPoint:bad) {
            String[] points = badPoint.split(" ");
            int a0 = Integer.parseInt(points[0]);
            int a1 = Integer.parseInt(points[1]);

            int b0 = Integer.parseInt(points[2]);
            int b1 = Integer.parseInt(points[3]);

            System.out.println("a0="+a0+" a1="+a1+" b0="+b0+" b1="+b1);

            //mark bad point in the field
            field[2*a0 + (b0-a0)][2*a1 + (b1-a1)] = -1;
        }

        field[0][0] = 1;
        for (int i = 2 ; i < field.length; i+=2) if(canMoveFromUp(i, 0)) field[i][0] = field[i-2][0];
        for (int i = 2 ; i < field[0].length; i+=2) if(canMoveFromLeft(0, i)) field[0][i] = field[0][i-2];

//        printArrays(field);
        System.out.println("----------------------");



        for (int i = 2; i < field.length; i += 2)
            for (int j = 2; j < field[0].length; j += 2) {
                if (canMoveFromLeft(i, j)) field[i][j] +=  field[i][j-2];
                if (canMoveFromUp(i, j)) field[i][j] +=  field[i-2][j];
            }

//        printArrays(field);
        return field[field.length-1][field[0].length-1];
	}

    public void printArrays(long[] ... arrays) {

        for (long[] arr : arrays) {
            for (int i = 0; i < arr.length; i++) System.out.format("%05d ", arr[i]);
            System.out.println("");
        }
    }

    public boolean canMoveFromLeft(int i, int j) {
        if (j > 0 && field[i][j-1] >= 0) return true;
        return false;
    }
    public boolean canMoveFromUp(int i, int j) {
        if (i > 0 && field[i-1][j] >= 0) return true;
        return false;
    }

}
