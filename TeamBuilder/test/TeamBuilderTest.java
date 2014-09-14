import org.junit.Test;
import static org.junit.Assert.*;

public class TeamBuilderTest {
	
	@Test
	public void test0() {
		String[] paths = new String[] {"010","000","110"};
		assertArrayEquals(new int[] { 1,  1 }, new TeamBuilder().specialLocations(paths));
	}

	@Test
	public void test1() {
		String[] paths = new String[] {"0010","1000","1100","1000"};
		assertArrayEquals(new int[] { 1,  3 }, new TeamBuilder().specialLocations(paths));
	}

	@Test
	public void test2() {
		String[] paths = new String[] {"01000","00100","00010","00001","10000"};
		assertArrayEquals(new int[] { 5,  5 }, new TeamBuilder().specialLocations(paths));
	}

	@Test
	public void test3() {
		String[] paths = new String[] {"0110000","1000100","0000001","0010000","0110000","1000010","0001000"};
		assertArrayEquals(new int[] { 1,  3 }, new TeamBuilder().specialLocations(paths));
	}
}
