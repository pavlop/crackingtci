package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**

 Given a string S and a string T, count the number of distinct subsequences of T in S.

 A subsequence of a string is a new string which is formed from the original string by deleting some
 (can be none) of the characters without disturbing the relative positions of the remaining characters.

 (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

 Here is an example:
 S = "rabbbit", T = "rabbit"

 rabb#it
 rab#bit
 ra#bbit

 Return 3.



 Let W(i, j) stand for the number of subsequences of S(0 ... i) in T(0 ... j).
 if (S.charAt(i) == T.charAt(j))
    W(0...i, 0...j) = W(0...i-1, j-1) + W(0...i-1, 0...j);

 Otherwise:
    W(0...i, 0...j) = W(0...i-1, 0...j).

 */
public class DistinctSubsequencesBottomUp {

    public int numDistinct(String S, String T) {
        if(S.length() == 0) return 0;

        int[][] sourceAndTargetEnds = new int[S.length()][T.length()];

        for(int i = 0; i < S.length(); i++) {
            for (int j = 0; j < T.length(); j++) {
                if (S.charAt(i) == T.charAt(j)) {
                    sourceAndTargetEnds[i][j] += getSmartElement(sourceAndTargetEnds, i-1, j) + getSmartElement(sourceAndTargetEnds, i - 1, j - 1);
                } else {
                    sourceAndTargetEnds[i][j] += getSmartElement(sourceAndTargetEnds, i-1, j);
                }
            }
        }
        return sourceAndTargetEnds[S.length()-1][T.length()-1];
    }


    private int getSmartElement(int[][] arr , int i, int j) {
        // J < 0 means that there are no elements in target
        if (j < 0) return 1;

        // i < 0 means that there are no elements in source
        if (i < 0 ) return 0;

        else return arr[i][j];
    }


    @Test
    public void test() {
        Assert.assertEquals(1, numDistinct("A", "A"));
        Assert.assertEquals(1, numDistinct("AB", "A"));
        Assert.assertEquals(2, numDistinct("AA", "A"));
        Assert.assertEquals(3, numDistinct("AAA", "AA"));
        Assert.assertEquals(3, numDistinct("ABBBC", "AB"));
        Assert.assertEquals(3, numDistinct("rabbbit", "rabbit"));
        Assert.assertEquals(1, numDistinct("1234567890", "1"));
        Assert.assertEquals(1, numDistinct("1234567890", "1234567890"));

        System.out.println("10 and 2:"+numDistinct("1111111111", "11"));
        System.out.println("40 and 10:"+numDistinct("1111111111111111111111111111111111111111", "1111111111"));
        System.out.println("TimeLimit and 1:"+numDistinct("adbdadeecadeadeccaeaabdabdbcdabddddabcaaadbabaaedeeddeaeebcdeabcaaaeeaeeabcddcebddebeebedaecccbdcbcedbdaeaedcdebeecdaaedaacadbdccabddaddacdddc", "bcddceeeebecbc"));
        System.out.println("TimeLimit and 1:"+numDistinct("xslledayhxhadmctrliaxqpokyezcfhzaskeykchkmhpyjipxtsuljkwkovmvelvwxzwieeuqnjozrfwmzsylcwvsthnxujvrkszqwtglewkycikdaiocglwzukwovsghkhyidevhbgffoqkpabthmqihcfxxzdejletqjoxmwftlxfcxgxgvpperwbqvhxgsbbkmphyomtbjzdjhcrcsggleiczpbfjcgtpycpmrjnckslrwduqlccqmgrdhxolfjafmsrfdghnatexyanldrdpxvvgujsztuffoymrfteholgonuaqndinadtumnuhkboyzaqguwqijwxxszngextfcozpetyownmyneehdwqmtpjloztswmzzdzqhuoxrblppqvyvsqhnhryvqsqogpnlqfulurexdtovqpqkfxxnqykgscxaskmksivoazlducanrqxynxlgvwonalpsyddqmaemcrrwvrjmjjnygyebwtqxehrclwsxzylbqexnxjcgspeynlbmetlkacnnbhmaizbadynajpibepbuacggxrqavfnwpcwxbzxfymhjcslghmajrirqzjqxpgtgisfjreqrqabssobbadmtmdknmakdigjqyqcruujlwmfoagrckdwyiglviyyrekjealvvigiesnvuumxgsveadrxlpwetioxibtdjblowblqvzpbrmhupyrdophjxvhgzclidzybajuxllacyhyphssvhcffxonysahvzhzbttyeeyiefhunbokiqrpqfcoxdxvefugapeevdoakxwzykmhbdytjbhigffkmbqmqxsoaiomgmmgwapzdosorcxxhejvgajyzdmzlcntqbapbpofdjtulstuzdrffafedufqwsknumcxbschdybosxkrabyfdejgyozwillcxpcaiehlelczioskqtptzaczobvyojdlyflilvwqgyrqmjaeepydrcchfyftjighntqzoo", "rwmimatmhydhbujebqehjprrwfkoebcxxqfktayaaeheys"));
    }
}
