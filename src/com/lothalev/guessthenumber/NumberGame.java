package com.lothalev.guessthenumber;

import java.util.Random;

import android.R.bool;

public class NumberGame {
	public int currentMin, currentMax;
	public int numToGuess;
	public int tries;
	
	public NumberGame(){
		currentMax=1000;
		currentMin=0;
		tries=0;
		Random r = new Random();
		numToGuess = r.nextInt(currentMax);
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
