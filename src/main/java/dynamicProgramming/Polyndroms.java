package dynamicProgramming;

import org.junit.Test;

import static junit.framework.TestCase.assertSame;

/**
 * Created by pavlop on 3/26/14.
 */
public class Polyndroms {
    public int calculateNumberOfPlyndroms(int length, int dictionaryDimention) {
        // recurrent formula
        // S(len) = S(len-2)*dictionaryDimention. len=2, .. lenght
        // S(0) = 1; S(1) = dictonaryDimention

        if (length==0) return 1;
        else if(length==1) return dictionaryDimention;
        else return dictionaryDimention*calculateNumberOfPlyndroms(length - 2, dictionaryDimention);

        //int result = length%2==0?1:dictionaryDimention;

    }

    @Test
    public void testRecurrentFormula() {
        assertSame(10, calculateNumberOfPlyndroms(1,10));
        assertSame(2, calculateNumberOfPlyndroms(2,2));
        assertSame(4, calculateNumberOfPlyndroms(3,2));
    }
}
