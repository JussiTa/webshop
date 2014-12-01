/**
 * 
 */
package fi.webshop.users.service;


import org.springframework.stereotype.Service;

import fi.webshop.users.dao.UsernameReservedException;
import fi.webshop.users.model.Order;
import fi.webshop.users.model.User;

/**
 * @author Jussi
 * 
 */
@Service
public interface UserService {
	

	public void addNewUser(User u) throws UsernameReservedException;
	
	public User getUserByUsername(String userName);
	public void updateUser(Order o, String username);

	public void updateUser(User user);
	
}
