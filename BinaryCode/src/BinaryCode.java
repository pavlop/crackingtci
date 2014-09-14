import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class BinaryCode {
	
	public String[] decode(String message) {

        boolean res1OK = true;
        boolean res2OK = true;

        int[] erncrypted = new int[message.length()];
        int[] res1 = new int[message.length()];
        int[] res2 = new int[message.length()];

        for(int i =0; i < message.length(); i++) {
            Integer num = Integer.parseInt(message.substring(i, i+1));
            erncrypted[i] = num;
        }

        if(message.length() > 1) {
            res1[0] = 0;
            res2[0] = 1;

            res1[1] = erncrypted[0] - res1[0];
            res2[1] = erncrypted[0] - res2[0];

            for (int i = 2; i < erncrypted.length; i++) {
                res1[i] = erncrypted[i - 1] - res1[i - 1] - res1[i - 2];
                res2[i] = erncrypted[i - 1] - res2[i - 1] - res2[i - 2];
            }

            if(erncrypted[erncrypted.length-1]!=(res1[erncrypted.length-1]+res1[erncrypted.length-2])) {res1OK = false;}
            if(erncrypted[erncrypted.length-1]!=(res2[erncrypted.length-1]+res2[erncrypted.length-2])) {res2OK = false;}
        } else {
            res1[0] = erncrypted[0];
            res2[0] = -1;
        }


        StringBuilder stringResult1 = new StringBuilder();
        StringBuilder stringResult2 = new StringBuilder();

        for (int i = 0; i < erncrypted.length; i++) {
            if(res1[i] != 0 && res1[i] != 1) res1OK = false;
            if(res2[i] != 0 && res2[i] != 1) res2OK = false;
            stringResult1.append(res1[i]);
            stringResult2.append(res2[i]);
        }


        String s1 = "NONE";
        String s2 = "NONE";
        if(res1OK) s1 = stringResult1.toString();
        if(res2OK) s2 = stringResult2.toString();

        System.out.println(message);
        System.out.println(s1);
        System.out.println(s2);

        return new String[] {s1, s2};

	}
}
