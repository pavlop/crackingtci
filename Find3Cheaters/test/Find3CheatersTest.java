import org.junit.Test;
import static org.junit.Assert.*;

public class Find3CheatersTest {
	
	@Test
	public void test0() {
		String a = "aagaaa";
		String b = "ataatg";
		String c = "ctggg";
		assertEquals(11, new Find3Cheaters().shortest(a, b, c));
	}
	
	@Test
	public void test1() {
		String a = "wowwowwow";
		String b = "wowwowwow";
		String c = "badbadbad";
		assertEquals(18, new Find3Cheaters().shortest(a, b, c));
	}
	
	@Test
	public void test2() {
		String a = "a";
		String b = "aaaaaaa";
		String c = "aaaaaaaaaaaaaa";
		assertEquals(14, new Find3Cheaters().shortest(a, b, c));
	}
}
