/**
 * 
 */
package fi.webshop.users.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import fi.webshop.users.dao.UserDao;
import fi.webshop.users.model.User;

/**
 * @author Jussi
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	@Transactional
	public void addNewUser(User u) {
		userDao.addNewUser(u);

	}

}
