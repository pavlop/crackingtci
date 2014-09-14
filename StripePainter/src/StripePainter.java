import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class StripePainter {
//    Set<Character> uniqueColors = new HashSet<>();
//    char[] expected;

    public int minStrokes(String stripes) {
        char s[] = stripes.toCharArray();
        int strokes[][] = new int[s.length][s.length];
//        for(int[] d : dp) fill(d,999);

        //initializing D(R), D(G), D(B) ...
        for(int i=0; i<s.length; i++) strokes[i][i] = 1;

        for (int len = 1; len < s.length; len ++) {
            for (int start = 0; start+len < s.length; start ++) {
                int end = start+len;
                int curMinStroke = Integer.MAX_VALUE;
                for (int splitter = start; splitter < end; splitter++) {
                    int curStroke = strokes[start][splitter]+ strokes[splitter+1][end];
                    if (s[start] == s[end]) curStroke--;
                    curMinStroke = Math.min(curMinStroke, curStroke);
                }
                strokes[start][end] = curMinStroke;
            }
//            printArrays(toLongs(strokes));
        }
        return strokes[0][strokes.length-1];
	}

//  public int minStrokes(String stripes) {
//      this.expected = stripes.toCharArray();
//
//        for (char c : expected) uniqueColors.add(c);
//
//        int paintsCount = 0;
//        char[] actual = new char[expected.length];
//        while (true) {
//            Pair<Integer> bound = getBoundersOfBiggestSection(actual);
//            if (bound.left >= 0) {
//                paintsCount++;
//                paintArea(bound, actual);
//            } else {
//                break;
//            }
//        }
//		return paintsCount;
//    }

//    public Pair<Integer> getBoundersOfBiggestSection( char[] actual) {
//        int maxLen = Integer.MIN_VALUE;
//        int bestLeft = Integer.MIN_VALUE;
//        int bestRight = Integer.MIN_VALUE;
//
//        for (char curColor:uniqueColors) {
//            int left = 0;
//            int right = actual.length - 1;
//            while (left < right) {
//                if(expected[left] != curColor || actual[left] == expected[left]) { left++; continue;}
//                if(expected[right] != curColor || actual[right] == expected[right]) { right--;continue;}
//                break;
//            }
//            int curLen = right-left;
//            if(curLen > maxLen && expected[left]!= actual[left]) {
//                bestLeft = left;
//                bestRight = right;
//                maxLen = curLen;
//            }
//        }
//        return new Pair<>(bestLeft, bestRight);
//    }
//
//    public void paintArea(Pair<Integer> bounderies, char[] actual) {
//        for (int i = bounderies.left; i <= bounderies.right; i++ )
//            actual[i] = expected[bounderies.left];
//    }

    public static void fill(int[] d, int val) {
        for(int i = 0; i<d.length; i++) d[i] = val;
    }

    public static long[] toLongs(int ... nums) {
        long[] res = new long[nums.length];
        for (int i = 0; i < nums.length; i++) res[i] = nums[i];
        return res;
    }

    public static long[][] toLongs(int[] ... nums) {
        long[][] res = new long[nums.length][nums[0].length];
        for (int i = 0; i < nums.length; i++) res[i] = toLongs(nums[i]);
        return res;
    }

    public static void printArrays(long[] ... arrays) {
        for (long[] arr : arrays) {
            for (int i = 0; i < arr.length; i++) System.out.format("%05d ", arr[i]);
            System.out.println("");
        }
        System.out.println("");
    }

}

class Pair<T> {
    public T left;
    public T right;

    Pair(T left, T right) {
        this.left = left;
        this.right = right;
    }
}


