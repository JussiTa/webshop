package fi.webshop.users.service;


import java.util.List;

import org.springframework.stereotype.Service;

import fi.webshop.users.dao.ProductsNotFoundException;
import fi.webshop.users.model.Product;
import fi.webshop.web.view.Cart;

/**
 * @author Jussi
 * 
 */
@Service
public interface ProductService {

	List<Product> listProducts() throws ProductsNotFoundException;

	public void addProduct(Product p);

	public void updateProduct(Product p);

	public void removeProduct(int id);

	public Product getProductById(int id);

	public Cart updateProductAmount(Cart cart);

	public List<Product> getProductByName(String string);
	
	

}
