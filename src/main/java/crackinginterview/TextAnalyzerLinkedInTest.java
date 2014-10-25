package crackinginterview;

import junit.framework.Assert;
import org.junit.Test;

class TextAnalyzerLinkedIn {

    String[] words;

    public TextAnalyzerLinkedIn(String text) {
        this.words = text.split(" ");
    }

    public int getMinDistanceBetweenWords(String w1, String w2) {
        int lastSeenW1 = -1;
        int lastSeenW2 = -1;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.equals(w1)) lastSeenW1 = i;
            if (word.equals(w2)) lastSeenW2 = i;
            res = Math.min(getDistance(lastSeenW1, lastSeenW2), res);
        }
        return res;
    }

    public int getDistance(int lastSeenW1, int lastSeenW2) {
        if (lastSeenW1 < 0 || lastSeenW2 < 0) return Integer.MAX_VALUE;
        else return Math.abs(lastSeenW2 - lastSeenW1);
    }

}

public class TextAnalyzerLinkedInTest {
    @Test
    public void test() {
        String s = "cat dog foo bar dog dog fuck bar cat";
        TextAnalyzerLinkedIn lextAnalyzer = new TextAnalyzerLinkedIn(s);
        Assert.assertSame(1, lextAnalyzer.getMinDistanceBetweenWords("dog", "foo"));
        Assert.assertSame(1, lextAnalyzer.getMinDistanceBetweenWords("foo", "dog"));
        Assert.assertSame(1, lextAnalyzer.getMinDistanceBetweenWords("foo", "dog"));
        Assert.assertSame(2, lextAnalyzer.getMinDistanceBetweenWords("cat", "fuck"));
    }
}