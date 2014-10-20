package fi.webshop.users.model;

/*
 * 
 * 
 * This class is entity class for database table.
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 * 
 * @author isokaju
 *
 */
@Entity
@Table(name = "products")
public class Product {

	public String getGategory() {
		return gategory;
	}

	public void setGategory(String gategory) {
		this.gategory = gategory;
	}

	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column (name= "productname", nullable =false,length = 10)
	private String name;
	
	
	@Column (name= "gategory", nullable =false,length = 10)
	private String gategory;
	@Column (name= "price", nullable =false,length = 10)
	private double price;

	// Getters and setters.
	public int getId() {
		return id;
	}	

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", price=" + price;
	}
}
