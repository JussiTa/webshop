package fi.webshop.users.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fi.webshop.users.model.Order;
import fi.webshop.users.model.OrderItem;
import fi.webshop.users.model.User;

@Service
public interface OrderService {

	List<OrderItem> listOrderItems(int order_id);

	public void addOrderItem(OrderItem oa);

	public Order getOrderById(int id);
	
	public List<Order> getOrderByUsername(String username);
	
	public void addOrder(Order o, User u);
	

}
	

