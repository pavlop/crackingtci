package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

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

 */
public class DistinctSubsequencesTopDownSLOW {
    int[][] startSourceAtTargeAt;
    int calls;

    public int numDistinct(String S, String T) {
        calls = 0;
        startSourceAtTargeAt = new int[S.length()][T.length()];
        for (int i = 0; i < S.length(); i++) {
            for (int j = 0; j < T.length(); j++) {
                startSourceAtTargeAt[i][j] = -1;
            }
        }
        int res= numDistinctSubsequencesRec(S.toCharArray(), 0, T.toCharArray(), 0);
        //System.out.println("calls="+calls);
        //System.out.println("startSourceAtTargeAt:"+ Arrays.deepToString(startSourceAtTargeAt));

        return res;
    }

    public int numDistinctSubsequencesRec(char[] source, int sourceStartAt, char[] target, int targetStartAt) {
        calls++;
        //solution is found. No elements left in [target]
        if(targetStartAt == target.length) return 1;

        //solution is not found. No elements left in [source]
        if(sourceStartAt == source.length) return 0;

        //read cached value if possible
        if(startSourceAtTargeAt[sourceStartAt][targetStartAt] > 0) return startSourceAtTargeAt[sourceStartAt][targetStartAt];

        int sumInclude = 0;
        int sumSkip = 0;
        if(source[sourceStartAt] == target[targetStartAt]) {
            //add cur char
            sumInclude = numDistinctSubsequencesRec(source, sourceStartAt + 1, target, targetStartAt + 1);
            //skip cur char
            sumSkip = numDistinctSubsequencesRec(source, sourceStartAt + 1, target, targetStartAt);
        } else {
            sumSkip = numDistinctSubsequencesRec(source, sourceStartAt + 1, target, targetStartAt);
        }

        //cache the result
        startSourceAtTargeAt[sourceStartAt][targetStartAt] = sumInclude+sumSkip;
        return startSourceAtTargeAt[sourceStartAt][targetStartAt];
    }

    @Test
    public void test() {
        Assert.assertEquals(1, numDistinct("A", "A"));
        Assert.assertEquals(1, numDistinct("AB", "A"));
        Assert.assertEquals(2, numDistinct("AA", "A"));
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
