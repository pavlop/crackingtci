package leetcode;

/**
 Validate if a given string is numeric.

 Some examples:
 "0" => true
 " 0.1 " => true
 "abc" => false
 "1 a" => false
 "2e10" => true
 Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.

 *
 */
public class ValidNumber {
    public boolean isNumber(String s) {
        s = s.trim();
        int dotsCount = 0;
        int numsCount = 0;
        int numsAfterECount = 0;
        int eCount = 0;

        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0')  {
                if(s.charAt(i) == '.' && dotsCount == 0 && eCount == 0) {
                    dotsCount++;
                } else if(s.charAt(i) == 'e' && eCount == 0 && numsCount > 0) {
                    eCount++;
                } else if ((s.charAt(i) == '-' || s.charAt(i) == '+') && (i == 0 || s.charAt(i-1) == 'e')) {
                } else {
                    return false;
                }
            } else {
                numsCount++;
                if (eCount != 0) numsAfterECount++;
            }
        }
        return (numsCount > 0 && (eCount == 0 || numsAfterECount >0));
    }
}
