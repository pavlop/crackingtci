import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class DoubleLetter {
	
	public String ableToSolve(String S) {
        List<Character> str = new LinkedList<Character>();
        for (char c:S.toCharArray()) str.add(c);
        boolean wasRemoved = true;
        while (wasRemoved && str.size() > 1) {
            wasRemoved = false;
            for (int i = 0; i < str.size() - 1; i++) {
                if(str.get(i).equals(str.get(i+1))) {
                    wasRemoved = true;
                    str.remove(i);
                    str.remove(i);
                    break;
                }
            }
        }
        if(str.size() == 0) return "Possible";
        else return "Impossible";
	}
}
