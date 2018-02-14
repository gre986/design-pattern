package micromouse;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class MicroMouse extends JFrame implements ActionListener, ChangeListener
{
	JFrame main;
	JPanel btn;
	Maze maze;
	AI ai;

	int speed = 100;

	public MicroMouse()
	{
		main = new JFrame("MicroMouse");
		btn = new JPanel();
		main.setSize(450, 550);
		main.setLayout(new BorderLayout());
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Maze maze = new Maze();
		main.getContentPane().add(maze, BorderLayout.LINE_START);

		JSlider spd = new JSlider(JSlider.HORIZONTAL, 0, 1000, 650);
		spd.addChangeListener(this);
		main.getContentPane().add(spd, BorderLayout.PAGE_END);

		main.getContentPane().add(btn, BorderLayout.LINE_END);

        main.setVisible(true);

        maze.LoadMaze();
		//maze.moveUp();
		// maze.Rx = 0;
		// maze.Ry = 15;
		int temp[][] = new int[16][16];
		
		for (int i=1; i<7; i++) {
			System.out.println("");
			System.out.println("Iteration is "+ i);
			System.out.println("");
			maze.moveUp();
			maze.Rx = 0;
			maze.Ry = 15;
			ai = new AI(maze);
			ai.run();
		}
	}


	public static void main(String arg[])
	{
		new MicroMouse();
	}

	public void actionPerformed(ActionEvent e)
	{
	}

	public void stateChanged(ChangeEvent e)
	{
	    JSlider source = (JSlider)e.getSource();
	    if (!source.getValueIsAdjusting())
	    {
	    	ai.setSpeed((int)source.getValue());

	    }

	}
}

