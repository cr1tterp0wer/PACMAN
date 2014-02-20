import java.util.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class PacMan extends GameObject
{
	// PacMan properties
	public int x = 100;
	public int y = 100;
	private int width = 50;
	private int height = 50;
	private int sAngle = 45;
	private int  fill = 270;
	private int DIAMETER = 75;
	
	// game ob. to keep in the screen
	private Game game;
	
	// incrementors to move through the screen
	private int xa = 0;
	private int ya = 0;
	
	// mouth movement
	int mM = 0;
	
    public PacMan(Game game)
    {
    	this.game = game;
    	this.boundsRectangle = new Rectangle(x,y,50,50);
    }
    
    // bullshit 
    
    int one=0 , FILL = 315;
    
    /**
     * This method basiclly makes pacmans maouth move
     * makes pacman move through the screen
     */
    //@Override
	public void update()
	{	
		// so it is adding 1 to sAngle only if it hits 0 ant it hits 360 too
		//if (sAngle > 0 && fill < 270)
		
		if (sAngle > one  && fill <= FILL)
		mM = 1;
		else if(sAngle <= one)
			mM = -1;
		
		// takes -1 to 45 and +2 to fill until 0 and 360
		sAngle -= mM;	
		// this keeps it 270;
		//fill += mM+mM;
		fill += 2*mM;
		
		if((x + xa + 300) < 0) xa = 1;
		if(x + xa + 320 > game.getWidth() - DIAMETER) xa = -1;
		if(y + ya + 200 < 0 ) ya = 1;
		if(y + ya + 230 > game.getHeight() - DIAMETER)ya = -1;
		
		//Righ 0 , 270 ,0
	    // up 90, 270, 90
	    // left 180, 270, 180
	    // down 270, 270, 270
		x += xa;
		y += ya;
	}
	
	//@Override
	public void render(Graphics g)
	{
		// body
		g.setColor(Color.yellow);
		g.fillArc(300+x, 200+y, width, height, sAngle, fill);
		
		/*	//eye
		g.setColor(Color.black);
		g.fillOval(x+325,y+225,5,5);
		*/
	}
	
	public void keyReleased(KeyEvent e )
	{
		xa = 0;
		ya = 0;
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_LEFT){xa = -1;;}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			xa = 1;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP){ya = -1;}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){ya = 1;}
	}
}