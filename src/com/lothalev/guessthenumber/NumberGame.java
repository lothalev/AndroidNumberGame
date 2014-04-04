package com.lothalev.guessthenumber;

import java.util.Random;

public class NumberGame {
	public class Score {
		private int level, tries, digitcounts[];
		
		public Score(int level, int tries, int[] digitcounts){
			this.level=level;
			this.tries=tries;
			this.digitcounts=digitcounts;
		}
		public int getLevel(){
			return level;
		}
		
		public int getTries(){
			return tries;
		}
		
		public int[] getCounts(){
			return digitcounts;
		}
	}

	private int numToGuess;
	private int level;
	public int tries;
	public int currentMin, currentMax;
	private int digitcounts[];
	
	public NumberGame(int level){
		this.level=level;
		currentMax=getMaxFromLevel(level);
		currentMin=0;
		for (int i=0;i<5;i++) {
			digitcounts[i]=0;
		}
		
		tries=0;
		Random r = new Random();
		numToGuess = r.nextInt(currentMax)+1;
	}
	
	public boolean Guess(int guess){
		tries++;
		digitcounts[(Integer.toString(guess).length())]++;
			
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
	
	public Score getHighScore() {
		return new Score(level, tries, digitcounts);
	}
}
