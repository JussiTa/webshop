package fi.webshop.users.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import fi.webshop.users.model.User;
/**
 * @author Jussi
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {

		List<User> users = new ArrayList<User>();

		users = getSessionFactory().getCurrentSession().createQuery("from User where username=?")
				.setParameter(0, username).list();

		if (users.size() > 0) {
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
			
		try {
			getSessionFactory().getCurrentSession().beginTransaction();
			getSessionFactory().getCurrentSession().save(u);
			getSessionFactory().getCurrentSession().getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			getSessionFactory().getCurrentSession().getTransaction().rollback();
		}
		
	}

	/*
	 * 
	 * Updating User name)
	 */
	@Override
	public void updateuser(String username) {
		
		Transaction tx = null;
		try {
			tx = getSessionFactory().getCurrentSession().beginTransaction();
			User user = (User) getSessionFactory().getCurrentSession().get(
					User.class, username);
			user.setUsername(username);
			getSessionFactory().getCurrentSession().update(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}