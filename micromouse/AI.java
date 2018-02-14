package micromouse;

class AI extends Thread 
{
	int speed = 100;
	boolean sleep = false;
	Maze maze;

	AI(Maze m)
	{
		maze = m;
		init();
	}

	public void setSpeed(int spd)
	{
		speed = spd;
	}
	public void run()
	{
		while(true)
		{
			try
			{
				Thread.sleep(1000-speed);
			}catch(InterruptedException e)
			{
			}

			int exit = move();
			
			if(exit == 1) {
				break;
			}
			
			maze.repaint();

			while(sleep)
			{
				try
				{
					Thread.sleep(10);
				}catch(InterruptedException e)
				{
				}
			}
		}
	}

	int position[][] = new int[16][16];
	int middleFloodAr[][] = new int[16][16];
	int startAr[][] = new int[16][16];
	int repeat[][] = new int[16][16];


	private void init()
	{

		int max = 14;
		int min = 7;

		for(int y = 0; y < 16; y++)
		{
			int cnt = min;
			for(int x = 0; x < 16; x++)
			{
				if(x < 8)
				{
					middleFloodAr[x][y] = max-x;
				}else
				{
					middleFloodAr[x][y] = cnt;
					cnt++;
				}

			}
			if(y < 7)
			{
				max--;
				min--;
			}else if(y > 7)
			{
				max++;
				min++;
			}

		}

		for(int y = 0; y < 16; y++)
		{
			int start = 15 - y;
			for(int x = 0; x < 16; x++)
			{
				repeat[x][y] = 0;
				startAr[x][y] = start + x;
			}
		}
		position = middleFloodAr;
	}

