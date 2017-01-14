package simonDavidM;

import java.awt.Color;
import java.util.ArrayList;

import gui.components.Action;
import gui.components.ClickableScreen;
import gui.components.TextLabel;
import gui.components.Visible;
import partnerCodeInHerePlease.Button;
import partnerCodeInHerePlease.Move;
import partnerCodeInHerePlease.Progress;

public class SimonScreenDavid extends ClickableScreen implements Runnable {


	private ArrayList<MoveInterfaceDavid> moves;
	private TextLabel label;
	private ButtonInterfaceDavid[] buttons;
	private ProgressInterfaceDavid progress;
	private int roundNumber;
	private	boolean acceptingInput;
	private int sequenceIndex;
	private	int lastSelectedButton;
	public SimonScreenDavid(int width, int height) {
		
		super(width, height);
		Thread app = new Thread(this);
		app.start();
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		// TODO Auto-generated method stub
		addButtons(viewObjects);
		progress = getProgress();
		label = new TextLabel(130,230,300,40,"Let's play Simon!");
		moves = new ArrayList<MoveInterfaceDavid>();
		//add 2 moves to start
		lastSelectedButton = -1;
		moves.add(randomMove());
		moves.add(randomMove());
		roundNumber = 0;
		viewObjects.add(progress);
		viewObjects.add(label);

	}

	private MoveInterfaceDavid randomMove() {
		int select = (int) (Math.random()*buttons.length);
		while(select == lastSelectedButton){
			select = (int) (Math.random()*buttons.length);
		}
		lastSelectedButton = select;
		return new Move(buttons[select]);
		
		
		
	}
	
	private MoveInterfaceDavid getMove(ButtonInterfaceDavid b) {
		// TODO Auto-generated method stub
		return new Move(b);
	}

	/**
	Placeholder until partner finishes implementation of simonAlex.ProgressInterface
	*/
	private ProgressInterfaceDavid getProgress() {
		// TODO Auto-generated method stub
		return new Progress();
	}

	private void addButtons(ArrayList<Visible> v) {
		// TODO Auto-generated method stub
		int numberOfButtons = 6;
		Color[] colors = {Color.blue,Color.green,Color.yellow,Color.red, Color.MAGENTA, Color.PINK};
		buttons = new ButtonInterfaceDavid[numberOfButtons]; // initialize array.
		for(int i = 0; i < buttons.length; i++){
			ButtonInterfaceDavid b = new Button((i + 1) * 100, 400 ,50, 50, colors[i]);//getAButton(colors[i]);
			b.setAction(new Action(){
				public void act(){
					Thread blink = new Thread(new Runnable(){

						public void run(){
							b.highlight();
							
							try {
								Thread.sleep(800);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							b.dim();
						
						}
						
					});
					if(acceptingInput) blink.start();
					if(acceptingInput){
						//if the user's button matches sequence
						if(b==moves.get(sequenceIndex).getButton())
							sequenceIndex++;
						else{
							acceptingInput = false;
							progress.gameOver();
							return;
						}	
					}
					if(moves.size()==sequenceIndex){
						Thread nextRound = new Thread(SimonScreenDavid.this);
						nextRound.start();
						
					}
				}

				
			});
			b.dim();
			v.add(b); // add components to vector.
			buttons[i] = b; // add button to buttons array.
		}
	}
	private ButtonInterfaceDavid getAButton(Color c) {
		// TODO Auto-generated method stub
		return new Button(100, 100, 100, 100, c);
	}

	@Override
	public void run() {
		label.setText("");
		nextRound();
		
	}

	private void nextRound() {
		acceptingInput=false;
		roundNumber++;
		moves.add(randomMove());
		progress.setRound(roundNumber);
		progress.setSequenceSize(moves.size());
		changeText("Simon's turn!");
		label.setText("");
		playSequence();
		changeText("Your turn.");
		label.setText("");
		acceptingInput=true;
		sequenceIndex=0;
		
	}

	private void playSequence() {
		ButtonInterfaceDavid b=null;
		for(int i=0; i<moves.size();i++){
			if(b!=null) b.dim();
			
			b=moves.get(i).getButton();
			b.highlight();
			//int sleepTime=1000*(int)Math.exp(roundNumber);
			try {
				Thread.sleep(1000 - roundNumber);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		b.dim();
		
	}

	private void changeText(String s) {
		// TODO Auto-generated method stub
		label.setText(s);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}




