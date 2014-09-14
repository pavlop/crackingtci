package leetcode;

import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**

 A message containing letters from A-Z is being encoded to numbers using the following mapping:

 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 Given an encoded message containing digits, determine the total number of ways to decode it.

 For example,
 Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

 The number of ways decoding "12" is 2.

 */
public class DecodeWays {
    Map<Integer,Integer> startAtResults= new HashMap<Integer,Integer>();

    public int numDecodings(String s) {
        if(s.length() == 0) return 0;
        startAtResults.clear();
        return numDecodings(s,0);
    }

    public int numDecodings(String s, int startAt) {
        if (startAt ==  s.length()) return 1;
        if(startAtResults.containsKey(startAt)) return startAtResults.get(startAt);

        int resWithOne = 0;
        int resWithTwo = 0;

        if (s.charAt(startAt) != '0')  {
            resWithOne =  numDecodings(s, startAt + 1);
            if(startAt < s.length() - 1 ) {
                String twoDigits = s.substring(startAt, startAt + 2);
                Integer twoDigitsInt = Integer.parseInt(twoDigits);
                if(twoDigitsInt <= 26) resWithTwo = numDecodings(s, startAt + 2);
            }
        }
        startAtResults.put(startAt, resWithOne + resWithTwo);
        return resWithOne + resWithTwo;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, numDecodings("12"));
        Assert.assertEquals(3, numDecodings("111"));
        Assert.assertEquals(2, numDecodings("411"));
    }

}
