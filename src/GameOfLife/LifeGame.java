package GameOfLife;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class LifeGame extends JFrame implements MouseMotionListener{
	private final World world;
	//JButton button = new JButton ("button");
	static JMenu location=new JMenu();
	public LifeGame(int rows,int columns)
	{
		world=new World(rows, columns);
		world.setBackground(Color.white);
		//new Thread(world).start();  //初始化界面
		add(world);
		new Thread(world).start();  //初始化界面
	}
	public static void main(String[]args)
	{
		LifeGame frame=new LifeGame(30, 40);
		
		frame.addMouseMotionListener(frame);
		JMenuBar menu=new JMenuBar();
		frame.setJMenuBar(menu);
		
		
		JMenu options =new JMenu("菜单");
		menu.add(options);
		JMenu changeSpeed=new JMenu("设置速度");
		menu.add(changeSpeed);
		JMenu other = new JMenu("其他");
		menu.add(other);
		JMenuItem start=options.add("开始");
		start.addActionListener(frame.new StartActionListener());
		JMenuItem random=options.add("随机生成开局");
		random.addActionListener(frame.new RandomActionListener());
		
		JMenuItem stop=options.add("清空");
		stop.addActionListener(frame.new StopActionListener());
		JMenuItem pause=options.add("暂停");
		pause.addActionListener(frame.new PauseActionListener());
		JMenuItem doityourself=options.add("添加生命");
		doityourself.addActionListener(frame.new DIYActionListener());
		JMenuItem clean=options.add("杀死生命");
		clean.addActionListener(frame.new CleanActionListener());
		
		JMenuItem slow=changeSpeed.add("慢");
		slow.addActionListener(frame.new SlowActionListener());
		JMenuItem fast=changeSpeed.add("快");
		fast.addActionListener(frame.new FastActionListener());
		JMenuItem hyper=changeSpeed.add("极快");
		hyper.addActionListener(frame.new HyperActionListener());
		
		JMenuItem help=other.add("帮助");
		help.addActionListener(frame.new HelpActionListener());
		
		JMenuItem about=other.add("关于");
		about.addActionListener(frame.new AboutActionListener());
		//about.addActionListener(frame.new AboutActionListener());
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(820,680);
		//frame.setSize(1207,859);
		//JButton button = new JButton ("button");
		//frame.button.setBounds(108,100,100,100);
		//frame.add(frame.button,BorderLayout.EAST);
		frame.setTitle("生命游戏");
		frame.setVisible(true);
		frame.setResizable(false);
	}
//	class ArrowActionListener implements ActionListener
//	{
//		public void actionPerformed(ActionEvent e)
//		{
//			world.diy=false;
//			world.setArrow();
//		}
//	}
//	class SquareActionListener implements ActionListener
//	{
//		public void actionPerformed(ActionEvent e) 
//		{
//			world.diy=false;
//			world.setSquare();
//		}
//	}
	class RandomActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.diy=false;
			world.clean=false;
			world.setBackground(Color.white);
			//world.setStop();
			world.setRandom();
		}
	}
	class StartActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			//world.begintime=System.currentTimeMillis();
			world.setBackground(Color.white);
			world.diy=false;
			world.clean=false;
			world.setShape();
		}
	}
	class StopActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			//world.time=0;
			world.setBackground(Color.white);
			world.diy=false;
			world.clean=false;
			world.setStop();
		}
	}
	class PauseActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.setBackground(Color.white);
			world.diy=false;
			world.clean=false;
			world.setPause();
		}
	}
	class SlowActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.changeSpeedSlow();
		}
	}
	class FastActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.changeSpeedFast();
		}
	}
	class HyperActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.changeSpeedHyper();
		}
	}
	class HelpActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			JOptionPane.showMessageDialog(null, "生命游戏\n生命游戏是英国数学家约翰·何顿·康威在1970年发明的细胞自动机\n "
		                                        +"1.每个细胞的状态由该细胞及周围 8 个细胞上一次的状态所决定；\n"
												+"2.如果一个细胞周围有 3 个细胞为生，则该细胞为生，即该细胞若原先为死则转为生， 若原先为生则保持不变；\n"
												+"3.如果一个细胞周围有 2 个细胞为生，则该细胞的生死状态保持不变；\n"
												+"4.在其它情况下，该细胞为死，即该细胞若原先为生则转为死，若原先为死则保持不变。");
		}
	}
	class AboutActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			JOptionPane.showMessageDialog(null, "游戏作者：肖豫，周文杰");
		}
	}
	class CleanActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.setPause();
			world.clean=true;
			world.diy=false;
			world.setBackground(Color.red);
		}
	}
	class DIYActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.setPause();
			world.diy=true;
			world.clean=false;
			world.setBackground(Color.green);
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(world.diy){
		int x=e.getX();
		int y=e.getY();
		//button.setText("x:"+x+"y:"+y);
		World.pauseshape[(y-50)/20][x/20]=1;
		world.setDiy();
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(world.clean){
		int x=e.getX();
		int y=e.getY();
		//button.setText("x:"+x+"y:"+y);
		World.pauseshape[(y-50)/20][x/20]=0;
		world.setDiy();
		}
	}
}

