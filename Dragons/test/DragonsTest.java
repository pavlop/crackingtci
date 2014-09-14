import org.junit.Test;
import static org.junit.Assert.*;

public class DragonsTest {
	
	@Test
	public void test0() {
		int[] initialFood = new int[] {0, 0, 4, 0, 0, 0};
		int rounds = 2;
		assertEquals("1", new Dragons().snaug(initialFood, rounds));
	}
	
	@Test
	public void test1() {
		int[] initialFood = new int[] {0, 0, 4, 0, 0, 0};
		int rounds = 3;
		assertEquals("1/2", new Dragons().snaug(initialFood, rounds));
	}
	
	@Test
	public void test2() {
		int[] initialFood = new int[] {1000, 1000, 1000, 1000, 1000, 1000};
		int rounds = 45;
		assertEquals("1000", new Dragons().snaug(initialFood, rounds));
	}
	
	@Test
	public void test3() {
		int[] initialFood = new int[] {1, 2, 3, 4, 5, 6};
		int rounds = 45;
		assertEquals("7/2", new Dragons().snaug(initialFood, rounds));
	}

    @Test
    public void test4() {
        int[] initialFood = new int[] {1, 10, 100, 2, 20, 200};
        int rounds = 45;
        assertEquals("3905465301860361/70368744177664", new Dragons().snaug(initialFood, rounds));
    }

}
