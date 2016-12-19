package hangmangameGUI;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Random;

public abstract class WordGame {
	public String[] dictionaryWords;		//stores words read from the dictionary
	public String gameWord;  				//word picked up from the dictionary for the puzzle 
	public StringBuilder userInputs = new StringBuilder(); //stores all guesses entered by the user
	public String message;					//message to be printed on console after each user interaction
	public static final int MAX_TRIALS = 10; 
	public int trialCount=0;				//incremented everytime user enters a valid guess
	public boolean won = false;			//set to true when user input matches the gameWord
	public String clueWord;				//clue shown to the user on console
	public double score;					//updated by calcScore() 
	public int hit; //number of correct guesses made by player
	public int miss; //number of wrong guesses made by player

	WordGame() {
		dictionaryWords = readFile();
		setupWords();
	}

	//readfile() opens the file and reads it into StringBuffer
	//returns an array of String by splitting the words on new line
	public String[] readFile() {
		//enter code here
		//enter your code here
		StringBuilder words = new StringBuilder();
		try{
			URL url = getClass().getResource("dictionary.txt");
			File file = new File(url.getPath());
			BufferedReader br = new BufferedReader(new FileReader(file));
			String word;
			int i=1;
			while ((word = br.readLine())!=null){
				//Because there is an extra line after each word, so we need to skip those empty lines.
				if(!word.equals("")){
					words.append(word);
					words.append("\n");
				}
			}
		}catch (Exception ioe){
			System.out.println(ioe.getMessage());
			ioe.printStackTrace();
		}finally {
			dictionaryWords=words.toString().split("\n");
			return dictionaryWords;
		}
	}

	//pickWord() picks a word randomly from within the dictionaryWords array
	//It returns the word that has at least 4 or more characters in it.
	public void pickGameWord(){
		//enter code here
		Random random = new Random();
		boolean notLessThanFour = false;
		while (!notLessThanFour){
			gameWord = dictionaryWords[Math.abs(random.nextInt() % dictionaryWords.length)]; //use the random number to generate the game word. Math.abs() makes sure the random number is positive.
			if(gameWord.length()>=4){
				notLessThanFour=true;
			}
		}
	}


	public abstract int nextTry(String input);

	public abstract double calcScore();

	public abstract void setupWords();

}
