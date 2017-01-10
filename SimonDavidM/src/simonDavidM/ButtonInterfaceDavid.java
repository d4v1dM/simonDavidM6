package simonDavidM;

import gui.components.Action;
import gui.components.Clickable;

import java.awt.*;

public interface ButtonInterfaceDavid extends Clickable {
    // interface methods are by default public.

    void setY(int y);
    void setX(int x);
    void setColor(Color c);
    void setAction(Action action);

    void highlight();

    void dim();
}
