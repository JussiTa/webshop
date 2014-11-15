/**
 * 
 */
package fi.webshop.users.service;




import fi.webshop.users.dao.UserDao;
import fi.webshop.users.model.Order;
import fi.webshop.users.model.User;

/**
 * @author Jussi
 *
 */

public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override	
	public void addNewUser(User u) {
		userDao.addNewUser(u);

	}

	@Override
	public User getUserByUsername(String userName) {
		// TODO Auto-generated method stub
		return userDao.findByUserName(userName);
	}

	@Override
	public void updateUser(Order o, String username) {
		userDao.updateUser(o,username);
		
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);	
	}

}
