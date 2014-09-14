package crackinginterview.approaches;

import org.junit.Test;

import java.util.Collection;

/**
 * Created by pavlop on 2/22/14.
 */
public class StringPermutationsRecursive {
    public static Collection<String> perform(String str) throws Exception {
        boolean[] used = new boolean[str.length()];
        doPermutationsRecursively(str, new StringBuilder(), used, 0);
        return null;
    }

    private static void doPermutationsRecursively(String str, StringBuilder buffer, boolean[] used, int position) {
        if (position == str.length()) {
            System.out.println("buff=" + buffer.toString());
            return;
        }

        for (int i = 0; i < used.length; i++) {
            if (used[i] == false) {
                buffer.append(str.charAt(i));
                used[i] = true;
                doPermutationsRecursively(str, buffer, used, position + 1);
                used[i] = false;
                buffer.deleteCharAt(buffer.length() - 1);
            }
        }
    }

    @Test
    public void positive1() throws Exception {
        System.out.print(perform("123"));
    }
}
