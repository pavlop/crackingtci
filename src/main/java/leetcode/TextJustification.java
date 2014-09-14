package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**

 Given an array of words and a length L, format the text such that each line has exactly
 L characters and is fully (left and right) justified.

 You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
 Pad extra spaces ' ' when necessary so that each line has exactly L characters.

 Extra spaces between words should be distributed as evenly as possible.
 If the number of spaces on a line do not divide evenly between words,
 the empty slots on the left will be assigned more spaces than the slots on the right.

 For the last line of text,
 it should be left justified and no extra space is inserted between words.

 For example,
 words: ["This", "is", "an", "example", "of", "text", "justification."]
 L: 16.

 Return the formatted lines as:
 [
 "This    is    an",
 "example  of text",
 "justification.  "
 ]
 Note: Each word is guaranteed not to exceed L in length.

 */
public class TextJustification {

    public List<String> fullJustify(String[] words, int L) {
        List<String> result = new ArrayList<String>();
        List<String> line = new ArrayList<String>();

        int curLineLen = 0;
        int curWordsLen = 0;
        int i = 0;
        boolean isLastWord = false;

        while (i < words.length) {
            if(curLineLen + words[i].length() <= L) {
                isLastWord = (i == words.length - 1);
                line.add(words[i]);
                curWordsLen += words[i].length();
                curLineLen += words[i].length() + 1; //+1 for a space between the words
                i++;
                if(!isLastWord) continue;
            }

//            System.out.println("line="+line);
            // format cur line
            if(line.size() > 1) {

                //How many spes should be between words
                int numOfSpacesNeeded;
                if(isLastWord) numOfSpacesNeeded = 1;
                else numOfSpacesNeeded = (L - curWordsLen) / (line.size() - 1);

                //create list with blank spaces
                List<StringBuilder> blankStringsList = new LinkedList<StringBuilder>();
                int totalLineLength = curWordsLen;
                for (int j = 0; j < line.size() - 1; j++) {
                    blankStringsList.add(generateEmptyString(numOfSpacesNeeded));
                    totalLineLength += numOfSpacesNeeded;
                }

                // if isLastWord - insert only one space, the rest will be appended
                if (!isLastWord) {
                    for (int j = 0; j < blankStringsList.size() && totalLineLength < L; j++) {
                        blankStringsList.get(j).append(" ");
                        totalLineLength++;
                    }
                }

                //build string
                StringBuilder stringWithSpaces = new StringBuilder();
                for (int j = 0; j < blankStringsList.size(); j++) {
                    stringWithSpaces.append(line.get(j));
                    stringWithSpaces.append(blankStringsList.get(j));
                }
                stringWithSpaces.append(line.get(line.size() - 1));

                //if isLastWord - append with spaces at the end
                stringWithSpaces.append(generateEmptyString(L-totalLineLength));

                result.add(stringWithSpaces.toString());
            } else {
                result.add(line.get(0) + generateEmptyString(L - curWordsLen));
            }

            //resset
            line.clear();
            curLineLen = 0;
            curWordsLen = 0;

        }
        return result;
    }

    public StringBuilder generateEmptyString(int len) {
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < len; i++) {
            sb.append(" ");
        }
        return sb;
    }

    @Test
    public void test() {
        String[] inp;

//        String[] inp = new String[]{"This", "is", "an", "justification."};
//        Assert.assertEquals("[This    is    an, justification.  ]", ""+fullJustify(inp, 16));


        inp = new String[]{"This", "is", "an", "example", "of", "text", "justification."};
        Assert.assertEquals("[This    is    an, example  of text, justification.  ]", ""+fullJustify(inp, 16));

        inp = new String[]{"12345", "1234", "1"};
        Assert.assertEquals("[12345  , 1234 1 ]", ""+fullJustify(inp, 7));

        inp = new String[] {"What","must","be","shall","be."};
        Assert.assertEquals("[What must be, shall be.   ]", ""+fullJustify(inp, 12));


    }
}

