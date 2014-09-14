package dynamicProgramming;

import java.util.Arrays;

/**
 * Created by pavlop on 3/30/14.
 */
public class MyArrayUtils
{
    public static int getMaxElement(int ... arr){
        Arrays.sort(arr);
        return arr[arr.length-1];
    }

    public static int getMinElement(int ... arr){
        Arrays.sort(arr);
        return arr[0];
    }

    public static int getSum(int ... arr){
        int sum = 0;
        for (int i:arr) {
            sum+=i;
        }
        return sum;
    }

}
