package partnerCodeInHerePlease;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import gui.components.Action;
import gui.components.Components;
import simonDavidM.ButtonInterfaceDavid;

import java.awt.*;

/**
 * Created by dav1d on 1/9/17.
 */
public class ButtonD extends Components implements ButtonInterfaceDavid {
    private boolean lit; // keep track whether button is on or off.
    private Color color;
    private Action action;

    public ButtonD(int i, int j) {
        super(i, j, 50, 50);
        
    }
  
    @Override
    public boolean isHovered(int x, int y) {
    	if(x>getX()&&x<(getX()+getWidth())&&y>getY()&&y<(getY()+getHeight()))
		return true;
		return false;
    }

    @Override
    public void act() {
    	action.act();
    	
    }

    @Override
    public void setColor(Color c) {
    	this.color = c;
    	
    }

    @Override
    public void setAction(Action action) {
    	this.action = action;
    	update();
    }

    @Override
    public void highlight() {
    		
    	lit = true;
    	update();
    }

    @Override
    public void dim() {
    
    	lit=false;
    	update();
    }
  

    @Override
    public void update(Graphics2D g) {
    	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	if(lit){ // if button is on.
    		g.setColor(this.color);
    		
        }
        else{ // if button is off.
        	g.setColor(Color.lightGray);
    		g.fillOval(0, 0, 60, 60);
    		g.setColor(Color.black);
    		g.drawOval(0, 0, 59, 59);
        }
    }

	@Override
	public Color getColor() {
		return this.color;
	}

	
}
