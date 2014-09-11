/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.*;
import java.util.ArrayList;

import acm.util.*;

public class HangmanLexicon {
	private static final String DICTIONARY_PATH = "ShorterLexicon.txt";
	
	public HangmanLexicon(){
		wordsList = readingToList(DICTIONARY_PATH);
	}

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return wordsList.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return wordsList.get(index);
	}
	
	/** Opens dictionary file. */
	private BufferedReader openFile(String file){
		BufferedReader rd;
		try{
			rd = new BufferedReader(new FileReader(file));
		} catch (IOException ex){
			throw new ErrorException(ex);
		}
		return rd;
		
	}
	
	/** Returns a list with all words from dictionary file */
	private ArrayList<String> readingToList(String filePath){
		ArrayList<String> wordsList = new ArrayList<String>();
		BufferedReader rd = openFile(filePath);
		try{
			while(true){
				String line = rd.readLine();
				if(line==null) break;
				wordsList.add(line);
			}
			rd.close();
		} catch (IOException ex){
			throw new ErrorException(ex);
		}
		return wordsList;
	}
	
	private ArrayList<String> wordsList;
}
