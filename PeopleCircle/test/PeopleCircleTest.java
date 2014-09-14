import org.junit.Test;
import static org.junit.Assert.*;

public class PeopleCircleTest {
	
	@Test
	public void test0() {
		int numMales = 5;
		int numFemales = 3;
		int K = 2;
		assertEquals("MFMFMFMM", new PeopleCircle().order(numMales, numFemales, K));
	}
	
	@Test
	public void test1() {
		int numMales = 7;
		int numFemales = 3;
		int K = 1;
		assertEquals("FFFMMMMMMM", new PeopleCircle().order(numMales, numFemales, K));
	}
	
	@Test
	public void test2() {
		int numMales = 25;
		int numFemales = 25;
		int K = 1000;
		assertEquals("MMMMMFFFFFFMFMFMMMFFMFFFFFFFFFMMMMMMMFFMFMMMFMFMMF", new PeopleCircle().order(numMales, numFemales, K));
	}
	
	@Test
	public void test3() {
		int numMales = 5;
		int numFemales = 5;
		int K = 3;
		assertEquals("MFFMMFFMFM", new PeopleCircle().order(numMales, numFemales, K));
	}
	
	@Test
	public void test4() {
		int numMales = 1;
		int numFemales = 0;
		int K = 245;
		assertEquals("M", new PeopleCircle().order(numMales, numFemales, K));
	}

    @Test
    public void test5() {
        int numMales = 0;
        int numFemales = 5;
        int K = 10000;
        assertEquals("FFFFF", new PeopleCircle().order(numMales, numFemales, K));
    }
}
