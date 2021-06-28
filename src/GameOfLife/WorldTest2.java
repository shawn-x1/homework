package GameOfLife;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WorldTest2 {
	World a=new World(30,40);
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testChangeSpeedSlow() {
		a.changeSpeedSlow();
		assertEquals(8,a.getspeed());

	}

	@Test
	public void testChangeSpeedFast() {
		a.changeSpeedFast();
		assertEquals(3,a.getspeed());
	}

	@Test
	public void testChangeSpeedHyper() {
		a.changeSpeedHyper();
		assertEquals(1,a.getspeed());
	}

}
