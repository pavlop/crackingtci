import org.junit.Test;
import static org.junit.Assert.*;

public class Egalitarianism3EasyTest {
	
//	@Test
//	public void test0() {
//		int n = 4;
//		int[] a = new int[] {1,1,1};
//		int[] b = new int[] {2,3,4};
//		int[] len = new int[] {1,1,1};
//		assertEquals(3, new Egalitarianism3Easy().maxCities(n, a, b, len));
//	}
//
//	@Test
//	public void test1() {
//		int n = 6;
//		int[] a = new int[] {1,2,3,2,3};
//		int[] b = new int[] {2,3,4,5,6};
//		int[] len = new int[] {2,1,3,2,3};
//		assertEquals(3, new Egalitarianism3Easy().maxCities(n, a, b, len));
//	}
//
//	@Test
//	public void test2() {
//		int n = 10;
//		int[] a = new int[] {1,1,1,1,1,1,1,1,1};
//		int[] b = new int[] {2,3,4,5,6,7,8,9,10};
//		int[] len = new int[] {1000,1000,1000,1000,1000,1000,1000,1000,1000};
//		assertEquals(9, new Egalitarianism3Easy().maxCities(n, a, b, len));
//	}
//
//	@Test
//	public void test3() {
//		int n = 2;
//		int[] a = new int[] {1};
//		int[] b = new int[] {2};
//		int[] len = new int[] {3};
//		assertEquals(2, new Egalitarianism3Easy().maxCities(n, a, b, len));
//	}
//
//	@Test
//	public void test4() {
//		int n = 1;
//		int[] a = new int[] {};
//		int[] b = new int[] {};
//		int[] len = new int[] {};
//		assertEquals(1, new Egalitarianism3Easy().maxCities(n, a, b, len));
//	}

    @Test
    public void test5() {
        int n = 10;
        int[] a = new int[] {7, 10, 7, 9, 6, 8, 7, 7, 7};
        int[] b = new int[] {5, 2, 10, 4, 8, 1, 6, 3, 9};
        int[] len = new int[] {632, 632, 633, 633, 632, 632, 632, 632, 633};
        assertEquals(3, new Egalitarianism3Easy().maxCities(n, a, b, len));
    }


}
