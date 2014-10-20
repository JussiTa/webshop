package fi.webshop.users.service;


import java.util.List;

import fi.webshop.users.dao.ProductsNotFoundException;
import fi.webshop.users.model.ForNullTest;
import fi.webshop.users.model.Product;

/**
 * @author Jussi
 * 
 */
public interface ProductService {

	List<Product> listProducts() throws ProductsNotFoundException;

	void addProduct(Product p);

	void updateProduct(Product p);

	void removeProduct(int id);

	public Product getProductById(int id);
	
	public void addForNullTest(ForNullTest t);

}
