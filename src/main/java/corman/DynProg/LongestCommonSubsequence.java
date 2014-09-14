package corman.DynProg;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *
 *
 */
public class LongestCommonSubsequence {
    char[] seq1, seq2;
    int lengthsMatrix[][];
    char directionsMatrix[][];

    public String getLongestCommonSubequence(String s1, String s2) {
        System.out.println("s1=" + s1 + " s2=" + s2);
        seq1 = s1.toCharArray();
        seq2 = s2.toCharArray();
        lengthsMatrix = new int[s1.length()][s2.length()];
        directionsMatrix = new char [s1.length()][s2.length()];


        if(seq1[0] == seq2[0]) {lengthsMatrix[0][0] = 1;}

        for (int i = 1; i < seq1.length; i++) {
            if(seq1[i] == seq2[0]) {
                lengthsMatrix[i][0] = 1;
            } else if(lengthsMatrix[i-1][0] > 0) {
                lengthsMatrix[i][0] = lengthsMatrix[i-1][0];
                directionsMatrix[i][0] = '^';
            }
        }

        for (int j = 1; j < seq2.length; j++) {
            if(seq1[0] == seq2[j]) {
                lengthsMatrix[0][j] = 1;
            } else if (lengthsMatrix[j-1][0] > 0) {
                lengthsMatrix[0][j] = lengthsMatrix[j-1][0];
                directionsMatrix[0][j] = '<';
            }
        }

        for (int i = 1; i < seq1.length; i++) {
            for(int j = 1; j < seq2.length; j++) {
                if(seq1[i] == seq2[j]) {
                    lengthsMatrix[i][j] = lengthsMatrix[i - 1][j - 1] + 1;
                    directionsMatrix[i][j] = '\\';
                }
                else if (lengthsMatrix[i-1][j] > lengthsMatrix[i][j-1]) {
                    lengthsMatrix[i][j] = lengthsMatrix[i-1][j];
                    directionsMatrix[i][j] = '^';
                }
                else {
                    lengthsMatrix[i][j] = lengthsMatrix[i][j - 1];
                    directionsMatrix[i][j] = '<';
                }
            }
        }

        printMatrix(lengthsMatrix);
        printMatrix(directionsMatrix);

        StringBuilder s = new StringBuilder();
        constructSolutionRec(seq1.length-1, seq2.length-1, s);
        return s.reverse().toString();
    }

    void constructSolutionRec(int i, int j, StringBuilder curres) {
        if (i<0 || j<0) {
            return;
        }

        //System.out.println("i="+i+" seq1="+seq1[i]);
        //System.out.println("j="+j+" seq2="+seq2[j]);

        if(seq1[i] == seq2[j])  curres.append(seq1[i]);

        if('\\' == directionsMatrix[i][j]) {
            constructSolutionRec(i - 1, j - 1, curres);
        } else if ('^' == directionsMatrix[i][j]) {
            constructSolutionRec(i-1, j, curres);
        } else {
            constructSolutionRec(i, j - 1, curres);
        }
    }

    private static void printMatrix(int[][] m) {
        for (int[] x :m) {
            System.out.println(Arrays.toString(x));
        }
    }

    private static void printMatrix(char[][] m) {
        for (char[] x :m) {
            System.out.println(Arrays.toString(x));
        }
    }

    @Test
    public void test() {
        Assert.assertEquals("ace", getLongestCommonSubequence("abcde", "ace"));
        Assert.assertEquals("GTCGTCGGAAGCCGGCCGAA", getLongestCommonSubequence("ACCGGTCGAGTGCGCGGAAGCCGGCCGAA", "GTCGTTCGGAATGCCGTTGCTCTGTAAA"));
    }
}
