/**
 * 
 */
package fi.webshop.users.dao;

/**
 * @author Jussi
 * 
 */
public class UsernameReservedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsernameReservedException(String message) {
		super(message);
	}
}