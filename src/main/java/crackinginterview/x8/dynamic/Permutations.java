package crackinginterview.x8.dynamic;

/**
 * Print all permutations of a string
 * http://www.cs.ucf.edu/~dmarino/ucf/cop3502/lec/Permutations.doc
 *
 cahnge a with a
 cahnge b with b
 cahnge c with c
 abc
 cahnge c with c
 cahnge b with b
 cahnge b with c
 cahnge b with b
 acb
 cahnge b with b
 cahnge b with c
 cahnge a with a
 cahnge a with b
 cahnge a with a
 cahnge c with c
 bac
 cahnge c with c
 cahnge a with a
 cahnge a with c
 cahnge a with a
 bca
 cahnge a with a
 cahnge a with c
 cahnge a with b
 cahnge a with c
 cahnge b with b
 cahnge a with a
 cba
 cahnge a with a
 cahnge b with b
 cahnge b with a
 cahnge b with b
 cab
 cahnge b with b
 cahnge b with a
 cahnge a with c
 */
public class Permutations {

    public static void pecursiveStringPermute(char[] str, int k) {
        int j;
        // Base-case: All fixed, so print str.
        if (k == str.length)
            System.out.println( str);
        else {
            // Try each letter in spot j.
            for (j=k; j<str.length; j++) {
                // Place next letter in spot k.
                exchangeCharacters(str, k, j);

                // Print all with spot k fixed.
                pecursiveStringPermute(str, k + 1);

                // Put the old char back.
                exchangeCharacters(str, j, k);
            }
        }
    }

    private static void exchangeCharacters(char[] str, int j, int k) {
        System.out.println("cahnge "+str[j] + " with "+str[k]);
        char jchar = str[j];
        str[j] = str[k];
        str[k] = jchar;
    }

    public static void main (String args[]) {
        pecursiveStringPermute("abc".toCharArray(), 0);
    }




    /*
Generation in lexicographic order[edit]
There are many ways to systematically generate all permutations of a given sequence.
[31] One classical algorithm, which is both simple and flexible,
is based on finding the next permutation in lexicographic ordering,
if it exists. It can handle repeated values, for which case it generates
the distinct multiset permutations each once.
Even for ordinary permutations it is significantly more efficient
than generating values for the Lehmer code in lexicographic order
(possibly using the factorial number system) and converting those to permutations.
To use it, one starts by sorting the sequence in (weakly)
increasing order (which gives its lexicographically minimal permutation),
and then repeats advancing to the next permutation as long as one is found.
The method goes back to Narayana Pandita in 14th century India,
and has been frequently rediscovered ever since.[32]

The following algorithm generates the next permutation lexicographically
after a given permutation. It changes the given permutation in-place.

Find the largest index k such that a[k] < a[k + 1].
If no such index exists, the permutation is the last permutation.
Find the largest index l such that a[k] < a[l].
Swap the value of a[k] with that of a[l].

Reverse the sequence from a[k + 1] up to and including the final element a[n].
For example, given the sequence [1, 2, 3, 4]
which starts in a weakly increasing order, and given that the index is zero-based,
 the steps are as follows:

Index k = 2, because 3 is placed at an index that satisfies
condition of being the largest index that is still less than a[k + 1] which is 4.

Index l = 3, because 4 is the only value in the sequence that is
greater than 3 in order to satisfy the condition a[k] < a[l].

The values of a[2] and a[3] are swapped to form the new sequence [1,2,4,3].

The sequence after k-index a[2] to the final element is reversed.
Because only one value lies after this index (the 3),
the sequence remains unchanged in this instance.
Thus the lexicographic successor of the initial state is permuted: [1,2,4,3].
Following this algorithm, the next lexicographic permutation will be [1,3,2,4],
and the 24th permutation will be [4,3,2,1] at which point a[k] < a[k + 1]
does not exist, indicating that this is the last permutation.
*/



}
