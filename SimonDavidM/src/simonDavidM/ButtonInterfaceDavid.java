package simonDavidM;

import gui.components.Action;
import gui.components.Clickable;

import java.awt.Color;

public interface ButtonInterfaceDavid extends Clickable {
    // interface methods are by default public.

 
    public void setColor(Color c);
    
    public Color getColor();
    
    public void setAction(Action action);

    public void highlight();

    public void dim();
}
