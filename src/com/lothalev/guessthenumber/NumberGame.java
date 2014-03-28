package com.lothalev.guessthenumber;

import java.util.Random;

public class NumberGame {
	public int currentMin, currentMax;
	private int numToGuess;
	public int tries;
	
	public NumberGame(int level){
		currentMax=getMaxFromLevel(level);
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
	public static int getMaxFromLevel(int level){
		switch(level){
		case 0:
			return 10;
		case 1:
			return 50;
		case 2:
			return 100;
		case 3:
			return 500;
		case 4:
			return 1000;
		case 5:
			return 10000;
		}
		return -1;
	}
}
