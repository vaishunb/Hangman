/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Color;

import acm.graphics.*;

public class HangmanCanvas2 extends GCanvas {
	
	HangmanCanvas2(int width, int height){
		this.width = width;
		this.height = height;
		startX=width/8;
		startY=height/6;
	}

/** Resets the display so that only the scaffold appears */
	public void reset() {
		loose = false;
		this.removeAll();
		incorrectGuesses = "";
		if(graphics==null) graphics = new GImage("main.jpg");
		graphics.setImage("main.jpg");
		graphics.setSize(width/2, height);
		add(graphics);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		if(actualWord!=null) remove(actualWord);
		actualWord = new GLabel(word, startX-40, startY+SCAFFOLD_HEIGHT+70);
		actualWord.setFont("Mountains of Christmas-bold-40");
		actualWord.setColor(Color.white);
		if(!loose) add(actualWord);
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
		incorrectGuessesLabel = new GLabel(incorrectGuesses, actualWord.getX(), actualWord.getY()+60);
		incorrectGuessesLabel.setColor(Color.decode("#1c1d5c"));
		incorrectGuessesLabel.setFont("Mountains of Christmas-bold-30");
		add(incorrectGuessesLabel);
		
	}
	
	public void drawHangman(int guessesLeft){
		switch (guessesLeft){
		   case 0: {
			   drawRightLeg();
			   break;
		   }
		   case 1: {
			   drawLeftLeg();
			   break;
		   }
		   case 2: {
			   drawRightArm();
			   break;
		   }
		   case 3: {
			   drawLeftArm();
			   break;
		   }
		   case 4: {
			   drawBody();
			   break;
		   }
		   case 5: {
			   drawHead();
			   break;
		   }
		   default: return;
		}
	}
	
	private void drawHead(){
		graphics.setImage("1.jpg");
		graphics.setSize(width/2, height);
	}
	private void drawBody(){
		graphics.setImage("2.jpg");
		graphics.setSize(width/2, height);
	}
	private void drawLeftArm(){
		graphics.setImage("3.jpg");
		graphics.setSize(width/2, height);
	}
	private void drawRightArm(){
		graphics.setImage("4.jpg");
		graphics.setSize(width/2, height);
	}
	private void drawLeftLeg(){
		graphics.setImage("5.jpg");
		graphics.setSize(width/2, height);
	}
	private void drawRightLeg(){
		graphics.setImage("6.jpg");
		graphics.setSize(width/2, height);
		looseMessage();
	}
	
	private void looseMessage(){
		remove(incorrectGuessesLabel);
		remove(actualWord);
		GLabel youLoose = new GLabel("You loose...");
		youLoose.setFont("Mountains of Christmas-70");
		youLoose.setColor(Color.white);
		youLoose.setLocation(width/4-youLoose.getWidth()/2, height-height/6);
		add(youLoose);
		loose = true;
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 300;
	
	private int width, height, startX, startY;
	private GImage graphics;
	private GLabel actualWord, incorrectGuessesLabel;
	private String incorrectGuesses = "";
	private boolean loose;

}
