package fi.webshop.users.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.webshop.users.dao.ProductDao;
import fi.webshop.users.model.ForNullTest;
import fi.webshop.users.model.Product;



@Service
public class ProductServiceImpl implements ProductService {

	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Transactional
	public void addProduct(Product p) {
		this.productDao.addProduct(p);
	}

	@Transactional
	public void updateProduct(Product p) {
		this.productDao.updateProduct(p);
	}

	@Override
	@Transactional
	public List<Product> listProducts() {
		return this.productDao.listProducts();
	}

	@Transactional
	public Product getProductById(int id) {
		return this.productDao.getProductById(id);
	}

	@Transactional
	public void removeProduct(int id) {
		this.productDao.removeProduct(id);
	}

	@Override
	public void addForNullTest(ForNullTest t) {
		// TODO Auto-generated method stub
		
	}


	}


