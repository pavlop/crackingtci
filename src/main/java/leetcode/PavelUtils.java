package leetcode;

/**
 *
 *
 *
 */
public class PavelUtils {

    public static int getMaxValue(int ... x) {
        if (x.length == 0) return Integer.MIN_VALUE;
        int max = x[0];
        for (int curId = 1; curId < x.length; curId++)
            if (x[curId] > max) max = x[curId];
        return max;
    }

    public static int getMaxId(int ... x) {
        if (x.length == 0) return Integer.MIN_VALUE;
        int maxId =0;
        for (int curId = 1; curId < x.length; curId++)
            if (x[curId] > x[maxId]) maxId = curId;
        return maxId;
    }

    public static int getMinValue(int ... x) {
        if (x.length == 0) return Integer.MIN_VALUE;
        int min = x[0];
        for (int curId = 1; curId < x.length; curId++)
            if (x[curId] < min)   min = x[curId];
        return min;
    }

    public static int getMinId(int ... x) {
        if (x.length == 0) return Integer.MIN_VALUE;
        int minId =0;
        for (int curId = 1; curId < x.length; curId++)
            if (x[curId] < x[minId]) minId = curId;
        return minId;
    }

    public static int sum(int ... x) {
        int sum = 0;
        for (int i : x)  sum += i;
        return sum;
    }

}