	int state = 0;
	int last = 0;
	int i = 0;
	private int move()
	{
		repeat[maze.Rx][maze.Ry]++;

		if(state == 0 && position[maze.Rx][maze.Ry] == 0)
		{
			System.out.println("");
			System.out.println("total step is "+i);
			System.out.println("");
			System.out.println("Mouse reached to its destination");
			System.out.println("");
			System.out.println("Path of the Mouse and number of times mouse entered each box.");
			System.out.println("");
			state = 1;
			position = startAr;

			
			for(int y = 0; y < 16; y++)
			{
				int start = 15 - y;
				for(int x = 0; x < 16; x++)
				{
					
					System.out.print(repeat[x][y] + " ");
				}
				System.out.println("");
			}
			return 1;
		}

		boolean right = false;
		boolean left = false;
		boolean up = false;
		boolean down = false;
		if(!maze.getTop(maze.Rx, maze.Ry))
		{
			up = true;
		}
		if(!maze.getRight(maze.Rx, maze.Ry))
		{
			right = true;
		}
		
		if(!maze.getLeft(maze.Rx, maze.Ry))
		{
			left = true;
		}
		if(!maze.getBottom(maze.Rx, maze.Ry))
		{
			down = true;
		}
		
		if(state == 0 || state == 1)
		{
		
			int best = 10000;

			if(up)
			{
				if(repeat[maze.Rx][maze.Ry-1] < best)
				{
					best = repeat[maze.Rx][maze.Ry-1];
				}else if(repeat[maze.Rx][maze.Ry-1] >= best)
				{
					up = false;
				}
			}
			if(right)
			{
				if(repeat[maze.Rx+1][maze.Ry] < best)
				{
					best = repeat[maze.Rx+1][maze.Ry];
					up = false;
				}else if(repeat[maze.Rx+1][maze.Ry] >best)
				{
					right = false;
				}
			}
			if(left)
			{
				if(repeat[maze.Rx-1][maze.Ry] < best)
				{
					up = false;
					right = false;
					best = repeat[maze.Rx-1][maze.Ry];
				}else if(repeat[maze.Rx-1][maze.Ry] > best)
				{
					left = false;
				}
			}
			if(down)
			{
				if(repeat[maze.Rx][maze.Ry+1] < best)
				{
					up = false;
					right = false;
					left = false;
					best = repeat[maze.Rx][maze.Ry+1];
				}else if(repeat[maze.Rx][maze.Ry+1] > best)
				{
					down = false;
				}
			}

			best = 35;
			if(up)
			{
				if(position[maze.Rx][maze.Ry-1] < best)
				{
					best = position[maze.Rx][maze.Ry-1];
				}else if(position[maze.Rx][maze.Ry-1] >best)
				{
					up = false;
				}
			}
			if(right)
			{
				if(position[maze.Rx+1][maze.Ry] < best)
				{
					up = false;
					best = position[maze.Rx+1][maze.Ry];
				}else if(position[maze.Rx+1][maze.Ry] > best)
				{
					right = false;
				}
			}
			if(left)
			{
				if(position[maze.Rx-1][maze.Ry] < best)
				{
					up = false;
					right = false;
					best = position[maze.Rx-1][maze.Ry];
								if(i == 167)
								{
									System.out.println(best);
			}
				}else if(position[maze.Rx-1][maze.Ry] > best)
				{
					left = false;
				}
			}
			if(down)
			{
				if(position[maze.Rx][maze.Ry+1] < best)
				{
					up = false;
					right = false;
					left = false;
					best = position[maze.Rx][maze.Ry+1];
				}else if(position[maze.Rx][maze.Ry+1] > best)
				{
					down = false;
				}
			}
		}


		if(i == 167)
		{
			System.out.println(i + ": " + left);
		}


		if(up && last == 1)
		{
			right = false;
			left = false;
			down = false;
		}else if(right && last == 2)
		{
			up = false;
			left = false;
			down = false;
		}else if(left && last == 3)
		{
			up = false;
			right = false;
			down = false;
		}else if(down && last == 4)
		{
			up = false;
			right = false;
			left = false;
		}


		i++;
		
		if(up)
		{	if(maze.getBottom(maze.Rx, maze.Ry) && Direction.mouseHead.equals("S") &&maze.getLeft(maze.Rx,maze.Ry) && maze.getRight(maze.Rx,maze.Ry) && maze.getTop(maze.Rx, maze.Ry)== false)
			{
				maze.moveUp();
				maze.top[maze.Rx][maze.Ry+1] = true;
				System.out.println("Direction : " + Direction.getHeadDirection(false, false, true, false));
			}
			else{
				last = 1;
			maze.moveUp();
			System.out.println("Direction : " + Direction.getHeadDirection(true, false, false, false));
			}
			
		}else if(right)
		{
			if(maze.getLeft(maze.Rx,maze.Ry) && Direction.mouseHead.equals("W") &&maze.getBottom(maze.Rx, maze.Ry) &&maze.getRight(maze.Rx,maze.Ry)== false && maze.getTop(maze.Rx, maze.Ry))
			{
				maze.moveRight();
				maze.side[maze.Rx][maze.Ry]=true;
				System.out.println("Direction : " + Direction.getHeadDirection(false, false, false, true));
			}else{
				last = 2;
			maze.moveRight();
			System.out.println("Direction : " + Direction.getHeadDirection(false, true, false, false));
			}
			
		}else if(left)
		{
			if(maze.getRight(maze.Rx,maze.Ry) && Direction.mouseHead.equals("E") &&maze.getBottom(maze.Rx, maze.Ry) &&maze.getLeft(maze.Rx,maze.Ry)== false && maze.getTop(maze.Rx, maze.Ry))
			{
				maze.moveLeft();
				maze.side[maze.Rx+1][maze.Ry]=true;
				System.out.println("Direction : " + Direction.getHeadDirection(false, true, false, false));
			}else{
			last = 3;
			maze.moveLeft();
			System.out.println("Direction : " + Direction.getHeadDirection(false, false, false, true));
			}
		}else
		{
			if(maze.getTop(maze.Rx,maze.Ry)&& Direction.mouseHead.equals("N")&& maze.getBottom(maze.Rx, maze.Ry)== false &&maze.getLeft(maze.Rx,maze.Ry) && maze.getRight(maze.Rx, maze.Ry))
			{
				maze.moveDown();
				maze.top[maze.Rx][maze.Ry]=true;
				System.out.println("Direction : " + Direction.getHeadDirection(true, false, false, false));
			}else{
			last = 4;
			maze.moveDown();
			System.out.println("Direction : " + Direction.getHeadDirection(false, false, true, false));
			}
		}

		return 0;
	}
	
	
}