package fi.webshop.users.service;

import java.util.List;

import fi.webshop.users.dao.OrderDao;
import fi.webshop.users.dao.UserDao;
import fi.webshop.users.model.Order;
import fi.webshop.users.model.OrderItem;
import fi.webshop.users.model.User;

public class OrderServiceImpl implements OrderService {
	
	private OrderDao orderDao;
	private UserDao userDao;
	
	
	
	public void setUserDao(UserDao ud){
		this.userDao=ud;
		
	}
	
	public void setOrderDao(OrderDao od){
		this.orderDao=od;
		
	}

	@Override
	public List<OrderItem> listOrderItems(int order_id) {
		return orderDao.listOrderItems(order_id);
	}

	@Override
	public void addOrderItem(OrderItem oa) {
		orderDao.addOrderItem(oa);

	}

	@Override
	public Order getOrderById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addOrder(Order o, User u) {
		userDao.addOrder(o, u);
		
	}

}
