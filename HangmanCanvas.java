/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Color;
import java.awt.Font;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	
	HangmanCanvas(int width, int height){
		startX=width/8;
		startY=height/6;
		hangmanX = startX+BEAM_LENGTH;
		hangmanY = startY+ROPE_LENGTH;
	}

/** Resets the display so that only the scaffold appears */
	public void reset() {
		this.removeAll();
		incorrectGuesses = "";
		add(new GLine(startX, startY, startX, startY+SCAFFOLD_HEIGHT)); //Scaffold
		add(new GLine(startX, startY, startX+BEAM_LENGTH, startY)); //Beam
		add(new GLine(startX+BEAM_LENGTH, startY, startX+BEAM_LENGTH, startY+ROPE_LENGTH)); //Rope
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		if(actualWord!=null) remove(actualWord);
		actualWord = new GLabel(word, startX-40, startY+SCAFFOLD_HEIGHT+70);
		actualWord.setFont("Serif-30");
		add(actualWord);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		if(incorrectGuessesLabel!=null) remove(incorrectGuessesLabel);
		incorrectGuesses = incorrectGuesses+letter;
		incorrectGuessesLabel = new GLabel(incorrectGuesses, actualWord.getX(), actualWord.getY()+30);
		incorrectGuessesLabel.setColor(Color.GRAY);
		incorrectGuessesLabel.setFont("Serif-bold-12");
		add(incorrectGuessesLabel);
		
	}
	
	public void drawHangman(int guessesLeft){
		switch (guessesLeft){
		   case 0: drawRightFoot();
		   case 1: drawRightLeg();
		   case 2: drawLeftFoot();
		   case 3: drawLeftLeg();
		   case 4: drawRightArm();
		   case 5: drawLeftArm();
		   case 6: drawBody();
		   case 7: drawHead();
		   default: return;
		}
	}
	
	private void drawHead(){
		add(new GOval(hangmanX-HEAD_RADIUS, hangmanY, HEAD_RADIUS*2, HEAD_RADIUS*2));
	}
	private void drawBody(){
		add(new GLine(hangmanX, hangmanY+HEAD_RADIUS*2, hangmanX, hangmanY+HEAD_RADIUS*2+BODY_LENGTH));
	}
	private void drawLeftArm(){
		add(new GLine(hangmanX, hangmanY+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD, hangmanX-ARM_OFFSET, hangmanY+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD+ARM_LENGTH));
	}
	private void drawRightArm(){
		add(new GLine(hangmanX, hangmanY+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD, hangmanX+ARM_OFFSET, hangmanY+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD+ARM_LENGTH));
	}
	private void drawLeftLeg(){
		add(new GLine(hangmanX, hangmanY+HEAD_RADIUS*2+BODY_LENGTH, hangmanX-LEG_OFFSET, hangmanY+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH));
	}
	private void drawRightLeg(){
		add(new GLine(hangmanX, hangmanY+HEAD_RADIUS*2+BODY_LENGTH, hangmanX+LEG_OFFSET, hangmanY+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH));
	}
	private void drawLeftFoot(){
		add(new GLine(hangmanX-LEG_OFFSET, hangmanY+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH, hangmanX-LEG_OFFSET-FOOT_LENGTH, hangmanY+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH-FOOT_LENGTH));
	}
	private void drawRightFoot(){
		add(new GLine(hangmanX+LEG_OFFSET, hangmanY+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH, hangmanX+LEG_OFFSET+FOOT_LENGTH, hangmanY+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH-FOOT_LENGTH));
		drawFace();
	}
	private void drawFace(){
		GImage face = new GImage("face.gif", hangmanX-HEAD_RADIUS-5, hangmanY);
		face.setSize(HEAD_RADIUS*2+15, HEAD_RADIUS*2);
		add(face);
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 300;
	private static final int BEAM_LENGTH = 120;
	private static final int ROPE_LENGTH = 15;
	private static final int HEAD_RADIUS = 30;
	private static final int BODY_LENGTH = 120;
	private static final int ARM_OFFSET_FROM_HEAD = 23;
	private static final int ARM_LENGTH = 60;
	private static final int ARM_OFFSET = 20;
	private static final int LEG_OFFSET = 20;
//	private static final int LOWER_ARM_LENGTH = 37;
//	private static final int HIP_WIDTH = 30;
	private static final int LEG_LENGTH = 90;
	private static final int FOOT_LENGTH = 15;
	
	private int startX, startY;
	private int hangmanX, hangmanY;
	private GLabel actualWord, incorrectGuessesLabel;
	private String incorrectGuesses = "";

}
