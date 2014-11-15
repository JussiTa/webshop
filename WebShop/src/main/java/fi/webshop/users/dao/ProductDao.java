package fi.webshop.users.dao;

//This is interface for Spring to handle Product class with db.

import java.util.List;

import fi.webshop.users.model.Product;
import fi.webshop.web.view.Cart;


public interface ProductDao {

	public List<Product> listProducts() throws ProductsNotFoundException;

	public void addProduct(Product p);

	public void updateProduct(Product p);	

	public Product getProductById(int id);

	public void removeProduct(int id);

	public Cart updateMount(Cart cart);

}
