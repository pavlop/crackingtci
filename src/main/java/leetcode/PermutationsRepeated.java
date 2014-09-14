package leetcode;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 *
 *
 */
public class PermutationsRepeated {
    boolean interrupt;

    public List<List<Integer>> permuteUnique(int[] num) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();

        Integer[] newArray = new Integer[num.length];
        for (int i = 0 ; i< newArray.length; i++) {newArray[i] = num[i];}

        while (!interrupt) {
            result.add(new ArrayList(Arrays.asList(newArray)));
            nextPermutation(newArray);
        }
        return result;
    }

    public Integer[] nextPermutation(Integer[] num) {

        int decSeq = num.length - 1;

        while (decSeq >= 0 ) {
            if(decSeq == 0) break;
            if(num[decSeq] > num[decSeq - 1]) break;
            decSeq--;
        }

        if (decSeq == 0) {
            interrupt = true;
            //Arrays.sort(num);
            return num;
        }

        int firstBigger = num.length - 1;
        while (num[firstBigger] <= num[decSeq - 1] && firstBigger >= decSeq) firstBigger--;

        //swap
        int tmp = num[firstBigger];
        num[firstBigger] = num[decSeq - 1];
        num[decSeq - 1] = tmp;

        //Sort tail
        Arrays.sort(num, decSeq, num.length);
        return num;

    }

    @Test
    public void test() {
        Assert.assertEquals("[[1, 2], [2, 1]]", "" + permuteUnique(new int[]{1,2}));
    }
}
