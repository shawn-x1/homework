package GameOfLife;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.*;

public class World extends JPanel implements Runnable{
	private final int rows;  //行
	private final int columns; //列
	JLabel  record;  //记录存活数量
	boolean diy=false;
	boolean clean=false;
	private int speed=8;
	private int lnum;  //存活数量
	private static int shape[][]=new int [30][40];
	private static int zero[][]=new int [30][40];
	static  int pauseshape[][]=new int [30][40];    //记录当前细胞状况
	private final CellStatus[][] generation1;//保证不指向其他对象
	private final CellStatus[][] generation2;
	private CellStatus[][] currentGeneration; //当前细胞状况
	private CellStatus[][] nextGeneration;   //下一代细胞状况
	private volatile boolean isChanging = false;
	private volatile boolean running = true;
	public World(int rows, int columns)
	{
		
		this.rows=rows;
		this.columns=columns;
		record = new JLabel();
		add(record);
		generation1=new CellStatus[rows][columns];
		//addMouseMotionListener(this);
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				generation1[i][j]=CellStatus.Dead;
			}
		}
		generation2=new CellStatus[rows][columns];
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				generation2[i][j]=CellStatus.Dead;
			}
		}
		currentGeneration=generation1;
		nextGeneration=generation2;
	}
	public void transfrom(CellStatus[][] generation, int pauseshape[][])
	{
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				if(generation[i][j]==CellStatus.Active)
				{
					pauseshape[i][j]=1;
				}
				else if(generation[i][j]==CellStatus.Dead)
				{
					pauseshape[i][j]=0;
				}
			}
		}
	}
	public void run()
	{
//		begintime=System.currentTimeMillis();
		while(running)
		{
			synchronized(this)
			{
				while(isChanging)
				{
					
					try
					{
						this.wait();
					}catch(InterruptedException e)
					{
						e.printStackTrace();
					}
				}
//				repaint();
				sleep(speed);
				for(int i=0;i<rows;i++)
				{
					for(int j=0;j<columns;j++)
					{
						evolve(i,j);
					}
				}
				/*for(int i=0;i<rows;i++)
				{
					for(int j=0;j<columns;j++)
					{
						if(currentGeneration[i][j]==nextGeneration[i][j])
							running=false;
					}
				}*/
				CellStatus[][]temp=null;
				temp=getCurrentGeneration();
				setCurrentGeneration(getNextGeneration());
				setNextGeneration(temp);
				for(int i=0;i<rows;i++)
				{
					for(int j=0;j<columns;j++)
					{
						getNextGeneration()[i][j]=CellStatus.Dead;	
					}
				}
				//transfrom(currentGeneration,shape);
				transfrom(getCurrentGeneration(),pauseshape);
				repaint();
				//endtime=System.currentTimeMillis();
				updateNumber();
			}
		}
	}
	public void run1()
	{   boolean test=true;
//		begintime=System.currentTimeMillis();
		while(test)
		{
			synchronized(this)
			{
				while(isChanging)
				{
					
					try
					{
						this.wait();
					}catch(InterruptedException e)
					{
						e.printStackTrace();
					}
				}
//				repaint();
				sleep(speed);
				for(int i=0;i<rows;i++)
				{
					for(int j=0;j<columns;j++)
					{
						evolve(i,j);
					}
				}
				CellStatus[][]temp=null;
				temp=getCurrentGeneration();
				setCurrentGeneration(getNextGeneration());
				setNextGeneration(temp);
				for(int i=0;i<rows;i++)
				{
					for(int j=0;j<columns;j++)
					{
						getNextGeneration()[i][j]=CellStatus.Dead;	
					}
				}
				//transfrom(currentGeneration,shape);
				transfrom(getCurrentGeneration(),pauseshape);
				repaint();
				//endtime=System.currentTimeMillis();
				updateNumber();
				test=false;
			}
		}
	}
	public void updateNumber()
	{
		String s = " 存活数量： " + lnum ;
		record.setText(s);
	}
	public void changeSpeedSlow()
	{
		speed=8;
	}
	public void changeSpeedFast()
	{
		speed=3;
	}
	public void changeSpeedHyper()
	{
		speed=1;
	}
	public void paintComponent(Graphics g)     //画图，生填充，死画矩形
	{
		lnum=0;
		super.paintComponent(g);
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				if(getCurrentGeneration()[i][j]==CellStatus.Active)
				{
					g.fillRect(j*20, i*20, 20, 20);
					lnum++;
				}
				else
				{
					g.drawRect(j*20, i*20, 20, 20);
				}
			}
		}
	}
