import org.junit.Test;
import static org.junit.Assert.*;

public class ZigZagTest {
    @Test
    public void testp() {
        int[] sequence = new int[] { 1, 10, 9, 5, 6, 5, 6 };
        assertEquals(6, new ZigZag().longestZigZag(sequence));
    }

    @Test
    public void testp2() {
        int[] sequence = new int[] { 1, 1 };
        assertEquals(1, new ZigZag().longestZigZag(sequence));
    }

    @Test
    public void testp3() {
        int[] sequence = new int[] { 1, 1 , 1, 1, 2, 2, 2, 1, 2 };
        assertEquals(4, new ZigZag().longestZigZag(sequence));
    }

	@Test
	public void test0() {
		int[] sequence = new int[] { 1, 7, 4, 9, 2, 5 };
		assertEquals(6, new ZigZag().longestZigZag(sequence));
	}

	@Test
	public void test1() {
		int[] sequence = new int[] { 1, 17, 5, 10, 13, 15, 10, 5, 16, 8 };
		assertEquals(7, new ZigZag().longestZigZag(sequence));
	}

	@Test
	public void test2() {
		int[] sequence = new int[] { 44 };
		assertEquals(1, new ZigZag().longestZigZag(sequence));
	}

	@Test
	public void test3() {
		int[] sequence = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		assertEquals(2, new ZigZag().longestZigZag(sequence));
	}

	@Test
	public void test4() {
		int[] sequence = new int[] { 70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32 };
		assertEquals(8, new ZigZag().longestZigZag(sequence));
	}

	@Test
	public void test5() {
		int[] sequence = new int[] { 374, 40, 854, 203, 203, 156, 362, 279, 812, 955,
600, 947, 978, 46, 100, 953, 670, 862, 568, 188,
67, 669, 810, 704, 52, 861, 49, 640, 370, 908,
477, 245, 413, 109, 659, 401, 483, 308, 609, 120,
249, 22, 176, 279, 23, 22, 617, 462, 459, 244 }
;
		assertEquals(36, new ZigZag().longestZigZag(sequence));
	}
}
