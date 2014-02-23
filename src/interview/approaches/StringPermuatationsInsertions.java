package interview.approaches;

import org.junit.Test;

import java.util.*;

/**
 * Created by pavlop on 2/16/14.
 */
public class StringPermuatationsInsertions {
    public static Collection<String> perform(String str) throws Exception {
        /*
        1234    2134    ...
        1243    2143
        1324    2314
        1342    2341
        1423    2413
        1432    2431
         */
        Set<String> result = new HashSet<String>();
        doPermutationsRecursively(str, 0, result);
        for (Iterator<String> it = result.iterator(); it.hasNext(); ) {
            if (it.next().length() != str.length()) {
                it.remove();
            }
        }
        return result;

    }

    private static void doPermutationsRecursively(String original, int addingChar, Collection<String> result) {
        if (addingChar == 0) {
            result.add(original.charAt(0) + "");
        } else {
            char newLetter = original.charAt(addingChar);
            Collection<String> newPermutations = new ArrayList<String>();
            for (String s : result) {
                for (int i = 0; i < s.length() + 1; i++) {
                    newPermutations.add(s.substring(0, i) + newLetter + s.substring(i, s.length()));
                }
            }
            result.addAll(newPermutations);
        }
        addingChar++;
        if (addingChar < original.length()) doPermutationsRecursively(original, addingChar, result);
    }

    @Test
    public void positive1() throws Exception {
        System.out.print(perform("12"));
        System.out.print(perform("123"));
        System.out.print(perform("1234"));
    }
}
