package leetcode;

import java.util.Set;

/**
 Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

 For example, given
 s = "leetcode",
 dict = ["leet", "code"].

 Return true because "leetcode" can be segmented as "leet code".

 */
public class WordBreak {
        public boolean wordBreak(String s, Set<String> dict) {
            return wordBreak(s, dict, s.length());
        }

        public boolean wordBreak(String s, Set<String> dict, int len) {
            if (len == 0) return true;
            boolean solFound = false;
            for(int endAt = len -1 ; endAt >= 0 && !solFound; endAt --) {
                String sbstr = s.substring(endAt, len);
                if (dict.contains(sbstr)) solFound = wordBreak(s, dict, endAt);
            }
            return solFound;
    }
}
