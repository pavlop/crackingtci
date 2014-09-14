package leetcode;

import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 Given an array of non-negative integers, you are initially positioned at the first index of the array.

 Each element in the array represents your maximum jump length at that position.

 Determine if you are able to reach the last index.

 For example:

 A = [2,3,1,1,4], return true.
 A = [3,2,1,0,4], return false.

 */

public class JumpGame {

    public boolean canJump(int[] A) {
        if(A.length <= 1) return true;

        //always jump to maximum distance
        int cur = 0;
        int chargeLeft = A[cur];
        while (chargeLeft > 0 && cur < A.length) {       // 1>0, 0<3
            chargeLeft--;                               //0
            cur ++;                                     //1
            if(cur >= A.length - 1) return true;
            if(chargeLeft < A[cur]) chargeLeft = A[cur];
        }
        return false;
    }

//    public boolean canReachTheEnd(int[] A, int startAt) {
//        if (resultsMap.containsKey(startAt)) return resultsMap.get(startAt);
//        if (startAt == A.length - 1) {
//            resultsMap.put(startAt, true);
//            return true;
//        }
//        if (startAt >  A.length - 1) {
//            resultsMap.put(startAt, false);
//            return false;
//        }
//
//        int possibleJumps = A[startAt];
//        if (possibleJumps == 0) return false;
//        boolean reachedTheEnd = false;
//
//        for (int i = 1; i <= possibleJumps && !reachedTheEnd; i++) {
//            reachedTheEnd = canReachTheEnd (A, startAt + i);
//        }
//
//        resultsMap.put(startAt, reachedTheEnd);
//        return reachedTheEnd;
//    }

    @Test
    public void test() {
        Assert.assertTrue(canJump(new  int[]{2,3,1,1,4}));
        Assert.assertFalse(canJump(new int[]{3, 2, 1, 0, 4}));
        canJump(new int[]{2,0,6,9,8,4,5,0,8,9,1,2,9,6,8,8,0,6,3,1,2,2,1,2,6,5,3,1,2,2,6,4,2,4,3,0,0,0,3,8,2,4,0,1,2,0,1,4,6,5,8,0,7,9,3,4,6,6,5,8,9,3,4,3,7,0,4,9,0,9,8,4,3,0,7,7,1,9,1,9,4,9,0,1,9,5,7,7,1,5,8,2,8,2,6,8,2,2,7,5,1,7,9,6});
    }

}
