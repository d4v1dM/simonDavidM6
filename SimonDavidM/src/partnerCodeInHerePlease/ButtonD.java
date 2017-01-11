package partnerCodeInHerePlease;


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
    public ButtonD() {
        super(0, 0, 50, 50);
        lit=false;
        update();
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
    	this.color =color;
    	update();
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
    public void setLit(boolean l){ lit = l;}
  boolean getLit(){ return lit;}

    @Override
    public void update(Graphics2D g) {
    	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	if(getLit()){ // if button is on.
    		g.setColor(Color.lightGray);
    		g.fillOval(0, 0, getWidth(), getHeight());
        }
        else{ // if button is off.
        	g.setColor(color);
			g.fillOval(0, 0, getWidth(), getHeight());
        }
    }
}
