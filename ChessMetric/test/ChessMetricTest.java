import org.junit.Test;
import static org.junit.Assert.*;

public class ChessMetricTest {
	
	@Test
	public void test0() {
		int size = 3;
		int[] start = new int[] {0,0};
		int[] end = new int[] {1,0};
		int numMoves = 1;
		assertEquals(1L, new ChessMetric().howMany(size, start, end, numMoves));
	}
	
	@Test
	public void test1() {
		int size = 3;
		int[] start = new int[] {0,0};
		int[] end = new int[] {1,2};
		int numMoves = 1;
		assertEquals(1L, new ChessMetric().howMany(size, start, end, numMoves));
	}
	
	@Test
	public void test2() {
		int size = 3;
		int[] start = new int[] {0,0};
		int[] end = new int[] {2,2};
		int numMoves = 1;
		assertEquals(0L, new ChessMetric().howMany(size, start, end, numMoves));
	}
	
	@Test
	public void test3() {
		int size = 3;
		int[] start = new int[] {0,0};
		int[] end = new int[] {0,0};
		int numMoves = 2;
		assertEquals(5L, new ChessMetric().howMany(size, start, end, numMoves));
	}
	
	@Test
	public void test4() {
		int size = 100;
		int[] start = new int[] {0,0};
		int[] end = new int[] {0,99};
		int numMoves = 50;
		assertEquals(243097320072600L, new ChessMetric().howMany(size, start, end, numMoves));
	}
}
