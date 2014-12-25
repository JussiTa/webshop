package fi.webshop.users.model;

import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "orders")
public class Order {

	private SortedSet<OrderItem> items = new TreeSet<OrderItem>();
	private Long id;
	private Date date = new Date();
	private String order_name;
	private double total;
	private String username;
	

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "order_date")
	@Type(type = "timestamp")
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date d) {
		this.date = d;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("item_name")
	@JoinColumn(name = "id")
	public SortedSet<OrderItem> getItems() {
		return items;
	}
	
	
	
	

	public void setItems(SortedSet<OrderItem> items) {
		this.items = items;
	}

	@Column(name = "total")
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}


	@Column(name = "username")
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername(){
		return username;
		
	}

	
	@Column(name = "order_name")
	public String getOrder_name() {
		return order_name;
	}

	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}

	public void addOrderItem(OrderItem oi) {
		
		this.total+= oi.getPrice();		
		this.items.add(oi);
		
		
		

	}

}
