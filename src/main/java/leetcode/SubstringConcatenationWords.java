package leetcode;
import org.junit.Test;

import java.util.*;
/**

 You are given a string, S, and a list of words, L, that are all of the same length. Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once and without any intervening characters.

 For example, given:
 S: "barfoothefoobarman"
 L: ["foo", "bar"]

 You should return the indices: [0,9].
 (order does not matter).

 *
 */
public class SubstringConcatenationWords {
    public List<Integer> findSubstring(String S, String[] L) {
        List<Integer> res = new ArrayList<Integer> ();
        if  (L.length == 0) return res;
        int wordLen = L[0].length();

        Map<String, Integer> wordsEtalon = countWords(L);

        for (int startPoint = 0; startPoint <= S.length() - wordLen*L.length; startPoint++) {
            Map<String, Integer> words = new HashMap<String, Integer>(wordsEtalon);
            boolean curPointIsOk = true;
            int wordsFound = 0;
            for(int cursor = startPoint ; wordsFound < L.length && cursor <= S.length() - wordLen; cursor +=wordLen) {
                String curWord = S.substring(cursor, cursor + wordLen);
                if (getWordCountAndDeduct(curWord, words) > 0) {
                    wordsFound++;
                    continue;
                }
                else {
                    curPointIsOk = false;
                    break;
                }
            }
            if(curPointIsOk) res.add(startPoint);
        }
        return res;
    }

    public static Map<String, Integer> countWords(String[] words) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(String s:words) {
            if(!map.containsKey(s)) map.put(s,1);
            else map.put(s, map.get(s)+1);
        }
        return map;
    }

    public static int getWordCountAndDeduct(String s, Map<String, Integer> map) {
        if (map.containsKey(s)) {
            int res = map.get(s);
            if (res > 0) map.put(s,res-1);
            return res;
        } else {
            return 0;
        }
    }

    @Test
    public void doTest() {
        String s = "barfoothefoobarman";
        String[] words = new String[] {"foo","bar"};
        System.out.println(findSubstring(s, words));

        s = "a";
        words = new String[] {"a"};
        System.out.println(findSubstring(s, words));
    }
}
