import java.util.Random;

public class GuessingGame {
	private int winningNumber;
	private int guesses;
	private boolean victory;
	private int lastGuess;
	private Random rn;
	
	public GuessingGame() {
		guesses = 0;
		victory = false;
		rn = new Random();
	}

	public void newGame() {
		winningNumber = rn.nextInt(100);
		guesses = 0;
		victory = false;
	}

	public boolean guess(int guess) {
		guesses++;
		lastGuess = guess;
		if(guess == winningNumber)
			victory = true; 
		return victory;
	}

	public int getGuesses() {
		return guesses;
	}

	public boolean getVictory() {
		return victory;
	}

	public String getHighLow() {
		return (lastGuess > winningNumber) ? "lower" : "higher";
	}
}
