package corman.DynProg;

import com.sun.org.apache.xpath.internal.SourceTree;
import junit.framework.Assert;
import org.junit.Test;

import java.util.*;

/**
 Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

 For example,
 S = "ADOBECODEBANC"
 T = "ABC"
 Minimum window is "BANC".

 Note:
 If there is no such window in S that covers all characters in T, return the emtpy string "".
 If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstringDynamicProgrammingBAD {
    Map<String, Integer> memorizationCalls = new LinkedHashMap<String, Integer>(50000);
    int memoreztions = 0;
    int totalCalls = 0;


    public String minWindow(String S, String T) {
        memorizationCalls.clear();
        char[] t = T.toCharArray();
        int bestStartAt = 0;
        int bestLen = Integer.MAX_VALUE;

        Map<Character, Integer> charsCounts = new LinkedHashMap<Character, Integer> ();
        for (Character c:t) increaseCounter(charsCounts, c);

        for (int startAt = 0; startAt < S.length(); startAt++) {
            if(charsCounts.containsKey(S.charAt(startAt))) {
                int finishedAt = findWhereSubstrFinishes(charsCounts, S, startAt);
                int curLen = finishedAt - startAt + 1;
                if (curLen < bestLen) {
                    bestLen = curLen;
                    bestStartAt = startAt;
                }
            }
        }
        if(bestLen < 0 || bestLen == Integer.MAX_VALUE) return "";
        return S.substring(bestStartAt, bestStartAt+bestLen);
    }

    public int findWhereSubstrFinishes(Map<Character, Integer> chars, String S, int startAt) {
        String key = chars+"_"+startAt;
        totalCalls++;
        //System.out.println("key"+key);
        if(memorizationCalls.containsKey(key)) {
            memoreztions++;
            return memorizationCalls.get(key);
        }


        int bestFinishedAt = Integer.MAX_VALUE;
        if(startAt == S.length()) return bestFinishedAt;

        for (int i = startAt; i < S.length(); i++) {
            Character curChar = S.charAt(i);
            if(chars.containsKey(curChar)) {
                Map<Character, Integer> newChars = new HashMap<Character, Integer>(chars);
                decreaseCounter(newChars, curChar);
                if(newChars.isEmpty()) {
                    bestFinishedAt = i;
                    break;
                }
                int finishedAt = findWhereSubstrFinishes(newChars, S, i+1);
                bestFinishedAt = Math.min(bestFinishedAt, finishedAt);
            }
        }
        memorizationCalls.put(key, bestFinishedAt);
        return bestFinishedAt;
    }

    public static void increaseCounter(Map<Character, Integer> map, Character o) {
        if (map.containsKey(o)) {
            map.put(o, map.get(o)+1);
        } else {
            map.put(o, 1);
        }
    }

    public static void decreaseCounter(Map<Character, Integer> map, Character o) {
        int count = map.get(o);
        if (count<1) return;
        if (count == 1) {
            map.remove(o);
        } else {
            map.put(o, count - 1);
        }
    }


    @Test
    public void test() {
        Assert.assertEquals("AC", minWindow("AC","AC"));
        Assert.assertEquals("AXC", minWindow("AXC","AC"));
        Assert.assertEquals("AXC", minWindow("XAXCX","AC"));
        Assert.assertEquals("AXC", minWindow("CXXAXCX","AC"));
        Assert.assertEquals("BANC", minWindow("ADOBECODEBANC","ABC"));
        Assert.assertEquals("", minWindow("XXXXXX","A"));
        Assert.assertEquals("", minWindow("A","AB"));
        Assert.assertEquals("", minWindow("ba","A"));
        Assert.assertEquals("AXAA", minWindow("XXXXAXXXXXXAXXAXXXXXXXAXAAXXX","AAA"));

        minWindow("ask_not_what_your_country_can_do_for_you_ask_what_you_can_do_for_your_country", "ask_country");
        System.out.println("memoreztions="+memoreztions);
        System.out.println("totalCalls="+totalCalls);
        System.out.println("memorizationCalls="+memorizationCalls.size());
        //System.out.println(memorizationCalls);
    }

}

