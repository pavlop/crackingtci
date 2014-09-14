package crackinginterview.approaches;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by pavlop on 2/16/14.
 */
public class NoteFromMagazine {
    public static boolean perform(String note, String magazine) throws Exception {

        if (note == null || note.length() == 0) return true;
        if (magazine == null || magazine.length() == 0) return false;

        String[] noteWords = note.split(" ");
        StringTokenizer magazineTokenizer = new StringTokenizer(magazine, " ");

        Map<String, Integer> magWordsCounts = new HashMap<String, Integer>();
        while (magazineTokenizer.hasMoreElements()) {
            String magWord = magazineTokenizer.nextToken();
            if (!magWordsCounts.containsKey(magWord)) {
                magWordsCounts.put(magWord, 1);
            } else {
                magWordsCounts.put(magWord, magWordsCounts.get(magWord) + 1);
            }
        }

        for (String noteWord : noteWords) {
            if (magWordsCounts.containsKey(noteWord) && magWordsCounts.get(noteWord) > 0) {
                magWordsCounts.put(noteWord, magWordsCounts.get(noteWord) - 1);
            } else {
                return false;
            }
        }
        return true;
    }


    @Test
    public void positive1() throws Exception {
        assertTrue(perform("I robot", "I am not a robot"));
        assertTrue(perform("I robot robot", "I robot am not a robot"));
        assertTrue(!perform("I robot robot", ""));
        assertTrue(perform("", "I robot am not a robot"));
        assertTrue(!perform("I robot I", "I am not a robot"));
        assertTrue(!perform("robot robot", "I am not a robot"));
        assertTrue(perform(null, "I am not a robot"));
        assertTrue(!perform("Hi", null));
    }

}
