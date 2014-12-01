package fi.webshop.users.dao;

//This is interface for Spring to handle Product class with db.

import java.util.List;

import fi.webshop.users.model.Order;
import fi.webshop.users.model.OrderItem;

public interface OrderDao {

	public List<OrderItem> listOrderItems(int order_id);

	public void addOrderItem(OrderItem oa);

	public List<Order> getOrdersByUsername(String username);

}
