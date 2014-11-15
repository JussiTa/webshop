package fi.webshop.users.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;



@Entity
@Table(name = "orders")
public class Order {

	private SortedSet<OrderItem> items = new TreeSet<OrderItem>();
	private Long order_id;
	private Date date;
	private String order_name;
	private double total;
	private Long user_id;

	@Id
	@GeneratedValue
	@Column(name = "order_id", unique = true, nullable = false)
	public Long getId() {
		return order_id;
	}	
	public void setId(Long id){
		this.order_id=id;		
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="order_date")
	
	public Date getDate() {
		return this.date;
	}
	
	public void setDate(Date d){
		this.date=d;
	}

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@OrderBy("itemid")
	@JoinColumn(name = "order_id")
	
	public SortedSet<OrderItem> getItems() {
		return items;
	}

	public void setItems(SortedSet<OrderItem> items) {
		this.items = items;
	}
	
	@Column(name="total")
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	@Column (name="user_id")
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	@Column(name="order_name")
	public String getOrder_name() {
		return order_name;
	}

	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}
	
	public void addrderItem(OrderItem oi){
		this.items.add(oi);
		
		
	}

}
