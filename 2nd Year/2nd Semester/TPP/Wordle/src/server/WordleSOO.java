package server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import commun.*;

/**
 * Class of the service operations object (SOO).
 *
 */
public class WordleSOO implements IWordle {
	// Constants
	static final int MAX_ATTEMPTS = 5;
	
	
	private int state;

	private int clientID;
	
	int numberAttempts = 0;
	// Area of data

	// List of possible words to guess
	// All words will be of size IWordle.WORD_SIZE
	static List<String> wordsGuess = initializeDictionary();
	
	String secretWord;

	// Constructor
	public WordleSOO(int id) throws DictionaryError {
		this.clientID = id;
		this.state = 0;
	}

	@Override
	public String obtainDifferences(String word) throws PA_Exception, WrongLength, WrongFormat {
		
		if(state != 1)
			throw new PA_Exception("This cannot be done");
		
		
		String wordUppercase = word.toUpperCase();
		
		if(word.length() != WORD_SIZE)
			throw new WrongLength(String.format("The guess must be %d letters long", WORD_SIZE));
		
		StringBuilder result = new StringBuilder();
		
		Map<Character, Integer> occurences = new HashMap<Character, Integer>();
		
		Map<Character, Integer> occurencesWell = new HashMap<Character, Integer>();
		
		for(int i = 0; i < word.length(); i++) {
			
			char currentChar = wordUppercase.charAt(i);
			
			if(occurences.containsKey(currentChar))
				occurences.put(currentChar, occurences.get(currentChar) + 1);
			else
				occurences.put(currentChar, 1);
			
			if(currentChar == secretWord.charAt(i)) 
				if(occurencesWell.containsKey(currentChar))
					occurencesWell.put(currentChar, occurencesWell.get(currentChar) + 1);
				else
					occurencesWell.put(currentChar, 1);
		}
		
		for(int i = 0; i < word.length(); i++) {
			
			char currentChar = wordUppercase.charAt(i);
			
			
			if(!occurences.containsKey(currentChar) || occurences.get(currentChar) == 0 || (occurencesWell.containsKey(currentChar) && occurencesWell.get(currentChar) == 0))
				
				result.append('X');
			
			else if(occurences.containsKey(currentChar) && !occurencesWell.containsKey(currentChar)) {
				
				result.append('C');
				
				occurences.put(currentChar, occurences.get(currentChar) - 1);
				
			} else if(occurencesWell.containsKey(currentChar) && currentChar != wordUppercase.charAt(i)) {  
				
				result.append('C');
				occurences.put(currentChar, occurences.get(currentChar) - 1);
		
			} else if(occurencesWell.containsKey(currentChar) && currentChar == wordUppercase.charAt(i)) {  
				
				result.append('=');
				occurences.put(currentChar, occurences.get(currentChar) - 1);
				occurencesWell.put(currentChar, occurencesWell.get(currentChar) - 1);
			}
			
			state = 2;
			
		}
		
		return result.toString();
<<<<<<< HEAD
=======
		
		// THIS OPERATION IS NOT COMPLETE.

		// HELP (STEPS) TO FOLLOW TO DETECT DIFFERENCES CORRECTLY
		// To report differences correctly, two passes, two loops, are necessary:

		// In the first loop, we count the number of occurrences of
		// each character in the secret word and the number of occurrences of each
		// character WELL PLACED in the proposed word
		// We will need these occurrences to correctly perform the second loop

		// In the second loop, we generate the string of differences:
		// Character in its place --> "="
		// Character does not exist --> "X"
		// Character exists and the number of occurrences up to now + Number of
		// occurrences well placed that come after
		// does not exceed the total number of occurrences in the word to be guessed -->
		// "C"
		// Character exists and the number of occurrences up to now + Number of
		// occurrences well placed that come after
		// EXCEEDS the total number of occurrences in the word to be guessed --> "X"

		// Two passes are necessary BECAUSE WELL PLACED occurrences MUST BE PRIORITIZED
		// over occurrences changed in position.

		// FIRST PASS: We count the occurrences...

		// TO BE COMPLETED

		// SECOND PASS: We generate the string of differences

		// TO BE COMPLETED
>>>>>>> 3f6b03c83fd2772c0b162f715262fcbf4e1c81c1
	}

	private static boolean validWord(String word) {
		return word.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+");
	}

	@Override
	public void restartGame() throws PA_Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newWord() throws PA_Exception, WrongLength {
		
		if(state != 0)
			throw new PA_Exception("Cannot be called");
		
		if(numberAttempts == MAX_ATTEMPTS)
			throw new PA_Exception("Already reached max number of attempts");
		
		numberAttempts++;
		Random rand = new Random();
		
		int secretWordIndex = rand.nextInt(0, wordsGuess.size());
		this.secretWord = wordsGuess.get(secretWordIndex);
		
		this.state = 1;
		
	}

	@Override
	public int endedGame(String word) throws PA_Exception, WrongLength, WrongFormat {
		if(word.length() != WORD_SIZE) {
			throw new WrongLength(String.format("The word must be %d characters long", WORD_SIZE));
		}
		if(!validWord(word))
			throw new WrongFormat("This word is not valid");
		
		if(word.equals(secretWord)) {
			state  = 3;
			return 1;
		} else {
			if(numberAttempts == MAX_ATTEMPTS) {
				state = 3;
				return 2;
			}
		}
		
		state = 1;
		return 0;
	}

	@Override
	public String requestWord() throws PA_Exception {
		if(state != 3)
			throw new PA_Exception("You cannot do this");
		return secretWord;
	}

	static private List<String> initializeDictionary() {
		String file_path = "src/main/resources/english-words.txt";
		String word;
		int word_len;
		List<String> word_dict;

		// Create the list of words to guess
		word_dict = new ArrayList<>(5000);

		// Open the dictionary file to store the words of size IWordle.WORD_SIZE
		// If the file cannot be opened or if the reading fails, the method will return
		// null
		try {
			Path path = Paths.get(file_path);
			BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);

			word = reader.readLine();

			while (word != null) {
				// Get the length of the word
				word_len = word.length();

				// Check that the word does not have strange characters and that it has the
				// appropriate size
				if (word_len == IWordle.WORD_SIZE && WordleSOO.validWord(word)) {
					// Get the word in uppercase and add it to the list
					word_dict.add(word.toUpperCase());
				}

				// Read the next word from the file
				word = reader.readLine();
			}

			// Close the file
			reader.close();
		} catch (FileNotFoundException e) {
			// Return null in order to indicate that the reading of the dictionary failed
			word_dict = null;
		} catch (IOException e) {
			// Return null in order to indicate that the reading of the dictionary failed
			word_dict = null;
		}

		return word_dict;
	}
}
