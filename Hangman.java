package hangmangameGUI;


import java.util.Random;

public class Hangman extends WordGame{

	//Invokes pickGameWord and then creates the clueWord by randomly replacing alphabets 
	//in the word with dashes until the dashCount is equal to or 
	//more than half the length of the word
	//
	@Override
	public void setupWords(){
		//enter code here
		pickGameWord();
		StringBuilder clueWordSB = new StringBuilder(gameWord); //initialize a clue word StringBuilder with gameWord. At the beginging, it is equal to the gameWord.
		int dashCount=0;	//How many dashes in a word
		int gamewordLength = gameWord.length(); //Length of the gameword
		Random random = new Random();
		while (dashCount < (gamewordLength+1)/2){ //Number of dashes should be equal to or larger than the half the length of the game word.
			int randomIndex = Math.abs(random.nextInt() % gamewordLength);//Random index
			char alphabet = clueWordSB.charAt(randomIndex); //Randomly select an alphabet.
			if (alphabet!='-'){
				int alphabetIndex;
				//Replace the all occurrences of the random alphabet into dashes
				while((alphabetIndex=clueWordSB.toString().indexOf(alphabet))!=-1){
					clueWordSB.setCharAt(alphabetIndex,'-');
					dashCount=countDashes(clueWordSB.toString());
				}
			}
		}
		clueWord=clueWordSB.toString().toLowerCase();
	}

	//dashCount() returns the number of dashes in the word 
	public int countDashes(String word) {
		//enter code here
		int dashCount = 0;
		char[] alphabets = word.toCharArray();
		for(int i=0; i<alphabets.length;i++){
			if(alphabets[i]=='-')
				dashCount++;
		}
		return dashCount;
	}
	
	@Override
	public int nextTry(String input) {
		//enter code here
		//Determine whether the word is already included in the clueword
		if(clueWord.indexOf(input)!=-1){
			message="Part of the clue!";
			return -1;
		}
		//Determine whether the word is entered by user previously
		else if(userInputs.indexOf(input)!=-1){
			message="You already entered that!";
			return -1;
		}
		else{
			//Determine whether the word is included in the game word but not in the clue word
			if(gameWord.indexOf(input)!=-1){
				StringBuilder clueWordSB = new StringBuilder(clueWord);
				char[] gameWordChar = gameWord.toCharArray();//Create a char array for the game word.
				////Replace dashes with 'input'
				for(int i=0; i<gameWordChar.length;i++){
					if (input.charAt(0)==gameWordChar[i]){
						clueWordSB.setCharAt(i, input.charAt(0));
					}
				}
				clueWord=clueWordSB.toString().toLowerCase();//Assign the new clue word to clueWord
				message="You got that right!";
				//if(won==true)
				//	message="Congratulations! You got it!";
				userInputs.append(input);
				hit++;
				trialCount++;
				//If there is no more dash in the clueWord, player wins.
				if(clueWord.indexOf("-")==-1){
					won=true;
				}
				return 1;
			}
			else {
				message = "Sorry! Got it wrong!";
				//if(trialCount==10)
				//	message = "Sorry! You missed it. It's " + gameWord;
				userInputs.append(input);
				miss++;
				trialCount++;
				return 0;
			}
		}
	}
	
	@Override
	public double calcScore() {
		//enter code here
		if(miss==0)
			score=hit;
		else
			score=hit/(double)miss; // Casting the int variables into double to avoid lost score

		return score;
	}
}

