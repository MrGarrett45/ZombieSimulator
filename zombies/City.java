package cs2113.zombies;

import cs2113.util.Helper;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Iterator;


public class City
{

	/** walls is a 2D array with an entry for each space in the city.
	 *  If walls[x][y] is true, that means there is a wall in the space.
	 *  else the space is free. Humans should never go into spaces that
	 *  have a wall.
	 */
	public static boolean walls[][];
	private static int width, height;
	public static int r;
	public static int c;
	static ArrayList<Human> humanList = new ArrayList<Human>();
	public static ArrayList<Zombie> zombieList = new ArrayList<Zombie>();
	static ArrayList<Zombie> tempZombie = new ArrayList<Zombie>();
	static boolean danceParty = false;

	//Zombie[] zombieArray = new Zombie[1000];

	/**
	 * Create a new City and fill it with buildings and people.
	 * @param w width of city
	 * @param h height of city
	 * @param numB number of buildings
	 * @param numP number of people
	 */
	public City(int w, int h, int numB, int numP) {
		width = w;
		height = h;
		walls = new boolean[w][h];

		randomBuildings(numB);
		populate(numP);
	}


	/**
	 * Generates numPeople random people distributed throughout the city.
	 * People must not be placed inside walls!
	 *
	 * //@param numPeople the number of people to generate
	 */

	public static void erase(int numPeople)
	{
		zombieList.clear();
		humanList.clear();
		tempZombie.clear();

		for(int i =0; i < walls.length;i++)
		{
			for(int j = 0; j< walls.length;j++)
			{
				walls[i][j] = false;
			}
		}

		randomBuildings(80);
		drawWalls();
		populate(numPeople);
	}
	public static void populate(int numPeople)
	{
		//Person test = new Person(50, 50);
		//humanList.add(test);

		int xLocation = 0;
		int yLocation = 0;
		walls[xLocation][yLocation] = true;
		for(int i = 0;i < numPeople;i++)
		{
			xLocation = 0;
			yLocation = 0;
			while(walls[xLocation][yLocation] == true)
			{
				xLocation = Helper.nextInt(200);
				yLocation = Helper.nextInt(200);
			}
			Human temp = new Human(xLocation, yLocation);
			humanList.add(temp);
		}

		int xLoc2 = 0;
		int yLoc2 = 0;
		for(int j = 0; j < 1;j++)
		{
			while(walls[xLoc2][yLoc2] == true)
			{
				xLoc2 = Helper.nextInt(200);
				yLoc2 = Helper.nextInt(200);
			}

			Zombie temp2 = new Zombie(xLoc2, yLoc2);
			zombieList.add(temp2);
		}
	}


	/**
	 * Generates a random set of numB buildings.
	 *
	 * @param numB the number of buildings to generate
	 */
	public static void randomBuildings(int numB) {
		/* Create buildings of a reasonable size for this map */
		int bldgMaxSize = width/6;
		int bldgMinSize = width/50;

		/* Produce a bunch of random rectangles and fill in the walls array */
		for(int i=0; i < numB; i++) {
			int tx, ty, tw, th;
			tx = Helper.nextInt(width);
			ty = Helper.nextInt(height);
			tw = Helper.nextInt(bldgMaxSize) + bldgMinSize;
			th = Helper.nextInt(bldgMaxSize) + bldgMinSize;

			for(int r = ty; r < ty + th; r++) {
				if(r >= height)
					continue;
				for(int c = tx; c < tx + tw; c++) {
					if(c >= width)
						break;
					walls[c][r] = true;
				}
			}
		}
	}

	public static void freeze()
	{
		for(Zombie z: zombieList)
		{
			z.whichWay = 10;
		}

		for(Human h: humanList)
		{
			h.whichWay = 10;
		}
	}

	/**
	 * Updates the state of the city for a time step.
	 */
	public void update()
	{

	}

	/**
	 * Draw the buildings and all humans.
	 */
	public void draw()
	{
		/* Clear the screen */
		ZombieSim.dp.clear(Color.black);

		//ZombieSim.dp.setPenColor(Color.BLUE);
		//ZombieSim.dp.drawDot(40, 40);
		int i = 0;
		for(Person p: humanList)
		{
			ZombieSim.dp.setPenColor(Color.WHITE);
			p.move(walls, 10);
			i++;
		}

		for(Person p2: zombieList)
		{
			if(danceParty == true)
			{
				ZombieSim.dp.setPenColor(Color.getHSBColor((float)(Math.random()*1), (float)1.0, (float)1.0));
			}

			else
			{
				ZombieSim.dp.setPenColor(Color.GREEN);
			}

			p2.move(walls, 5);
		}

		int looper = 0;
		Zombie[] zombieArray = new Zombie[1000];
		Human[] removeList = new Human[1000];
		for(Zombie test: zombieList)
		{
			for(Human test2: humanList)
			{
				//test.chase(test2);
				if(test.collidingWith(test2) == true)
				{
					/*
					Zombie temp3 = new Zombie(test2.xLoc, test2.yLoc);
					tempZombie.add(temp3);
					*/
					zombieArray[looper] = new Zombie(test2.xLoc, test2.yLoc);
					//test2.remove(walls);
					removeList[looper] = test2;
					looper++;
					System.out.println("COLLISION");
				}
			}
		}

		for(int m = 0; m < zombieArray.length; m++)
		{
			if(zombieArray[m] != null)
			{
				zombieList.add(zombieArray[m]);
			}
		}

		for(int n = 0; n < removeList.length; n++)
		{
			if(removeList[n] != null)
			{
				humanList.remove(removeList[n]);
			}
		}
		/*
		for(Person p3: tempZombie)
		{
			ZombieSim.dp.setPenColor(Color.GREEN);
			p3.move(walls);
		}
		*/



		drawWalls();
	}

	/**
	 * Draw the buildings.
	 * First set the color for drawing, then draw a dot at each space
	 * where there is a wall.
	 */
	public static void drawWalls() {
		ZombieSim.dp.setPenColor(Color.DARK_GRAY);
		for(r = 0; r < height; r++)
		{
			for(c = 0; c < width; c++)
			{
				if(walls[c][r])
				{
					ZombieSim.dp.drawDot(c, r);
				}
			}
		}
	}

}
