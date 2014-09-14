import org.junit.Test;
import static org.junit.Assert.*;

public class BadMazeStrategyTest {
	
	@Test
	public void test0() {
		String[] maze = new String[] {"XXXXXXX"
,"X.....X"
,"X.....X"
,"XY...DX"
,"X.....X"
,"XXXXXXX"};
		assertEquals(4, new BadMazeStrategy().numSteps(maze));
	}
	
	@Test
	public void test1() {
		String[] maze = new String[] {"XXXXXXX"
,"XY....X"
,"X.....X"
,"X.....X"
,"X....DX"
,"XXXXXXX"};
		assertEquals(7, new BadMazeStrategy().numSteps(maze));
	}
	
	@Test
	public void test2() {
		String[] maze = new String[] {"XXXXXXX"
,"XY....X"
,"X.....X"
,"X..D..X"
,"X.....X"
,"XXXXXXX"};
		assertEquals(-1, new BadMazeStrategy().numSteps(maze));
	}
	
	@Test
	public void test3() {
		String[] maze = new String[] {"DY............."};
		assertEquals(27, new BadMazeStrategy().numSteps(maze));
	}
	
	@Test
	public void test4() {
		String[] maze = new String[] {"Y..X.............."
,"XXX.XXXXXXXXXXXX.X"
,".X.X.XX.......D..."
,".XX.XXX..........."}
;
		assertEquals(39, new BadMazeStrategy().numSteps(maze));
	}
}
