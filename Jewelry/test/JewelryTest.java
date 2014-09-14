import org.junit.Test;
import static org.junit.Assert.*;

public class JewelryTest {

    @Test
    public void testp() {
        int[] values = new int[] {1,2,3};
        assertEquals(1L, new Jewelry().howMany(values));
    }


	@Test
	public void test0() {
		int[] values = new int[] {1,2,5,3,4,5};
		assertEquals(9L, new Jewelry().howMany(values));
	}
//
//	@Test
//	public void test1() {
//		int[] values = new int[] {1000,1000,1000,1000,1000,
// 1000,1000,1000,1000,1000,
// 1000,1000,1000,1000,1000,
// 1000,1000,1000,1000,1000,
// 1000,1000,1000,1000,1000,
// 1000,1000,1000,1000,1000};
//		assertEquals(18252025766940L, new Jewelry().howMany(values));
//	}
//
//	@Test
//	public void test2() {
//		int[] values = new int[] {1,2,3,4,5};
//		assertEquals(4L, new Jewelry().howMany(values));
//	}
//
//	@Test
//	public void test3() {
//		int[] values = new int[] {7,7,8,9,10,11,1,2,2,3,4,5,6};
//		assertEquals(607L, new Jewelry().howMany(values));
//	}
//
//	@Test
//	public void test4() {
//		int[] values = new int[] {123,217,661,678,796,964,54,111,417,526,917,923};
//		assertEquals(0L, new Jewelry().howMany(values));
//	}
}
