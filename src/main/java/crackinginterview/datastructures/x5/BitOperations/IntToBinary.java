package crackinginterview.datastructures.x5.BitOperations;

/**
 * Created by pavlop on 4/21/14.
 */

public class IntToBinary {
    public static String intToBinary(int num) {
        StringBuilder sb = new StringBuilder();
        int tmpNum = num;
        while (tmpNum > 0) {
            sb.append(tmpNum%2);
            tmpNum = tmpNum/2;
        }
        return sb.reverse().toString();
    }

    public static int binaryToInt(String binary) {
        int res = 0;
        int len =  binary.length();
        for (int i =0; i < len; i++) {
            res += Integer.parseInt(binary.charAt(i)+"")*(1<<(len - i - 1));
        }
        return res;
    }

}
