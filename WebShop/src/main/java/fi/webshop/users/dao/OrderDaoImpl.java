package fi.webshop.users.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fi.webshop.users.model.Order;
import fi.webshop.users.model.OrderItem;



public class OrderDaoImpl implements OrderDao {
	
	
	
	private SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderItem> listOrderItems(int order_id) {
		Session session = sessionFactory.getCurrentSession();
		List<OrderItem> orders;
		orders= (List<OrderItem>) session.createQuery("from orderitem where order_id=?").
				setParameter(0, order_id).list();
		
		return orders;
	}

	@Override
	public void addOrderItem(OrderItem oa) {
	 

	}

	
}
