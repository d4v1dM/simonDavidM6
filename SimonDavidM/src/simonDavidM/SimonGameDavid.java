package simonDavidM;

import gui.GUIApplication;
import gui.Screen;

public class SimonGameDavid extends GUIApplication {

	
	
	public SimonGameDavid(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initScreen() {
		SimonScreenDavid ssd = new SimonScreenDavid(getWidth(), getHeight());
		setScreen(ssd);
	}
	public static void main(String[] args){
		SimonGameDavid game = new SimonGameDavid(800,500);
		Thread app = new Thread(game);
		app.start();
	}

}
 	