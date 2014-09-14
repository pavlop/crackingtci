package leetcode;

import java.util.*;
import org.junit.Test;

/**
 *
 *
 *
 */
public class Permutations {
    public static List<List<Integer>> permute(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        LinkedHashSet<Integer> remain = new LinkedHashSet<Integer>();
        for (Integer i:num) {
            remain.add(i);
        }
        List<Integer> buff = new ArrayList(num.length);
        permute(buff, remain, result);
        return result;
    }

    public static void permute(List<Integer> buff, LinkedHashSet<Integer> remain, List<List<Integer>> result) {
        if (remain.size() == 0) {
            //result.add(buff);
            return;
        }

        for(Integer i: remain) {
            List<Integer> newBuff = new ArrayList<Integer>(buff);
            newBuff.add(i);
            LinkedHashSet<Integer> newRemain = new LinkedHashSet<Integer> (remain);
            newRemain.remove(i);
            permute(newBuff, newRemain, result);
        }
    }


    @Test
    public void positive1() throws Exception {

        long start = System.nanoTime();
        System.out.print(permute(new int[]{1,2,3,4,5,6,7,8,9,10,11}));
        System.out.println((System.nanoTime() - start)/1000000.0);
    }
}
