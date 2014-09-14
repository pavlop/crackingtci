package dynamicProgramming;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertSame;

/**
 * Created by pavlop on 3/26/14.
 */


/*
Написать рекуррентную формулу для подсчета количества различных укладок плитками размера 1х2
коридора размера 2 х N.
Например, при N = 2 существует два варианта таких укладок:
|1 |1 |
|2 |2 |
|1 |2 |
|1 |2 |
 */
public class BarLaying {
    Map<Integer,Long> counts = new HashMap<Integer,Long>();
    int[] countsAr = new int[100];
    int takenFromCache = 0;
    int recursiveCalls = 0;

    public int doParLayingCounter(int N) {
        recursiveCalls++;
        //if (N<1) return 0;
        if(N==1)  return 1;
        if(N==2) return 2;
        Integer result;
        result = (doParLayingCounter(N-1)+doParLayingCounter(N-2));
        return result;


    }

    public long doParLayingCounterInPlaceOprimized(int N) {
        recursiveCalls++;
        //System.out.println("optimized for "+N);
        //if (N<1) return 0;
        if(N==1) {
            counts.put(1, 1L);
            return 1;
        }
        if(N==2) {
            counts.put(2, 2L);
            return 2;
        }
        Long result;
        if (counts.containsKey(N)) {
            takenFromCache++;
            result = counts.get(N);
        } else {
            long result1 = doParLayingCounterInPlaceOprimized(N - 1);
            long result2 = doParLayingCounterInPlaceOprimized(N - 2);
            result = result1+result2;
            counts.put(N, result);
        }
        return result;
    }



    @Test
    public void testNormal() {
        System.out.println("");
        recursiveCalls = 0;
        assertSame(2, doParLayingCounter(2));
        assertSame(3, doParLayingCounter(3));
        assertSame(5, doParLayingCounter(4));

        long start = System.currentTimeMillis();
        System.out.println("NORMAL"+(doParLayingCounter(41)));
        System.out.println("doParLayingCounter time:" + (System.currentTimeMillis() - start));
        System.out.println("recursive calls "+recursiveCalls);
    }
      
    @Test
    public void testInPlaceOptimized() {
        System.out.println("");

        recursiveCalls = 0;
        long    start = System.currentTimeMillis();
        System.out.println("OPTIMIZED:"+doParLayingCounterInPlaceOprimized(100));
        System.out.println("ddoParLayingCounterOptimized time:" + (System.currentTimeMillis() - start));
        System.out.println("counts"+counts);
        System.out.println("Taken form cache"+ takenFromCache);
        System.out.println("recursive calls "+recursiveCalls);
    }

}
