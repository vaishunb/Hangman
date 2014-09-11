/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Hangman extends ConsoleProgram {
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 550;
	private static final int NUM_OF_GUESSES = 6;
	
	
	
	public static void main(String[] args){
		new Hangman().start();
	}
	
	
	public void init() { 
		 resize(WIDTH, HEIGHT);
		 canvas = new HangmanCanvas2(WIDTH, HEIGHT); 
		 add(canvas); 
		 canvas.reset();
	} 


    public void run() {
    	initialization();
    	intro();
		gameProcess();
	}
    
    
    
    /**Shows welcome*/
    private void intro(){
    	println("Welcome to hangman!");
    }
    
    
    /**Initialize main components of the application*/
    private void initialization(){
    	dictionary = new HangmanLexicon();
    	genWord();
    	guessesLeft = NUM_OF_GUESSES;
    	lettersGuessed = new char[word.length()];
    }
    
    /**Restarts the game after ending */
    private void restart(){
		println("Press any key to start again.");
		readLine();
    	canvas.reset();
    	initialization();
    	gameProcess();
    }
    
    /**Shows information about guessed letters and guesses left*/
    private void infoMessage(String actualWord, int guessesLeft){
    	println("The word now looks like this: "+actualWord);
    	if (guessesLeft>1){
    		println("You have "+guessesLeft+" guesses left.");
    	} else if(guessesLeft==1){
    		println("You have only one guess left.");
    	} else{
    		looseMessage();
    	}
    }
    
    
    /**Informs when user looses and restarts the game*/
    private void looseMessage(){
    	println("You are completly hung.\nThe word was: "+word+"\nYou lose.");
		restart();
    }
    
    /**Informs when user wins and restarts the game*/
    private void winMessage(){
    	println("You guessed the word: "+word+"\nYou win.");
		restart();
    }
    
    /**Generates a new word to guess*/  
	private void genWord() {
		int temp;
		Random rand = new Random();
		if (numbersToChose == null) {
			numbersToChose = new ArrayList<Integer>();
			for (int i = 0; i < dictionary.getWordCount(); i++) {
				numbersToChose.add(i);
			}
		}
		if(numbersToChose.isEmpty()){
			readLine("Dictionary is exhausted. Start again?");
			for (int i = 0; i < dictionary.getWordCount(); i++) {
				numbersToChose.add(i);
			}
		}
		temp = rand.nextInt(numbersToChose.size());
		word = dictionary.getWord(numbersToChose.get(temp));
		numbersToChose.remove(temp);
    }
    
    
    /**Returns covert word*/
    private String wordHidden(){
    	char[] actualLetters = new char[word.length()];
    	for(int i=0; i<word.length(); i++){
    		for(int k=0; k<word.length(); k++){
    			if(word.charAt(i)==lettersGuessed[k]){
    				actualLetters[i]=word.charAt(i);
    				break;
    			}
    			else 
    				actualLetters[i]='-';
    		}
    	}
    	return new String (actualLetters);
    }
    
    /**Get a character from user and check the input*/
    private char readLetter(){
    	char temp=0;
    	while (  !(  (temp>='A' && temp<='Z' ) || ( temp>='a' && temp<='z' ))  ) {
    		
    		String str = readLine();
    		
    		if (str.length()==1){
        		temp = str.charAt(0);
    		}
    		else println("Enter one character");
    		
    		if(!(  (temp>='A' && temp<='Z' ) || ( temp>='a' && temp<='z' )) ){
    			println("Enter character from A to Z");
    		}
    	}
    	
    	return Character.toUpperCase(temp);
    }
    
    /**Checks if the letter is right*/
    private boolean isGuessed(char letter){
    	for (int i=0; i<word.length(); i++){
    		if(word.charAt(i)==letter) return true;
    	}
    	return false;
    }
    
    
    /**runs all game methods*/
    private void gameProcess(){
    	int guessedLettersCounter=0;
    	int uniques = uniqueChars(word);
    	
    	while(true){
    		canvas.displayWord(wordHidden());
    		infoMessage(wordHidden(), guessesLeft);
    		char letter = readLetter();
    		
    		if(isGuessed(letter)){
    			println("The guess is correct");
    			
    			for (int i=0; i<uniqueChars(word); i++){
    				if (letter==lettersGuessed[i]){
    					print("... But you've already choosen this letter\n");
    					break;
    				} else if(i==uniqueChars(word)-1){
    	    	    	lettersGuessed[guessedLettersCounter]=letter;
    	    			guessedLettersCounter++;
    				}
    			}
    			
    			
    		} else{
    			println("There are no "+letter+"'s in the word");
    			canvas.noteIncorrectGuess(letter);
    			guessesLeft--;
    			canvas.drawHangman(guessesLeft);
    		}
    		
    		if(uniques==guessedLettersCounter){
    			canvas.displayWord(word);
    			winMessage();
    			break;
    		}
    	}
    }
    
    /**Counts unique characters in string */
    private int uniqueChars(String word){
    	boolean isUnique = false;
    	int uniqueCharsCounter=0;
    	ArrayList<Character> list = new ArrayList<Character>();
    	list.add('0');
    	
    	for (int i=0; i<word.length(); i++){
    		for (int k=0; k<list.size(); k++){
    			if(word.charAt(i)==list.get(k)){
    				isUnique = false;
    				break;
    			}else isUnique = true;
    		}
    		if(isUnique){
    			list.add(word.charAt(i));
    			uniqueCharsCounter++;
    		}
    	}
    	
    	return uniqueCharsCounter;
    }
    
    /** Instance variables*/
    private String word; //A new word
    private char[] lettersGuessed; //Guessed letters to fill hidden word
    private int guessesLeft; //How many guesses left
    private String[] chosenWords; //Words which have already been in the game
    private int chosenWordsCounter; //Counter for the array above
    private HangmanLexicon dictionary; //Dictionary to get words from
    private HangmanCanvas2 canvas;
    private ArrayList<Integer> numbersToChose;

}
