package simonDavidM;

import gui.components.Action;
import gui.components.Clickable;

import java.awt.Color;

public interface ButtonInterfaceDavid extends Clickable {
	
	

	void setColor(Color color);

	Color getColor();

	void setHColor(Color color);

	Color getHColor();

	void setAction(Action action);
	
	void highlight();
	
	void dim();

	boolean isLit();

	

	


}
