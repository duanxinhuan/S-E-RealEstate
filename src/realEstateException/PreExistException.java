package realEstateException;

public class PreExistException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PreExistException() {
		super("Property Already exists!!!");
	}
	
}
