package partnerCodeInHerePlease;

import gui.components.Components;
import simonDavidM.ProgressInterfaceDavid;

import java.awt.*;

/**
 * Created by dav1d on 1/9/17.
 */
public class Progress extends Components implements ProgressInterfaceDavid {
    
    private int size;
    private boolean gameOver;
    private int roundNumber;
    private static int height = 100;
    private static int width = 200;
    public Progress() {
        super(200,60,width,height);
    }

    
    
    

  
  

    @Override
    public void update(Graphics2D g) {
    	g = clear();
    	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int fontSize = 12;
		g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
    	if(gameOver){ // if game is over:
    		g.setColor(Color.red);
			g.fillRect(0, 0, width, 100);
			g.setColor(Color.black);
			g.drawRect(0, 0, 199, 99);
			g.drawString("Game over your lost", width/2-25, height/2);
			g.drawString("Round: "+roundNumber,5,55);
			g.drawString("Current Sequence Length: "+size,5,75);
        }
        else{ // if game is not over:
        	g.setColor(Color.yellow);
			g.fillRect(0, 0, width, height);
			g.setColor(Color.black);
			g.drawString("Round: "+roundNumber, 5, 35);
			g.drawString("Sequence length: "+size,5,55);	
        }
    }

	@Override
	public void updateRound(int roundNum, int i) {
		roundNumber = roundNum;
		size = i;
		update();
		
	}

	@Override
	public void setgameOver() {
		gameOver = true;
    	update();
		
	}

	
}
