package commun;
import server.*;

/**
 * Interface for service operations.
 */
public interface IWordle extends lib.DefaultService {

	/**
	 * Length of the word to guess (number of characters).
	 */
	static final int WORD_SIZE = 5;

	/**
	 * Restart the game to the initial situation.
	 * 
	 * @throws PA_Exception if it is not allowed to call this operation
	 *                      (Prohibited Action Exception).
	 */
	public void restartGame() throws PA_Exception;

	/**
	 * Randomly chooses a word to guess.
	 * 
	 * @throws PA_Exception if it is not allowed to call this operation
	 *                      (Prohibited Action Exception).
	 * @throws WrongLength  if there are no words of the indicated length.
	 */
	public void newWord() throws PA_Exception, WrongLength;

	/**
	 * Returns the differences between a word and the word to guess.
	 * 
	 * @param word The proposed word to compare.
	 * @throws PA_Exception if it is not allowed to call this operation
	 *                      (Prohibited Action Exception).
	 * @throws WrongLength  if the length of the proposed word is not correct.
	 * @throws WrongFormat  if the word contains invalid characters.
	 * @return A text string representing the differences, character by character,
	 *         between the proposed word and the word to be guessed. For each
	 *         character:
	 *         "=" If the character matches.
	 *         "C" If the character exists but is in another position.
	 *         "X" If the character does not exist.
	 */
	public String obtainDifferences(String word)
			throws PA_Exception, WrongLength, WrongFormat;

	/**
	 * Returns whether the game has ended or not.
	 * 
	 * @param word The proposed word to compare.
	 * @throws PA_Exception if it is not allowed to call this operation
	 *                      (Prohibited Action Exception).
	 * @throws WrongLength  if the length of the proposed word is not correct.
	 * @throws WrongFormat  if the word contains invalid characters.
	 * @return An integer indicating whether the game has ended or not (and for what
	 *         reason it ended):
	 *         0 The game has not ended (there are attempts left and the word was
	 *         not guessed)
	 *         1 The game has ended (THE WORD WAS GUESSED)
	 *         2 The game has ended (NO ATTEMPTS LEFT)
	 */
	public int endedGame(String word)
			throws PA_Exception, WrongLength, WrongFormat;

	/**
	 * Returns the word to be guessed.
	 * 
	 * @throws PA_Exception if it is not allowed to call this operation
	 *                      (Prohibited Action Exception).
	 * @return the word to be guessed.
	 */
	public String requestWord() throws PA_Exception;

}