//	public void setArrow()
//	{
//		setZero();
//		//shape=arrow;
//		setShapetemp(arrow);
//		//pauseshape=shape;
//	}
//	public void setSquare()
//	{
//		setZero();
//		//shape=square;
//		setShapetemp(square);
//		//pauseshape=shape;
//	}
	public void setShape()
	{
		setShape(shape);
	}
	public void setRandom()
	{
		Random a=new Random();
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				shape[i][j]=Math.abs(a.nextInt(2));
				pauseshape[i][j]=shape[i][j];
			}
		}
		setShapetemp(shape);
	}
	public void setZero()
	{
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				zero[i][j]=0;
			}
		}
	}
	public void setStop()
	{
		setZero();		
		shape=zero;
		setShape(shape);
		pauseshape=shape;
	}
	
	public void setPause()
	{
		shape=pauseshape;
		setShapetemp(pauseshape);
	}
	
	public void setDiy()
	{
		shape=pauseshape;
		setShapetemp(shape);
	}
	private void setShapetemp(int [][]shape)   //把shape[][]的数据转化成细胞状态，并更新地图和存活数量
	{
		isChanging=true;
		int arrowsRows=shape.length;
		int arrowsColumns=shape[0].length;
		int minimumRows=(arrowsRows<rows)?arrowsRows:rows;
		int minimunColumns=(arrowsColumns<columns)?arrowsColumns:columns;
		synchronized(this)
		{
			for(int i=0;i<rows;i++)
			{
				for(int j=0;j<columns;j++)
				{
					getCurrentGeneration()[i][j]=CellStatus.Dead;
				}
			}
			for(int i=0;i<minimumRows;i++)
			{
				for(int j=0;j<minimunColumns;j++)
				{
					if(shape[i][j]==1)
					{
						getCurrentGeneration()[i][j]=CellStatus.Active;
					}
				}
			}
			//transfrom(currentGeneration,pauseshape);
			repaint();
			updateNumber();
//			isChanging=true;
//			this.notifyAll();
		}
	}
	public void setShape(int [][]shape)   //把shape[][]的数据转化成细胞状态
	{
		isChanging=true;
		int arrowsRows=shape.length;
		int arrowsColumns=shape[0].length;
		int minimumRows=(arrowsRows<rows)?arrowsRows:rows;
		int minimunColumns=(arrowsColumns<columns)?arrowsColumns:columns;
		synchronized(this)
		{
			for(int i=0;i<rows;i++)
			{
				for(int j=0;j<columns;j++)
				{
					getCurrentGeneration()[i][j]=CellStatus.Dead;
				}
			}
			for(int i=0;i<minimumRows;i++)
			{
				for(int j=0;j<minimunColumns;j++)
				{
					if(shape[i][j]==1)
					{
						getCurrentGeneration()[i][j]=CellStatus.Active;
					}
				}
			}

			isChanging=false;
			this.notifyAll();
		}
		
	}
	
	public void evolve(int x,int y)
	{
		int activeSurroundingCell=0;
		if(isVaildCell(x-1,y-1)&&(getCurrentGeneration()[x-1][y-1]==CellStatus.Active))
			activeSurroundingCell++;
		if(isVaildCell(x,y-1)&&(getCurrentGeneration()[x][y-1]==CellStatus.Active))
			activeSurroundingCell++;
		if(isVaildCell(x+1,y-1)&&(getCurrentGeneration()[x+1][y-1]==CellStatus.Active))
			activeSurroundingCell++;
		if(isVaildCell(x+1,y)&&(getCurrentGeneration()[x+1][y]==CellStatus.Active))
			activeSurroundingCell++;
		if(isVaildCell(x+1,y+1)&&(getCurrentGeneration()[x+1][y+1]==CellStatus.Active))
			activeSurroundingCell++;
		if(isVaildCell(x,y+1)&&(getCurrentGeneration()[x][y+1]==CellStatus.Active))
			activeSurroundingCell++;
		if(isVaildCell(x-1,y+1)&&(getCurrentGeneration()[x-1][y+1]==CellStatus.Active))
			activeSurroundingCell++;
		if(isVaildCell(x-1,y)&&(getCurrentGeneration()[x-1][y]==CellStatus.Active))
			activeSurroundingCell++;
		//
		if(activeSurroundingCell==3)
		{
			getNextGeneration()[x][y]=CellStatus.Active;
		}
		else if(activeSurroundingCell==2)
		{
			getNextGeneration()[x][y]=getCurrentGeneration()[x][y];
		}
		else
		{
			getNextGeneration()[x][y]=CellStatus.Dead;
		}
	}
	public boolean isVaildCell(int x,int y)
	{
		if((x>=0)&&(x<rows)&&(y>=0)&&(y<columns))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	private void sleep(int x)
	{
		try {
			Thread.sleep(80*x);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
	}
	public CellStatus[][] getCurrentGeneration() {
		return currentGeneration;
	}
	public void setCurrentGeneration(CellStatus[][] currentGeneration) {
		this.currentGeneration = currentGeneration;
	}
	public CellStatus[][] getNextGeneration() {
		return nextGeneration;
	}
	public void setNextGeneration(CellStatus[][] nextGeneration) {
		this.nextGeneration = nextGeneration;
	}
	public int getspeed() {
		return speed;
	}
	static enum CellStatus
	{
		Active,
		Dead;
	}	
}

