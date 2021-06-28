package GameOfLife;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import GameOfLife.World.CellStatus;


public class WorldTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEvolve() {	
	    World a=new World(30,40);
	    
		for(int i=0;i<30;i++) {
			for(int j=0;j<40;j++) {
				assertEquals(CellStatus.Dead,a.getCurrentGeneration()[i][j]);//判断初始化是否成功
				assertEquals(CellStatus.Dead,a.getNextGeneration()[i][j]);
				a.getCurrentGeneration()[i][j]=CellStatus.Active;
			}
		}
		a.getCurrentGeneration()[0][0]=CellStatus.Dead;
		for(int i=0;i<30;i++) {
			for(int j=0;j<40;j++) {
				a.evolve(i,j);
				}
			}		
				assertEquals(CellStatus.Active,a.getNextGeneration()[0][0]);//三个活
				assertEquals(CellStatus.Active,a.getNextGeneration()[0][39]);
				assertEquals(CellStatus.Active,a.getNextGeneration()[29][0]);
				assertEquals(CellStatus.Active,a.getNextGeneration()[26][39]);
				assertEquals(CellStatus.Dead,a.getNextGeneration()[1][1]);//三个以上活
				
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
		for(int i=0;i<30;i++) {
			for(int j=0;j<40;j++) {
				a.evolve(i,j);
			}
		}
		/*
		 1 1 0
		 1 1 0
		 0 0 0
		 */
		assertEquals(CellStatus.Active,a.getNextGeneration()[0][1]);  //原先为活，边上两个活
		assertEquals(CellStatus.Dead,a.getNextGeneration()[1][0]);  //原先为死，边上两个活
	
	}
}
