import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class PeopleCircle {
	
	public String order(int numMales, int numFemales, int K) {
        StringBuffer result = new StringBuffer();

		for (int i = 0; i < numMales; i++) {
            result.append("M");
        }

        int pointer = 0;
        for (int i = 0; i < numFemales; i++) {
            pointer = nextPointer(pointer, result, K);
            result.insert(pointer, "F");
        }
        pointer = nextPointer(pointer, result, K);
        return result.substring(pointer, result.length()) + result.substring(0, pointer);
	}

    public int nextPointer(int curPointer, StringBuffer result, int K ) {
        if (result.length() == 0) return 0;
        int jump = K % result.length();
        int pointer = curPointer - jump + 1;
        if (pointer < 0) pointer = result.length() + pointer;
        return pointer;
    }

}
