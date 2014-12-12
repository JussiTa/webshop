package fi.webshop.users.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fi.webshop.users.model.Product;
import fi.webshop.users.model.User;
import fi.webshop.web.view.Cart;
import fi.webshop.web.view.CartItem;

/**
 * @author justapis
 * 
 */

@Repository
public class ProductDaoImpl implements ProductDao {

	private static final Logger logger = LoggerFactory
			.getLogger(ProductDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public void addProduct(Product p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("Product saved successfully, Product Details=" + p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateProduct(Product p) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Product> products = new ArrayList<Product>();
		String hql = "FROM Product WHERE productname =?";
		products = session.createQuery(hql).setParameter(0, p.getName()).list();
		if (!products.isEmpty()) {

			session.merge(p);
			logger.info("Product updated successfully, Product Details=" + p);
		}

		else
			addProduct(p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> listProducts() throws ProductsNotFoundException {
		Session session = this.sessionFactory.getCurrentSession();
		List<Product> productsList;

		productsList = (List<Product>) session.createQuery("from Product")
				.list();
		if (productsList.isEmpty())
			throw new ProductsNotFoundException("Products are not");

		else
			return productsList;
	}

	@Override
	public Product getProductById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Product p = (Product) session.load(Product.class, new Integer(id));
		logger.info("Product loaded successfully, Product details=" + p);
		return p;
	}

	@Override
	public void removeProduct(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Product p = (Product) session.load(Product.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}

		logger.info("Product deleted successfully, product details=" + p);
	}

	@Override
	public Cart updateMount(Cart cart) {
		CartItem ci;
		Cart cart2;
		boolean sufficient = false;
		for (ListIterator<CartItem> iter = cart.getProductList().listIterator(); iter
				.hasNext();) {
			ci = iter.next();
			sufficient = checkAmountOfDb(ci);
			if (!sufficient)
				ci.setSufficient(false);

			else
				ci.setSufficient(true);
		}
		cart2 = cart;
		return cart2;

	}

	@SuppressWarnings("unchecked")
	private boolean checkAmountOfDb(CartItem ci) {
		Session session = this.sessionFactory.getCurrentSession();

		List<Product> products = new ArrayList<Product>();
		String hql = "FROM Product WHERE productname =?";
		products = session.createQuery(hql).setParameter(0, ci.getName())
				.list();

		if (ci.getPcs() > products.get(0).getPcs()) {
			ci.setDbamount(products.get(0).getPcs());
			System.out.println("####NO SUFFICIENT!!!######");
			return false;
		} else {
			int result;
			result = products.get(0).getPcs() - ci.getPcs();
			products.get(0).setPcs(result);
			session.update(products.get(0));

			return true;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Product> products = new ArrayList<Product>();

		String hql = "FROM Product WHERE productname =?";
		products = session.createQuery(hql).setParameter(0, name).list();

		return products;
	}

}
