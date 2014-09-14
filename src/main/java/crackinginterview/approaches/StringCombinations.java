package crackinginterview.approaches;

import org.junit.Test;

import java.util.Collection;

/**
 * Created by pavlop on 2/22/14.
 */
public class StringCombinations {
    public static Collection<String> perform(String str) throws Exception {
        //boolean[] used = new boolean[str.length()];
        StringBuffer buffer = new StringBuffer(str.length());
        int currentPos = 0;
        int startAllowedAt = 0;
        doPermutationsRecursively(str, buffer, startAllowedAt);
        return null;
    }

    private static void doPermutationsRecursively(String str, StringBuffer buffer, int startAllowedAt) {
        if (startAllowedAt == str.length()) return;

        for (int i = startAllowedAt; i < str.length(); i++) {
            buffer.append(str.charAt(i));
            System.out.println(buffer.toString());
            doPermutationsRecursively(str, buffer, i + 1);
            buffer.deleteCharAt(buffer.length() - 1);
        }
    }

    @Test
    public void positive1() throws Exception {
        System.out.print(perform("123"));

    }
}
