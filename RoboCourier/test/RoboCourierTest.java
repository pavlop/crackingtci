import org.junit.Test;
import static org.junit.Assert.*;

public class RoboCourierTest {
	
	@Test
	public void test0() {
		String[] path = new String[] { "FRRFLLFLLFRRFLF" };
		assertEquals(15, new RoboCourier().timeToDeliver(path));
	}

//	@Test
//	public void test1() {
//		String[] path = new String[] { "RFLLF" };
//		assertEquals(17, new RoboCourier().timeToDeliver(path));
//	}
//
	@Test
	public void test2() {
		String[] path = new String[] { "FLFRRFRFRRFLLFRRF" };
		assertEquals(0, new RoboCourier().timeToDeliver(path));
	}
//
	@Test
	public void test3() {
		String[] path = new String[] { "FFFFFFFFFRRFFFFFFRRFFFFF",
  "FLLFFFFFFLLFFFFFFRRFFFF" };
		assertEquals(44, new RoboCourier().timeToDeliver(path));
	}
//
//	@Test
//	public void test4() {
//		String[] path = new String[] { "RFLLFLFLFRFRRFFFRFFRFFRRFLFFRLRRFFLFFLFLLFRFLFLRFF",
//  "RFFLFLFFRFFLLFLLFRFRFLRLFLRRFLRFLFFLFFFLFLFFRLFRLF",
//  "LLFLFLRLRRFLFLFRLFRF" };
//		assertEquals(24, new RoboCourier().timeToDeliver(path));
//	}
//
//	@Test
//	public void test5() {
//		String[] path = new String[] { "LLFLFRLRRLRFFLRRRRFFFLRFFRRRLLFLFLLRLRFFLFRRFFFLFL",
//  "RLFFRRLRLRRFFFLLLRFRLLRFFLFRLFRRFRRRFRLRLRLFFLLFLF",
//  "FRFLRFRRLLLRFFRRRLRFLFRRFLFFRLFLFLFRLLLLFRLLRFLLLF",
//  "FFLFRFRRFLLFFLLLFFRLLFLRRFRLFFFRRFFFLLRFFLRFRRRLLR",
//  "FFFRRLLFLLRLFRRLRLLFFFLFLRFFRLRLLFLRLFFLLFFLLFFFRR",
//  "LRFRRFLRRLRRLRFFFLLLLRRLRFFLFRFFRLLRFLFRRFLFLFFLFR",
//  "RFRRLRRFLFFFLLRFLFRRFRFLRLRLLLLFLFFFLFRLLRFRLFRLFR",
//  "LLFLFRLFFFFFFFRRLRLRLLRFLRLRRRRRRRRLFLFLFLRFLFRLFF",
//  "RLFRRLLRRRRFFFRRRLLLLRRLFFLLLLLRFFFFRFRRLRRRFFFLLF",
//  "FFFFLRRLRFLLRRLRLRFRRRRLFLLRFLRRFFFRFRLFFRLLFFRRLL" };
//		assertEquals(169, new RoboCourier().timeToDeliver(path));
//	}
}
