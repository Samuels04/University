package commun;

/**
 * Exception for errors accessing the words file.
 */
public class DictionaryError extends Exception {

	private static final long serialVersionUID = -1283348700658572943L;

	public DictionaryError() {
		super();
	}

	public DictionaryError(String str) {
		super(str);
	}
}
