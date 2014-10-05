package fi.webshop.web.view;

/**
* This class handles shopping cart content: adding removing...
*/


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fi.webshop.users.model.Product;



/**
* @author justapis
*
*/

@Component
@Scope("session")
public class Cart {

	private List<Product> productList = new ArrayList<Product>();

	public void addProduct(Product p) {
		productList.add(p);

	}

	public List<Product> getProductList() {

		return this.productList;
	}

	public void removeProduct(Product p) {
		for (ListIterator<Product> iter = productList.listIterator(); iter
				.hasNext();) {
			Product p2 = iter.next();
			if (p2.getId() == p.getId())
				iter.remove();
		}
	}

	public Product getProductById(int id) {
		Product p2 = null;
		for (ListIterator<Product> iter = productList.listIterator(); iter
				.hasNext();) {
			p2 = iter.next();
			if (p2.getId() == id)
				break;
		}
		return p2;

	}

}