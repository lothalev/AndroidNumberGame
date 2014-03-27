package com.lothalev.guessthenumber;

import java.util.Random;

public class NumberGame {
	public int currentMin, currentMax;
	public int numToGuess;
	
	public NumberGame(){
		currentMax=1000;
		currentMin=0;
		Random r = new Random();
		numToGuess = r.nextInt(currentMax);
	}
	
	public void Guess(int guess){
		if (guess>numToGuess)
			currentMax=guess;
		if (guess<numToGuess)
			currentMin=guess;
// TODO: sprawdzenie czy odpowiedz poprawna
		
	}
}
