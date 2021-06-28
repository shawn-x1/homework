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
		//new Thread(world).start();  //��ʼ������
		add(world);
		new Thread(world).start();  //��ʼ������
	}
	public static void main(String[]args)
	{
		LifeGame frame=new LifeGame(30, 40);
		
		frame.addMouseMotionListener(frame);
		JMenuBar menu=new JMenuBar();
		frame.setJMenuBar(menu);
		
		
		JMenu options =new JMenu("�˵�");
		menu.add(options);
		JMenu changeSpeed=new JMenu("�����ٶ�");
		menu.add(changeSpeed);
		JMenu other = new JMenu("����");
		menu.add(other);
		JMenuItem start=options.add("��ʼ");
		start.addActionListener(frame.new StartActionListener());
		JMenuItem random=options.add("������ɿ���");
		random.addActionListener(frame.new RandomActionListener());
		
		JMenuItem stop=options.add("���");
		stop.addActionListener(frame.new StopActionListener());
		JMenuItem pause=options.add("��ͣ");
		pause.addActionListener(frame.new PauseActionListener());
		JMenuItem doityourself=options.add("�������");
		doityourself.addActionListener(frame.new DIYActionListener());
		JMenuItem clean=options.add("ɱ������");
		clean.addActionListener(frame.new CleanActionListener());
		
		JMenuItem slow=changeSpeed.add("��");
		slow.addActionListener(frame.new SlowActionListener());
		JMenuItem fast=changeSpeed.add("��");
		fast.addActionListener(frame.new FastActionListener());
		JMenuItem hyper=changeSpeed.add("����");
		hyper.addActionListener(frame.new HyperActionListener());
		
		JMenuItem help=other.add("����");
		help.addActionListener(frame.new HelpActionListener());
		
		JMenuItem about=other.add("����");
		about.addActionListener(frame.new AboutActionListener());
		//about.addActionListener(frame.new AboutActionListener());
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(820,680);
		//frame.setSize(1207,859);
		//JButton button = new JButton ("button");
		//frame.button.setBounds(108,100,100,100);
		//frame.add(frame.button,BorderLayout.EAST);
		frame.setTitle("������Ϸ");
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
			JOptionPane.showMessageDialog(null, "������Ϸ\n������Ϸ��Ӣ����ѧ��Լ�����ζ١�������1970�귢����ϸ���Զ���\n "
		                                        +"1.ÿ��ϸ����״̬�ɸ�ϸ������Χ 8 ��ϸ����һ�ε�״̬��������\n"
												+"2.���һ��ϸ����Χ�� 3 ��ϸ��Ϊ�������ϸ��Ϊ��������ϸ����ԭ��Ϊ����תΪ���� ��ԭ��Ϊ���򱣳ֲ��䣻\n"
												+"3.���һ��ϸ����Χ�� 2 ��ϸ��Ϊ�������ϸ��������״̬���ֲ��䣻\n"
												+"4.����������£���ϸ��Ϊ��������ϸ����ԭ��Ϊ����תΪ������ԭ��Ϊ���򱣳ֲ��䡣");
		}
	}
	class AboutActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			JOptionPane.showMessageDialog(null, "��Ϸ���ߣ�Фԥ�����Ľ�");
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

