import org.junit.Test;
import static org.junit.Assert.*;

public class BadParserTest {
	
	@Test
	public void test0() {
		String[] badTree = new String[] {"+ 1 2","5","- 3 4","2","- 5 6","3","* 7 8","4","2"};
		assertEquals(-4, new BadParser().evaluate(badTree));
	}
	
	@Test
	public void test1() {
		String[] badTree = new String[] {"- 1 2","5","- 3 4","2","- 5 6","3","* 7 8","4","2"};
		assertEquals(-8, new BadParser().evaluate(badTree));
	}
	
	@Test
	public void test2() {
		String[] badTree = new String[] {"* 1 2","5","- 3 4","2","- 5 6","3","* 7 8","4","2"};
		assertEquals(-1, new BadParser().evaluate(badTree));
	}
	
	@Test
	public void test3() {
		String[] badTree = new String[] {"99"};
		assertEquals(99, new BadParser().evaluate(badTree));
	}
}
