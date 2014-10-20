package fi.webshop.users.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.webshop.users.dao.ProductDao;
import fi.webshop.users.dao.ProductsNotFoundException;
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
	public List<Product> listProducts() throws ProductsNotFoundException {
		List<Product> list = null;
	//	try{
		list= this.productDao.listProducts();
		
	//	}catch(NullPointerException e){
	//		e.printStackTrace();
	//	}
		return list;
		
		
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


