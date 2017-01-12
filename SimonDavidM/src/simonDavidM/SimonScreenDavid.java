package simonDavidM;

import java.awt.Color;
import java.util.ArrayList;

import gui.components.*;
import partnerCodeInHerePlease.ButtonD;
import partnerCodeInHerePlease.Move;
import partnerCodeInHerePlease.Progress;

public class SimonScreenDavid extends ClickableScreen implements Runnable {
	private ProgressInterfaceDavid progress; // what is the progress.
	private ArrayList<MoveInterfaceDavid> moves; // simons says?
	private int roundNum; // what round is it?
	private boolean acceptingInput; // input is valid?
	private TextLabel label; // text
	private int lastMove; // last number inputted.
	private int sequenceIdx;
	private ButtonInterfaceDavid[] validMoves; // possible moves.
	private int numOfButtons; // number of buttons on screen, same number as number of colors.
	private Color colors;
	public SimonScreenDavid(int width, int height) {
		super(width, height);
		Thread app = new Thread(this);
		app.start();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		
		label.setText("");
		nextRound();

	}

	public void nextRound() {
		acceptingInput = false;
		++roundNum; // new round = increase round.
		moves.add(randomMove()); // simon says new move.
		// update current progress(state).
		progress.setRound(roundNum);
		progress.setSequenceSize(moves.size());
		changeText("Simon's turn."); // notify the user to follow new sequence.
		label.setText(""); // reset screen message.
		playSequence(); // play the sequence of moves.
		changeText("Your turn!"); // new screen message to input user's guess.
		acceptingInput = true; // first phase is done --> accept input.
		sequenceIdx = 0; // reset the sequence index.
	}

	public void playSequence() {
		ButtonInterfaceDavid b = null;
		for(int i = 0; i < moves.size(); ++i){
			if(b != null) b.dim(); // turn off color.
			b = moves.get(i).getButton(); // get the button of the move.
			b.highlight();
			int sleepTime = (roundNum*roundNum) % (roundNum * 2); // higher the round, lower the sleep time.
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		b.dim();
	}


	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		// TODO Auto-generated method stub
		addButtons(viewObjects);
		progress = getProgress();
		label = new TextLabel(130,230,300,40,"Let's play Simon!");
		moves = new ArrayList<MoveInterfaceDavid>();
		//add 2 moves to start
		lastMove = -1;
		moves.add(randomMove());
		moves.add(randomMove());
		roundNum = 0;
		viewObjects.add(progress);
		viewObjects.add(label);

	}

	public MoveInterfaceDavid randomMove() {
		// TODO Auto-generated method stub
		
		int random = (int) (Math.random()*validMoves.length);
		while(random == lastMove){
			random = (int) (Math.random()*validMoves.length);
		}
		lastMove = random;
		return getMove(validMoves[random]);
		//
	}

	public MoveInterfaceDavid getMove(ButtonInterfaceDavid newMove) {
		// TODO Auto-generated method stub
		return new Move(newMove);
	}

	public ProgressInterfaceDavid getProgress() {
		// TODO Auto-generated method stub
		return new Progress();
	}
	public ButtonInterfaceDavid getAButton(){
		return new ButtonD();
	}
	public void addButtons(ArrayList<Visible> viewObjects) {
		// TODO Auto-generated method stub
		int numOfButtons = 6;
		Color[] colors= {Color.blue,Color.black, Color.gray, Color.ORANGE, Color.RED};
		for(int i = 0; i < numOfButtons; i++){
			ButtonInterfaceDavid b = getAButton();
			if(i< 0 || i > numOfButtons) {
				b.setColor(colors[i]);
				b.setX(10*(int)Math.cos(Math.PI/3*(i)));
				b.setY(10*(int)Math.sin(Math.PI/3*(i)));
				// add action for when the button is clicked.
				b.setAction(new Action() {
					@Override
					public void act() {
						if(acceptingInput){
							Thread blink = new Thread(new Runnable() {
								@Override
								public void run() {
									b.highlight(); // show color
									try {
										Thread.sleep(800);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									b.dim(); // hide color.
								}
							});
							blink.start(); // execute thread.
							if(moves.get(sequenceIdx).getButton() == b){
								++sequenceIdx;
								if(sequenceIdx == moves.size()){ // check if round is over.
									Thread nextRound = new Thread(SimonScreenDavid.this);
									nextRound.start(); // start next round.
								}
							}
							else progress.gameOver();
						}
					}
				});
				viewObjects.add(b); // add button to view object vector.
			}
			}
		
	}
	public void changeText(String s){
		label.setText(s);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void gameOver() {
		progress.gameOver();
	}

}
