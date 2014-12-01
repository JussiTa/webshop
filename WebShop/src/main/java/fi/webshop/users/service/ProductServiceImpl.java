package fi.webshop.users.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.webshop.users.dao.ProductDao;
import fi.webshop.users.dao.ProductsNotFoundException;
import fi.webshop.users.model.Product;
import fi.webshop.web.view.Cart;




public class ProductServiceImpl implements ProductService {

	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	//@Transactional
	public void addProduct(Product p) {
		this.productDao.addProduct(p);
	}

	
	public void updateProduct(Product p) {
		this.productDao.updateProduct(p);
	}

	@Override
	//@Transactional
	public List<Product> listProducts() throws ProductsNotFoundException  {
		List<Product> list = null;
	
		list= this.productDao.listProducts();
	
		return list;
		
		
	}

//	@Transactional
	public Product getProductById(int id) {
		return this.productDao.getProductById(id);
	}

	//@Transactional
	public void removeProduct(int id) {
		this.productDao.removeProduct(id);
	}

	@Override
	public Cart updateProductAmount(Cart cart) {
		Cart cart2;
		
		cart2= productDao.updateMount(cart);
		return cart2;
		
	}

	@Override
	public List<Product> getProductByName(String name) {
		// TODO Auto-generated method stub
		return productDao.getProductByName(name);
	}




	}


