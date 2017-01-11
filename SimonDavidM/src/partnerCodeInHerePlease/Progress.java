package partnerCodeInHerePlease;

import gui.components.Components;
import simonDavidM.ProgressInterfaceDavid;

import java.awt.*;

/**
 * Created by dav1d on 1/9/17.
 */
public class Progress extends Components implements ProgressInterfaceDavid {
    private boolean over; // keep track of game state.
    private int size;
    private int roundNumber;
    private static int height = 100;
    private static int width = 200;
    public Progress() {
        super(0,0,width,height);
    }

    @Override
    public void gameOver() {
    	over = false;
    	update();
    }

    @Override
    public void setRound(int roundNumber) {
    	this.roundNumber = roundNumber;
    	update();
    }

    @Override
    public void setSequenceSize(int size) {
    	this.size = size;
    	update();
    }
    public void setOver(boolean o){ over = o;}
    public boolean getOver(){ return over;}

    @Override
    public void update(Graphics2D g) {
    	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		FontMetrics fm = g.getFontMetrics();	
		int fontSize = 12;
		g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
    	if(getOver()){ // if game is over:
    		g.setColor(Color.red);
			g.fillRect(0, 0, width, height);
			g.setColor(Color.black);
			g.drawString("Game over your lost", width/2-25, height/2);
        }
        else{ // if game is not over:
        	g.setColor(Color.yellow);
			g.fillRect(0, 0, width, height);
			g.setColor(Color.black);
			g.drawString("Round: "+roundNumber, width/2 - 50, height/2);
			g.drawString("Sequence length: "+size, width/2, height/2);	
        }
    }
}
