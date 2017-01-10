package partnerCodeInHerePlease;

import gui.components.Components;
import simonDavidM.ProgressInterfaceDavid;

import java.awt.*;

/**
 * Created by dav1d on 1/9/17.
 */
public class Progress extends Components implements ProgressInterfaceDavid {
    private boolean over; // keep track of game state.
    public Progress(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void gameOver() {

    }

    @Override
    public void setRound(int roundNum) {

    }

    @Override
    public void setSequenceSize(int size) {

    }
    public void setOver(boolean o){ over = o;}
    public boolean getOver(){ return over;}

    @Override
    public void update(Graphics2D g) {
        if(getOver()){ // if game is over:

        }
        else{ // if game is not over:

        }
    }
}
