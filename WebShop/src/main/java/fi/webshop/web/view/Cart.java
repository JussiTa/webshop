package fi.webshop.web.view;

/**
 * This class handles shopping cart content: adding removing...
 * @Scope session means that content of the Cart shows every pages.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import org.springframework.context.annotation.Scope;


/**
 * @author justapis
 * @param <K>
 * 
 */

@Scope("session")
public class Cart {

	/**
	 * 
	 */

	private List<CartItem> productList = new ArrayList<CartItem>();
	private double total;

	private HashMap<String, CartItem> id = new HashMap<String, CartItem>();

	public void addProduct(CartItem ci) {
		if (!id.containsKey(ci.getId())) {
			ci.setPcs(1);
			id.put(ci.getId(), ci);

			productList.add(ci);
		} else {
			CartItem ci2 = (CartItem) id.get(ci.getId());

			ci2.setPcs(1);

		}

		total += ci.getPrice();
	}

	public List<CartItem> getProductList() {

		return this.productList;
	}

	public void removeProduct(CartItem ci) {
		for (ListIterator<CartItem> iter = productList.listIterator(); iter
				.hasNext();) {
			CartItem ci2 = iter.next();
			if (ci2.getId() == ci.getId())
				iter.remove();
		}
	}

	/*
	 * public CartItem getProductById(int id) { CartItem ci2 = null; for
	 * (ListIterator<CartItem> iter = productList.listIterator(); iter
	 * .hasNext();) { ci2 = iter.next(); if (ci2.getId() == id) break; } return
	 * ci2;
	 * 
	 * }
	 */

	public double getTotal() {

		return this.total;

	}

	public List<CartItem> getItems() {
		return this.productList;

	}

}