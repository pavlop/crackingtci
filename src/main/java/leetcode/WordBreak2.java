package leetcode;

import java.util.*;

/**
 *
 *
 *
 */
public class WordBreak2 {
    List<String> result = new ArrayList<String>();
    public List<String> wordBreak(String s, Set<String> dict) {
        result.clear();
        wordBreak(s, dict, s.length(), new StringBuilder());
        return result;
    }

    public void wordBreak(String s, Set<String> dict, int lenLeft, StringBuilder curSentence) {
        if (lenLeft == 0) {
            curSentence.deleteCharAt(curSentence.length() - 1);
            result.add(curSentence.toString());
        }
        for(int endAt = lenLeft -1 ; endAt >= 0; endAt --) {
            String sbstr = s.substring(endAt, lenLeft);
            if (dict.contains(sbstr)) {
                StringBuilder curSentenceNew = new  StringBuilder(curSentence);
                curSentenceNew.insert(0, sbstr+" ");
                wordBreak(s, dict, endAt, curSentenceNew);
            }
        }
    }
}
