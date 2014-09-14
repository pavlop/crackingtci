package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *
 */
public class MultiplyStrings {

    public String multiply(String num1, String num2) {
        String longer = num1.length() >= num2.length()? num1:num2;
        String shorter = num1.length() >= num2.length()? num2:num1;
        if(num1.equals("0") || num2.equals("0")) return "0";

        List<String> resultBeforeSum = new ArrayList<String>();

        int biggestLength = 0;

        for (int i = 0 ; i < shorter.length(); i++) {

            int remained = 0;
            int curShort = getFromTheEnd(i, shorter);
            StringBuilder curMultiplyRes = new StringBuilder();

            for (int j = 0 ; j < longer.length(); j++) {
                int curres = curShort * getFromTheEnd(j, longer)  + remained;
                curMultiplyRes.insert(0, curres%10);
                remained = curres/10;
            }

            if(remained > 0) curMultiplyRes.insert(0, remained);
            appendZeros(curMultiplyRes, i);
            resultBeforeSum.add(curMultiplyRes.toString());
            biggestLength = Math.max(biggestLength, curMultiplyRes.length());
        }


        StringBuilder finalResult = new StringBuilder();
        int remained = 0;
        for (int i = 0; i < biggestLength; i++) {
            int curres = 0;

            for (String sb: resultBeforeSum)
                curres += getFromTheEnd(i, sb);
            curres += remained;
            finalResult.insert(0, curres%10);
            remained = curres/10;
        }
        if(remained > 0) finalResult.insert(0, remained);
        return finalResult.toString();

    }

    int getFromTheEnd(int i, String sb) {
        if(i >= sb.length()) return 0;
        int fromEnd = sb.length() - i - 1;
        return Integer.parseInt(sb.substring(fromEnd, fromEnd+1));
    }

    void appendZeros(StringBuilder sb, int numOfZeros) {
        if (sb.length() == 0) return;
        for (int i = 0; i < numOfZeros ; i++)
            sb.append("0");
    }
}
