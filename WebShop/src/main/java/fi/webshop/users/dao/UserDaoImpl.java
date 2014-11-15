package fi.webshop.users.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.management.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.Transaction;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import fi.webshop.users.model.Order;
import fi.webshop.users.model.User;
/**
 * @author Jussi
 *
 */

public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;
	
	private final static Logger LOGGER = Logger.getLogger(User.class.getName()); 
	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {
		
		List<User> users = new ArrayList<User>();
		

		users = getSessionFactory().getCurrentSession().createQuery("from User where username=?")
				.setParameter(0,username).list();
		
		
		if (users.size()>0) {
			return users.get(0);
		} else {
			return null;
		}

	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addNewUser(User u) {
		String pw_hash = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(10));	
		u.setPassword(pw_hash);
		getSessionFactory().getCurrentSession().persist(u);
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public void updateUser(Order o,String username) {
		List<User> users = new ArrayList<User>();
		getSessionFactory().openSession();
		
		users =  getSessionFactory().getCurrentSession().
				createQuery("from User where username=?")
				.setParameter(0,username).list();	
		User user = users.get(0);
		
		LOGGER.info("User id =***********************************    "+user.getId());
		
		user.addOrder(o);
        getSessionFactory().getCurrentSession().merge(user);		
		
	}

	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addOrder(Order o, User u) {
		u.addOrder(o);		
	//	updateUser(u.getUsername());
		
	}

	@Override
	public void updateUser(User user) {
		 getSessionFactory().getCurrentSession().merge(user);
		
	}

}