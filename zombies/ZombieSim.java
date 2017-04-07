package cs2113.zombies;

import cs2113.util.DotPanel;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.*;

import javax.swing.JFrame;

//Garrett McLaughlin

/*
 * You must add a way to represent humans.  When there is not a zombie apocalypse occurring, humans
 * should follow these simple rules:
 * 		if (1 in 10 chance):
 * 			turn to face a random direction (up/down/left/right)
 * 		Move in the current direction one space if not blocked by a wall
 *
 * We will add additional rules for dealing with sighting or running into zombies later.
 */

public class ZombieSim extends JFrame implements MouseListener, MouseMotionListener, KeyListener {

	private static final long serialVersionUID = -5176170979783243427L;

	/** The Dot Panel object you will draw to */
	protected static DotPanel dp;

	/* Define constants using static final variables */
	public static final int MAX_X = 200;
	public static final int MAX_Y = 200;
	private static final int DOT_SIZE = 4;
	private static final int NUM_HUMANS = 1000;
	private static final int NUM_BUILDINGS = 80;



	/*
	 * This fills the frame with a "DotPanel", a type of drawing canvas that
	 * allows you to easily draw squares to the screen.
	 */
	public ZombieSim()
	{
		this.setSize(MAX_X * DOT_SIZE, MAX_Y * DOT_SIZE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setTitle("Braaiinnnnnsss");

		/* Create and set the size of the panel */
		dp = new DotPanel(MAX_X, MAX_Y, DOT_SIZE);

		/* Add the panel to the frame */
		Container cPane = this.getContentPane();
		cPane.add(dp);
		cPane.addMouseListener(this);
		cPane.addMouseMotionListener(this);
		cPane.addKeyListener(this);
		//this.setFocusable(true);
		dp.addKeyListener(this);
		this.addKeyListener(this);

		/* Initialize the DotPanel canvas:
		 * You CANNOT draw to the panel BEFORE this code is called.
		 * You CANNOT add new widgets to the frame AFTER this is called.
		 */
		this.pack();
		dp.init();
		dp.clear();
		dp.setPenColor(Color.red);
		this.setVisible(true);

		/* Create our city */
		City world = new City(MAX_X, MAX_Y, NUM_BUILDINGS, NUM_HUMANS);


		/* This is the Run Loop (aka "simulation loop" or "game loop")
		 * It will loop forever, first updating the state of the world
		 * (e.g., having humans take a single step) and then it will
		 * draw the newly updated simulation. Since we don't want
		 * the simulation to run too fast for us to see, it will sleep
		 * after repainting the screen. Currently it sleeps for
		 * 33 milliseconds, so the program will update at about 30 frames
		 * per second.
		 */

		while(true)
		{
			// Run update rules for world and everything in it
			world.update();
			// Draw to screen and then refresh
			world.draw();
			dp.repaintAndSleep(33);

		}
	}

	public static void main(String[] args) {
		/* Create a new GUI window  */
		new ZombieSim();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		//System.out.println(e.getX()/DOT_SIZE +" "+ e.getY()/DOT_SIZE);
		Zombie temp5 = new Zombie(e.getX()/DOT_SIZE, e.getY()/DOT_SIZE);
		City.zombieList.add(temp5);
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			City.erase(NUM_HUMANS);
		}

		if(e.getKeyChar() == 'd')
		{
			City.danceParty = !(City.danceParty);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
