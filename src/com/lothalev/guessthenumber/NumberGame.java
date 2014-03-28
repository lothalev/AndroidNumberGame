package com.lothalev.guessthenumber;

import java.util.Random;

import android.R.bool;

public class NumberGame {
	public int currentMin, currentMax;
	private int numToGuess;
	public int tries;
	
	public NumberGame(int max){
		currentMax=max;
		currentMin=0;
		tries=0;
		Random r = new Random();
		numToGuess = r.nextInt(currentMax)+1;
	}
	
	public boolean Guess(int guess){
		tries++;
		if (guess == numToGuess) return true;
		if (guess>numToGuess && guess<currentMax)
			currentMax=guess;
		if (guess<numToGuess && guess>currentMin)
			currentMin=guess;
		return false;
		
	}
}
