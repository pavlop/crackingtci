package leetcode;

/**
 *Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

 For example,
 Given:
 s1 = "aabcc",
 s2 = "dbbca",

 When s3 = "aadbbcbcac", return true.
 When s3 = "aadbbbaccc", return false.
 *
 *
 */
public class InterleavingString {
    boolean[][] visited;

    public boolean isInterleave(String s1, String s2, String s3) {
        if(s3.length() != s1.length() + s2.length()) return false;
        visited = new boolean[s1.length()+1][s2.length()+1];
        return isInterleaveRec(s1, 0, s2, 0, s3, 0);
    }

    public boolean isInterleaveRec(String s1, int s1Start, String s2, int s2Start, String s3, int s3Start) {
        if(s3Start == s3.length()) return true;
        if(visited[s1Start][s2Start]) return false;
        visited[s1Start][s2Start] = true;

        boolean res = false;
        if (s1Start < s1.length() && s3.charAt(s3Start) == s1.charAt(s1Start))
            res = res || isInterleaveRec(s1, s1Start+1, s2, s2Start, s3, s3Start+1);
        if (s2Start < s2.length() && s3.charAt(s3Start) == s2.charAt(s2Start))
            res = res || isInterleaveRec(s1, s1Start, s2, s2Start+1, s3, s3Start+1);
        return res;
    }
}
