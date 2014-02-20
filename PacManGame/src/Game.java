import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.RenderingHints;
import java.util.ArrayList;

public class Game extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static int SCREEN_WIDTH = 800;
	private static int SCREEN_HEIGHT = 600;
	
	// Ball array to draw in the screen
	private ArrayList<Ball> ballList;
	
	
	GameObject go;
	
	
	// PacMan and Ghost Objects.
	private PacMan pacman = new PacMan(this);
	private Ghost ghost = new Ghost(this);
	
	/*
	 * Constructor has an anonimous class in it
	 * for a keylistener
	 */
	
	public Game()
	{
		addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent e)
			{
				pacman.keyPressed(e);
			}
			public void keyReleased(KeyEvent e)
			{
				pacman.keyReleased(e);
			}
			public void keyTyped(KeyEvent arg0){}
		}
		);
		setFocusable(true);
		
	   init();	
		
	}
	
	private void init()
	{
		pacman.init();
		ghost.init();
		ballList = new ArrayList();
		int y=0,x=20;
		
		//INIT BALLS HERE
		for(int i=0;i<50; i++)
		{
			ballList.add(new Ball(x, y, Color.black));
			x+=100;
			
			if (x>= SCREEN_WIDTH){
				x=20;
				y+=100;
			}
			
		}
		
	}
	
	
	/*
	 * Game updates all objects 
	 * for animation
	 */
	private void update()
	{
		ghost.Update();
		pacman.update();
		
		//TO-DO UPDATE BALL COLLISION HERE
		for(int i=0;i<ballList.size(); i++)
		{
			
		  if(pacman.collisionCheck(ballList.get(i).boundsRectangle))
		  {
			  ballList.remove(i);
			  System.out.println(pacman.x);
		  }
		  
		  System.out.println(pacman.x);
	  }
	}
	
	/**
	 * Rendering all the objects to screen
	 * the pacman
	 * the ghost
	 * the pills
	 */
	public void paint(Graphics g)
	{	
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		//smooth graphics
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		
		for(int i=0;i<ballList.size(); i++)
		{
			ballList.get(i).render(g2d);
		}
		// Draw pacman and the ghost
		ghost.render(g2d);
		pacman.render(g2d);
		
	
		
	}
	
	/**
	 * This method is for the game over
	 * When the ghost collides with pacman 
	 * then it will run
	public void GameOver()
	{
		JOptionPane.showMessageDialog(this, "Game Over");
		System.exit(ABORT);
	}
	*/
	
	// The main method and the game loop here
	public static void main(String[] args) throws InterruptedException
	{
		JFrame frame = new JFrame("PACMAN");
		Game game = new Game();
		frame.add(game);
		frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		// Simplest game loop
		// Will change it later though
		// for a Variable timeStep
		// http://www.java-gaming.org/index.php?topic=24220.0
		while(true)
		{
			game.update();
			game.repaint();
			Thread.sleep(10);
		}
	}	
}