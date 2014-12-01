package fi.webshop.users.dao;

import fi.webshop.users.model.Order;
import fi.webshop.users.model.User;

/*
 * This is interface for handling user database functions. 
 * 
 */


public interface UserDao {

	public User findByUserName(String username);

	public void addNewUser(User u) throws UsernameReservedException;

	public void updateUser(Order o,String username);

	public User findUserById(int id);
	
	public void addOrder(Order o, User u);

	public void updateUser(User user);

}