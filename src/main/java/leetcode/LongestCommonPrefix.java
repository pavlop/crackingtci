package leetcode;

/**
 *
 *
 *
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";
        StringBuilder longestPrefix = new StringBuilder();
        String str1 = strs[0];
        boolean common = true;
        int str1Id = 0;
        while( str1Id < str1.length() && common) {
            for (String str : strs) {
                if (str.length() <= str1Id || str.charAt(str1Id) != str1.charAt(str1Id)) {
                    common = false;
                }
            }
            if(common) str1Id++;
        }
        if (str1Id == 0 && common==false) return "";
        return str1.substring(0, str1Id);
    }
}
