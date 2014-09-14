import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class BadNeighbors {
    public int maxDonations(int[] donations) {
        if (donations.length == 0) return 0;
        if (donations.length == 1) return donations[0];
        int solWithFirst = maxDonationsWithoutLast(Arrays.copyOfRange(donations, 0 , donations.length - 1));
        int solWithLast = maxDonationsWithoutLast(Arrays.copyOfRange(donations, 1 , donations.length));
        return Math.max(solWithFirst, solWithLast);
    }
	
	public int maxDonationsWithoutLast(int[] donations) {

        int previousInculded = donations[0];
        int previousNotInculded = 0;

        for (int i = 1; i < donations.length; i++) {
            int curIncluded = previousNotInculded + donations[i];
            int curNotIncluded = Math.max(previousInculded, previousNotInculded);
            previousInculded = curIncluded;
            previousNotInculded = curNotIncluded;
        }
		return Math.max(previousInculded, previousNotInculded);
	}
}
