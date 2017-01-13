package simonDavidM;

import java.awt.Color;
import java.util.ArrayList;


import gui.components.Action;
import gui.components.ClickableScreen;
import gui.components.TextArea;
import gui.components.TextLabel;
import gui.components.Visible;
import partnerCodeInHerePlease.ButtonD;
import partnerCodeInHerePlease.Move;
import partnerCodeInHerePlease.Progress;

public class SimonScreenDavid extends ClickableScreen implements Runnable {
	private ProgressInterfaceDavid progress; // what is the progress.
	private boolean gameOver;
	private ArrayList<MoveInterfaceDavid> moves; // simons says?
	private int roundNum; // what round is it?
	private boolean acceptingInput; // input is valid?
	private TextLabel label; // text
	private int lastMove; // last number inputted.
	private int sequenceIdx;
	private ButtonInterfaceDavid[] validMoves; // possible moves.
	
	public SimonScreenDavid(int width, int height) {
		super(width, height);
		roundNum = 0;
		lastMove = -1;
		Thread app = new Thread(this);
		acceptingInput = false;
		app.start();
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		
		acceptingInput = false;
		moves.add(getMove()); // simon says new move.
		++roundNum;
		// update current progress(state).
		progress.updateRound(roundNum,moves.size());
		changeText("My move"); // notify the user to follow new sequence.
		label.setText(""); // reset screen message.
		playSequence(); // play the sequence of moves.
		changeText("Your turn!"); // new screen message to input user's guess.
		sequenceIdx = 0;
		label.setText("");
		acceptingInput = true;

	}

	

	public void playSequence() {
		ButtonInterfaceDavid b = null;
		for(int i = 0; i < moves.size(); i++){
			if(b != null) b.dim(); // turn off color.
			b = moves.get(i).getButton(); // get the button of the move.
			b.highlight();
			// higher the round, lower the sleep time.
			try {
				Thread.sleep((roundNum*roundNum) % (roundNum * 2));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		b.dim();
	}


	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		// TODO Auto-generated method stub
		Color[] colors = {Color.red,Color.yellow,Color.blue,Color.orange,Color.green};
		validMoves = new ButtonInterfaceDavid[colors.length];
		label = new TextLabel(130,230,300,40,"Let's play Simon!");
		progress = getProgress();
		for(int i=0;i<validMoves.length;i++){
			validMoves[i] = getAButton(10,50+(60*i));
			ButtonInterfaceDavid b = validMoves[i];
			b.setColor(colors[i]);
			b.setAction(new Action() {
				@Override
				public void act() {
					if(acceptingInput){
						Thread blinks = new Thread(new Runnable() {
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
						blinks.start();
		}
		
		if(sequenceIdx<moves.size()-1){
			if(b.getColor() == moves.get(sequenceIdx).getButton().getColor()){
				sequenceIdx++;
			}
			else{
				progress.setgameOver();
				 acceptingInput = false;
				System.out.println("game over");
			}
		}
		else{
			Thread Game = new Thread(SimonScreenDavid.this);
			Game.start();
					}
				}
			}
		);
		moves = new ArrayList<MoveInterfaceDavid>();
		moves.add(getMove());
		moves.add(getMove());
		
		viewObjects.add(progress);
		viewObjects.add(label);
		}

	}

	

	public MoveInterfaceDavid getMove() {
		int random = (int)(Math.random()*validMoves.length);
		while(random == lastMove){
			random = (int)(Math.random()*validMoves.length);
		}
		lastMove = random;
		return new Move(validMoves[random]);
	}

	public ProgressInterfaceDavid getProgress() {
		// TODO Auto-generated method stub
		return new Progress();
	}
	public ButtonInterfaceDavid getAButton(int i, int j ){
		return new ButtonD(i,j);
	}
	
	public void changeText(String s){
		label.setText(s);
		try {
			label.setText(s);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void blinkSequence() {
		for(MoveInterfaceDavid m:moves){
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			m.getButton().highlight();
			try {
				Thread.sleep((int)(1000*(1.0/roundNum)));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			m.getButton().dim();
		}
	}
	

}
