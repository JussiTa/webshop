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
	
	
	
	public Product(String name,String category, double price){
		this.productname = name;
		this.category = category;
		this.price = price;		
	}
	

	public Product() {		
	}


	public String getcategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column (name= "productname", nullable =false,length = 10)
	private String productname;
	
	
	@Column (name= "category", nullable =false,length = 10)
	private String category;
	@Column (name= "price", nullable =false,length = 10)
	private double price;

    @Column (name ="pcs",length =3)
	private int pcs;

	// Getters and setters.
	public int getId() {
		return id;
	}	

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return productname;
	}

	public void setName(String name) {
		this.productname = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + productname + ", price=" + price;
	}


	public int getPcs() {
		return pcs;
	}


	public void setPcs(int pcs) {
		this.pcs=pcs;
	}
}
