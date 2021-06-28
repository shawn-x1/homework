package GameOfLife;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WorldTest3 {
	World a=new World(30,40);
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsVaildCell() {
		assertEquals(false,a.isVaildCell(40, 50));
		assertEquals(false,a.isVaildCell(-5, 20));
		assertEquals(true,a.isVaildCell(10, 20));
	}

}
