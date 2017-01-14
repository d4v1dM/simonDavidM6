package partnerCodeInHerePlease;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import gui.components.Components;
import simonDavidM.ProgressInterfaceDavid;

import java.awt.*;

/**
 * Created by dav1d on 1/9/17.
 */
public class Progress extends Components implements ProgressInterfaceDavid {
    private BufferedImage image;
    private int sequenceSize, round;
    private boolean over;
    public Progress(int x, int y, int w, int h) {
        super(x, y, w, h);
    }
    public Progress() {
        super(300, 75, 150, 150);
    }

    @Override
    public void update(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        FontMetrics fm = g.getFontMetrics();
        if(over){
            g.setColor(new Color(220,55,90));
            g.fillRect(0, 0, 150 - 1, 150);
            g.setColor(Color.BLACK);
            String go = "GAME OVER!";
            g.drawString(go, (150 - fm.stringWidth(go))/2, 20);
            g.drawString(("Final Sequence: " + sequenceSize), (150 - fm.stringWidth(String.valueOf("Final sequence: " + sequenceSize)))/2, 40);

        }else{
            g.setColor(new Color(20,55,30));
            g.fillRect(0, 0, 150, 150);
            g.setColor(Color.black);
            g.drawRect(0, 0, 150-1, 150-1);
            g.setColor(Color.white);
            if(String.valueOf(round) !=null && String.valueOf(sequenceSize) != null){

                g.drawString("Round: " + round, (150 - fm.stringWidth("Round: " + round))/2, 20);
                g.drawString("Sequence: " + sequenceSize, (150 - fm.stringWidth("Sequence: " + sequenceSize))/2, 40);
            }
        }
    }


    @Override
    public void gameOver() { // game is done
        over = true;
        update();
    }

    @Override
    public void setRound(int roundNumber) {
        round = roundNumber;
        update();
    }

    @Override
    public void setSequenceSize(int size) {
        sequenceSize = size;
        update();
    }
}
