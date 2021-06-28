package GameOfLife;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import GameOfLife.World.CellStatus;

public class WorldTest4 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRun() {
		World a = new World(30,40);
		for(int i=0;i<30;i++) {
			for(int j=0;j<40;j++) {
				a.getCurrentGeneration()[i][j]=CellStatus.Dead;
			}
		}
		a.getCurrentGeneration()[0][0]=CellStatus.Active;
		a.getCurrentGeneration()[0][1]=CellStatus.Active;
		a.getCurrentGeneration()[1][1]=CellStatus.Active;
	/*   1 1 0
		 0 1 0
		 0 0 0 */
		a.run1();
	 /*  1 1 0
		 1 1 0
		 0 0 0 */
		for(int i=0;i<30;i++) {
			for(int j=0;j<40;j++) {
				assertEquals(CellStatus.Dead,a.getNextGeneration()[i][j]);	
			}
		}
		assertEquals(CellStatus.Active,a.getCurrentGeneration()[0][0]);
		assertEquals(CellStatus.Active,a.getCurrentGeneration()[1][0]);
		assertEquals(CellStatus.Dead,a.getCurrentGeneration()[1][2]);
		}

}
