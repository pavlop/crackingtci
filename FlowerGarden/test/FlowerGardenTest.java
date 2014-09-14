import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class FlowerGardenTest {

    @Test
    public void testFromTC() {
        int[] height = new int[] {689, 929, 976, 79, 630, 835, 721, 386, 492, 767, 787, 387, 193, 547, 906, 642, 3, 920, 306, 735, 889, 663, 295, 892, 575, 349, 683, 699, 584, 149, 410, 710, 459, 277, 965, 112, 146, 352, 408, 432};
        int[] bloom = {4, 123, 356, 50, 57, 307, 148, 213, 269, 164, 324, 211, 249, 355, 110, 280, 211, 106, 277, 303, 63, 326, 285, 285, 269, 144, 331, 15, 296, 319, 89, 243, 254, 159, 185, 158, 81, 270, 219, 26};
        int[] wilt = {312, 158, 360, 314, 323, 343, 267, 220, 347, 197, 327, 334, 250, 360, 350, 323, 291, 323, 315, 320, 355, 334, 286, 293, 362, 181, 360, 328, 322, 344, 173, 248, 284, 301, 215, 230, 226, 331, 355, 81};
        int[] expected =  new int[] {149, 3, 79, 193, 112, 146, 432, 277, 386, 349, 410, 295, 306, 352, 387, 408, 547, 459, 492, 575, 663, 683, 976, 584, 630, 642, 689, 699, 787, 735, 835, 710, 721, 767, 889, 892, 906, 920, 965, 929};

        assertEquals(Arrays.toString(expected), Arrays.toString(new FlowerGarden().getOrdering(height, bloom, wilt)));
    }

    @Test
    public void testFromTCPavel() {
        int[] height = new int[] {10, 3, 5, 7};
        int[] bloom = {1, 3, 7, 10};
        int[] wilt = {3, 7, 10, 12};
        int[] expected =  new int[] {3, 10, 5, 7};

        assertEquals(Arrays.toString(expected), Arrays.toString(new FlowerGarden().getOrdering(height, bloom, wilt)));
    }

	@Test
	public void test0() {
		int[] height = new int[] {5,4,3,2,1};
		int[] bloom = new int[] {1,1,1,1,1};
		int[] wilt = new int[] {365,365,365,365,365};
		assertArrayEquals(new int[] { 1,  2,  3,  4,  5 }, new FlowerGarden().getOrdering(height, bloom, wilt));
	}

	@Test
	public void test1() {
		int[] height = new int[] {5,4,3,2,1};
		int[] bloom = new int[] {1,5,10,15,20};
		int[] wilt = new int[] {4,9,14,19,24};
		assertArrayEquals(new int[] { 5,  4,  3,  2,  1 }, new FlowerGarden().getOrdering(height, bloom, wilt));
	}

	@Test
	public void test2() {
		int[] height = new int[] {5,4,3,2,1};
		int[] bloom = new int[] {1,5,10,15,20};
		int[] wilt = new int[] {5,10,15,20,25};
		assertArrayEquals(new int[] { 1,  2,  3,  4,  5 }, new FlowerGarden().getOrdering(height, bloom, wilt));
	}

	@Test
	public void test3() {
		int[] height = new int[] {5,4,3,2,1};
		int[] bloom = new int[] {1,5,10,15,20};
		int[] wilt = new int[] {5,10,14,20,25};
		assertArrayEquals(new int[] { 3,  4,  5,  1,  2 }, new FlowerGarden().getOrdering(height, bloom, wilt));
	}

	@Test
	public void test4() {
		int[] height = new int[] {1,2,3,4,5,6};
		int[] bloom = new int[] {1,3,1,3,1,3};
		int[] wilt = new int[] {2,4,2,4,2,4};
		assertArrayEquals(new int[] { 2,  4,  6,  1,  3,  5 }, new FlowerGarden().getOrdering(height, bloom, wilt));
	}

	@Test
	public void test5() {
		int[] height = new int[] {3,2,5,4};
		int[] bloom = new int[] {1,2,11,10};
		int[] wilt = new int[] {4,3,12,13};
		assertArrayEquals(new int[] { 4,  5,  2,  3 }, new FlowerGarden().getOrdering(height, bloom, wilt));
	}


}
