import org.junit.Test;
import static org.junit.Assert.*;

public class SuffixArrayDiv1Test {
	
	@Test
	public void test0() {
		int[] SA = new int[] {3,0,1,2};
		assertEquals(2, new SuffixArrayDiv1().minimalCharacters(SA));
	}
	
	@Test
	public void test1() {
		int[] SA = new int[] {3,2,1,0};
		assertEquals(1, new SuffixArrayDiv1().minimalCharacters(SA));
	}
	
	@Test
	public void test2() {
		int[] SA = new int[] {0,1,2,3};
		assertEquals(2, new SuffixArrayDiv1().minimalCharacters(SA));
	}
	
	@Test
	public void test3() {
		int[] SA = new int[] {7,4,8,6,1,5,2,9,3,0};
		assertEquals(4, new SuffixArrayDiv1().minimalCharacters(SA));
	}
	
	@Test
	public void test4() {
		int[] SA = new int[] {0};
		assertEquals(1, new SuffixArrayDiv1().minimalCharacters(SA));
	}
}
