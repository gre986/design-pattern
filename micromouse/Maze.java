package micromouse;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

class Maze extends JPanel
{
	boolean top[][] = new boolean[16][17];
	boolean side[][] = new boolean [17][16];
	int Rx = 0;
	int Ry = 15;
	int Sx = 0;
	int Sy = 15;

	int x = 300;
	
	Map<Integer,Integer> mouseMap = new HashMap<Integer, Integer>();
	
	public boolean redrawPath = false;

	protected void paintComponent(Graphics g)
	{
		setSize(430,430);
		g.setColor(Color.WHITE);
	    g.fillRect(0, 0, getWidth(), getHeight());
	    g.setColor(Color.BLACK);
	    for(int y = 0; y < 17; y++)
	    {
			for(int x = 0; x < 16; x++)
			{
				if(top[x][y])
				{
					g.drawLine(x*26+5,y*26+5, x*26+31,y*26+5);
				}
			}
		}

		for(int y = 0; y < 16; y++)
		{
			for(int x = 0; x < 17; x++)
			{
				if(side[x][y])
				{
					g.drawLine(x*26+5,y*26+5, x*26+5,y*26+31);
				}
			}
		}

		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(6+(Sx*26), 6+(Sy*26), 25, 25);

		g.setColor(Color.CYAN);
		g.fillRect(6+(7*26), 6+(7*26), 51, 51);

		g.setColor(Color.ORANGE);
		g.fillRect(9+(Rx*26), 9+(Ry*26), 19, 19);
		
		if(Direction.mouseHead.equals("N")) {
			g.setColor(Color.BLACK);
			g.fillRect(9+(Rx*26), 9+(Ry*26), 19, 5);
		}
		if(Direction.mouseHead.equals("E")) {
			g.setColor(Color.BLACK);
			g.fillRect(24+(Rx*26), 9+(Ry*26), 5, 19);
		}
		if(Direction.mouseHead.equals("W")) {
			g.setColor(Color.BLACK);
			g.fillRect(5+(Rx*26), (9+Ry*26), 5, 19);
		}
		if(Direction.mouseHead.equals("S")) {
			g.setColor(Color.BLACK);
			g.fillRect((9+Rx*26), (24+Ry*26), 19,5);
		}
	}

	void setStart(int x, int y)
	{
		Sx = x;
		Sy = y;
	}
	boolean[][] getTop()
	{
		return top;
	}

	boolean[][] getSide()
	{
		return side;
	}

	void printMaze()
	{
		for(int y = 0; y < 16; y++)
		{
			System.out.print(" ");
			for(int x = 0; x < 16; x++)
			{
				if(top[x][y])

				{
					System.out.print("- ");
				}else
				{
					System.out.print("  ");
				}
			}
			System.out.println("");
			for(int x = 0; x < 17; x++)
			{
				if(x == Rx && y == Ry)
				{
					if(side[x][y])
					{
						System.out.print("|0");
					}else
					{
						System.out.print(" 0");
					}
				}else
				{
					if(side[x][y])
					{
						System.out.print("| ");
					}else
					{
						System.out.print("  ");
					}
				}
			}
			System.out.println("");
		}

		System.out.print(" ");
		for(int x = 0; x < 16; x++)
		{
			if(top[x][16])
			{
				System.out.print("- ");
			}else
			{
				System.out.print("  ");
			}
		}
		System.out.println("");
	}

	void LoadMaze()
	{

		File fin = new File("..\\InputTextFiles\\maze2.txt");
		try
		{
			FileInputStream in = new FileInputStream (fin);
			char let;
			let = (char)in.read();
			boolean flag = true;
			boolean spaces = false;
			int x = 0;
			int y = 0;
			while((byte)let != -1)
			{
				if(flag && x < 16)
				{
					if(spaces)
					{
						if(let == '-' || let == '_')
						{
							top[x][y] = true;
							x++;
						}else
						{
							top[x][y] = false;
							x++;
						}
						spaces = false;
					}else
					{
						spaces = true;
					}
				}else if(!flag && x < 17)
				{
					if(!spaces)
					{
						if(let == '|')
						{
							side[x][y] = true;
							x++;
						}else
						{
							side[x][y] = false;
							x++;
						}
						spaces = true;
					}else
					{

					spaces = false;
					}
				}
				if(let == '\n')
				{
					flag = !flag;
					if(flag == true)
					{
						y++;
					}
					x = 0;
					spaces = false;
				}
				let = (char)in.read();
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		for(int y = 0; y < 16; y++)
		{
			for(int x = 0; x < 17; x++)
			{
				if(side[x][y])
				{
				}else
				{
				}
			}
		}

	}

	boolean getTop(int x, int y)
	{
		return top[x][y];
	}

	boolean getBottom(int x, int y)
	{
		return top[x][y+1];
	}

	boolean getLeft(int x, int y)
	{
		return side[x][y];
	}

	boolean getRight(int x, int y)
	{
		return side[x+1][y];
	}

	void setRx(int x)
	{
		Rx = x;
	}

	void setRy(int y)
	{
		Ry = y;
	}

	void setPos(int x, int y)
	{
		Ry = y;
		Rx = x;
	}

	int getRx()
	{
		return Rx;
	}

	int getRy()
	{
		return Ry;
	}

	boolean moveUp()
	{
		if(!getTop(Rx, Ry))
		{
			Ry--;
			return true;
		}else
		{
			return false;
		}
	}

	boolean moveDown()
	{
		if(!getBottom(Rx, Ry))
		{
			Ry++;
			return true;
		}else
		{
			return false;
		}
	}

	boolean moveRight()
	{
		if(!getRight(Rx, Ry))
		{
			Rx++;
			return true;
		}else
		{
			return false;
		}
	}

	boolean moveLeft()
	{
		if(!getLeft(Rx, Ry))
		{
			Rx--;
			return true;
		}else
		{
			return false;
		}
	}
}