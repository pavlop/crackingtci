package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 The count-and-say sequence is the sequence of integers beginning as follows:
 1, 11, 21, 1211, 111221, ...

 1 is read off as "one 1" or 11.
 11 is read off as "two 1s" or 21.
 21 is read off as "one 2, then one 1" or 1211.
 Given an integer n, generate the nth sequence.

 Note: The sequence of integers will be represented as a string.
 */
public class CountAndSay {
    public String countAndSay(int n) {
        String s = "1";
        for (int i = 1; i < n; i++)
            s = countAndSayNext(s);
        return s;
    }

    public String countAndSayNext(String s) {
        StringBuilder res = new StringBuilder();
        int i = 0;
        while ( i < s.length()) {
            int count = 0;
            char c = s.charAt(i);
            while ( i < s.length() && s.charAt(i) == c) {
                count++;
                i++;
            }
            res.append(count).append(c);
        }
        return res.toString();
    }

    @Test
    public void test() {
       Assert.assertEquals("312211", countAndSayNext("111221"));
       Assert.assertEquals("312211", countAndSay(6));
    }
}
