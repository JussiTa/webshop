package fi.webshop.users.dao;

import fi.webshop.users.model.User;

/*
 * This is interface for handling user database functions. 
 * 
 */


public interface UserDao {

	public User findByUserName(String username);

	public void addNewUser(User u);

	public void updateuser(String username);

	public User findUserById(int id);

}