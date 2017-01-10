package partnerCodeInHerePlease;


import gui.components.Action;
import gui.components.Components;
import simonDavidM.ButtonInterfaceDavid;

import java.awt.*;

/**
 * Created by dav1d on 1/9/17.
 */
public class Button extends Components implements ButtonInterfaceDavid {
    private boolean lit; // keep track whether button is on or off.

    public Button(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public boolean isHovered(int x, int y) {
        return false;
    }

    @Override
    public void act() {

    }

    @Override
    public void setColor(Color c) {

    }

    @Override
    public void setAction(Action action) {

    }

    @Override
    public void highlight() {

    }

    @Override
    public void dim() {

    }
    public void setLit(boolean l){ lit = l;}
    public boolean getLit(){ return lit;}

    @Override
    public void update(Graphics2D g) {
        if(getLit()){ // if button is on.

        }
        else{ // if button is off.

        }
    }
}
