package fi.webshop.users.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItem implements Comparable<OrderItem>{

	private Long itemid;
	private String item_name;
	private int pcs;
	private double price;
	private double itemprice;

	public OrderItem(){
		
	}
	
	public OrderItem(String name, int pcs, double price){
		this.item_name =name;
		this.pcs = pcs;
		this.itemprice =price;
		this.price= this.pcs*this.itemprice;
		
		
	}
	
	@Id
	@GeneratedValue
	@Column(name = "item_id", unique = true, nullable = false)
	public Long getId() {
		return this.itemid;
	}

	public void setId(Long id) {

		this.itemid = id;
	}

	@Column(name = "item_name")
	public String getProductname() {
		return item_name;
	}

	public void setProductname(String productname) {
		this.item_name = productname;
	}

	@Column(name = "pcs")
	public int getPcs() {
		return pcs;
	}

	public void setPcs(int pcs) {
		this.pcs = pcs;
	}

	@Column(name = "price")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	@Column(name="itemprice")
	public double getItemprice() {
		return itemprice;
	}

	public void setItemprice(double itemprice) {
		this.itemprice = itemprice;
	}

	@Override
	public int compareTo(OrderItem o) {		
		int number = 0;
		if(o.item_name!=null)
		number = this.item_name.compareTo(o.item_name);
		
		return number;
				
	}

}
