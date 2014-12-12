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
 * 
 * 
 */

@Scope("session")
public class Cart {

	private List<CartItem> productList = new ArrayList<CartItem>();
	private double total;
	private HashMap<String, CartItem> id = new HashMap<String, CartItem>();

	public void addProduct(CartItem ci) {
		if (!id.containsKey(ci.getId())) {
			id.put(ci.getId(), ci);
			productList.add(ci);
		} else {
			CartItem ci2 = (CartItem) id.get(ci.getId());
			ci2.setPcs(ci.getPcs());

		}

		total += ci.geTotal();
	}

	public List<CartItem> getProductList() {

		return this.productList;
	}

	public void removeProduct(int id) {
		for (ListIterator<CartItem> iter = productList.listIterator(); iter
				.hasNext();) {
			CartItem ci2 = iter.next();
			if (ci2.getId().equals(id))
				iter.remove();
		}
	}

	public double getTotal() {

		return this.total;

	}

	public List<CartItem> getItems() {
		return this.productList;

	}

	public void empty() {
		this.productList.clear();

	}

	public void editPcs(String id, int pcs) {
		for (ListIterator<CartItem> iter = productList.listIterator(); iter
				.hasNext();) {
			CartItem ci2 = iter.next();
			if (ci2.getId().equals(id))
				total -= ci2.editPcs(pcs);
		}
	}

	public CartItem getItem(int id) {

		for (ListIterator<CartItem> iter = productList.listIterator(); iter
				.hasNext();) {
			CartItem ci2 = iter.next();
			if (ci2.getId().equals(Integer.toString(id)))
				return ci2;

		}

		return null;

	}
	
	@Override
	public String toString(){
		return ""+this.total;
		
		
	}

}