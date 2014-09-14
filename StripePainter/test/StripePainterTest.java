import org.junit.Test;
import static org.junit.Assert.*;

public class StripePainterTest {

	@Test
	public void test0() {
		String stripes = "RGBGR";
		assertEquals(3, new StripePainter().minStrokes(stripes));
	}

	@Test
	public void test1() {
		String stripes = "RGRG";
		assertEquals(3, new StripePainter().minStrokes(stripes));
	}

	@Test
	public void test2() {
		String stripes = "ABACADA";
		assertEquals(4, new StripePainter().minStrokes(stripes));
	}

	@Test
	public void test3() {
		String stripes = "AABBCCDDCCBBAABBCCDD";
		assertEquals(7, new StripePainter().minStrokes(stripes));
	}

	@Test
	public void test4() {
		String stripes = "BECBBDDEEBABDCADEAAEABCACBDBEECDEDEACACCBEDABEDADD";
		assertEquals(26, new StripePainter().minStrokes(stripes));
	}
}
