package fi.webshop.users.dao;

//This is interface for Spring to handle Product class with db.

import java.util.List;

import fi.webshop.users.model.Product;


public interface ProductDao {

	public List<Product> listProducts();

	public void addProduct(Product p);

	public void updateProduct(Product p);	

	public Product getProductById(int id);

	public void removeProduct(int id);

}
