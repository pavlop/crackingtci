import org.junit.Test;
import static org.junit.Assert.*;

public class DungeonEscapeTest {
	
	@Test
	public void test0() {
		String[] up = new String[] {"0x4",
"0x3",
"0x3"};
		String[] down = new String[] {"0x9",
"009",
"0x9"};
		String[] east = new String[] {"0x9",
"1x9",
"009"};
		String[] west = new String[] {"0x9",
"0x0",
"009"};
		int startLevel = 2;
		int startEasting = 2;
		assertEquals(10, new DungeonEscape().exitTime(up, down, east, west, startLevel, startEasting));
	}

	@Test
	public void test1() {
		String[] up = new String[] {"xxxxxxxxx1",
"1xxxxxxxxx",
"xxxxxxxxx1"};
		String[] down = new String[] {"xxxxxxxxxx",
"xxxxxxxxxx",
"xxxxxxxxxx"};
		String[] east = new String[] {"1111111111",
"xxxxxxxxxx",
"" +
        "" +
        ""};
		String[] west = new String[] {"xxxxxxxxxx",
"1111111111",
"xxxxxxxxxx"};
		int startLevel = 2;
		int startEasting = 0;
		assertEquals(30, new DungeonEscape().exitTime(up, down, east, west, startLevel, startEasting));
	}

	@Test
	public void test2() {
		String[] up = new String[] {
"xxxxxxxxx1",
"xxxx999991",
"x999999991"};
		String[] down = new String[] {
"1111111111",
"1111111111",
"1111111111"};
		String[] east = new String[] {
"1111122242",
"2222222241",
"2111111111"};
		String[] west = new String[] {
"xxxxxxxxx1",
"1111111111",
"xxxxxxxxx1"};
		int startLevel = 2;
		int startEasting = 0;
		assertEquals(-1, new DungeonEscape().exitTime(up, down, east, west, startLevel, startEasting));
	}

	@Test
	public void test3() {
		String[] up = new String[] {
"1x2x3x4x5x6x7x8x9",
"00000000000000000",
"98765432223456789",
"12345678987654321"};
		String[] down = new String[] {
"00000000000000000",
"00000000000000000",
"00000000000000000",
"00000000000000000"};
		String[] east = new String[] {
"xxxxxxxxxxxxxxxxx",
"xxxxxxxxxxxxxxxxx",
"22222222222222222",
"33333333333333333"};
		String[] west = new String[] {
"xxxxxxxxxxxxxxxxx",
"xxxxxxxxxxxxxxxxx",
"22222222222222222",
"33333333333333333"};
		int startLevel = 3;
		int startEasting = 12;
		assertEquals(17, new DungeonEscape().exitTime(up, down, east, west, startLevel, startEasting));
	}
}
