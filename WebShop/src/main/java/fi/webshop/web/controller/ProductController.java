package fi.webshop.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.webshop.users.dao.ProductsNotFoundException;
import fi.webshop.users.model.Product;
import fi.webshop.users.model.User;
import fi.webshop.users.service.ProductService;
import fi.webshop.web.view.Cart;
import fi.webshop.web.view.CartItem;
import fi.webshop.web.view.Tag;

/**
 * @author Jussi
 * 
 *         This controller class handler product from db to web site and to
 *         cart.
 * 
 */
@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	//@Autowired
	private Cart cart = new Cart();

	public void setProductService(ProductService ps) {
		this.productService = ps;

	}

	List<Tag> data = new ArrayList<Tag>();

	ProductController() {
		// init data for testing
		data.add(new Tag(1, "ruby"));
		data.add(new Tag(2, "rails"));
		data.add(new Tag(3, "c / c++"));
		data.add(new Tag(4, ".net"));
		data.add(new Tag(5, "python"));
		data.add(new Tag(6, "java"));
		data.add(new Tag(7, "javascript"));
		data.add(new Tag(8, "jscript"));

	}

	
	// Getting products from database and showing them via model.

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String listProducts(Model model, HttpServletRequest request) {		
		 request.getSession().setAttribute("cart", cart);
		model.addAttribute("product", new Product());

		try {
			model.addAttribute("listProducts",
					this.productService.listProducts());

		} catch (ProductsNotFoundException e) {
			model.addAttribute("errorMessage", "No products!");

		}
		return "product";
	}

	// Showing content of the cart
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String listProductsCart(Model model) {
		model.addAttribute("total", cart.getTotal());
		model.addAttribute("cart", this.cart.getProductList());
		return "cart";
	}
/*
	 Adding product to cart
	@RequestMapping(value = "/addtocart/{id}/addtocart2/{name}/addtocart3/{price}", method = RequestMethod.GET)
	public String addToCart(@PathVariable final int id,
			@PathVariable final String name, @PathVariable final double price) {
		
		CartItem ci = new CartItem();
		ci.setId(id);
		ci.setName(name);
		ci.setPrice(price);
		this.cart.addProduct(ci);
		return "redirect:/cart";

	}

	*/
	
	
	
	
	@RequestMapping(value = "/addtocart", method = RequestMethod.GET)
	public String viewRegistrationForm(Map<String, Object> model) {
		Product userForm = new Product();
		model.put("userForm", userForm);

		return "product";
	}

	
	
	@RequestMapping(value = "/addtocart", method = RequestMethod.POST)
	public String processRegistration(@ModelAttribute("userForm") Product p,
			Map<String, Object> model) {
		
		return "cart";
	
	}
	
	
	// Removing product from the DB.
	@RequestMapping("/remove/{id}")
	public String removeProduct(@PathVariable("id") int id) {

		this.productService.removeProduct(id);
		return "admin";
	}

	// Edit product in DB.
	@RequestMapping("/edit/{id}")
	public String editProduct(@PathVariable("id") int id, Model model) {

		try {
			model.addAttribute("product",
					this.productService.getProductById(id));
			model.addAttribute("listProducts",
					this.productService.listProducts());
		} catch (ProductsNotFoundException e) {

			e.printStackTrace();
		}
		return "admin";
	}

	@RequestMapping(value = "/products/getTags", method = RequestMethod.GET)
	public @ResponseBody
	List<Tag> getTags(@RequestParam String tagName) {

		return simulateSearchResult(tagName);

	}

	private List<Tag> simulateSearchResult(String tagName) {
		List<Tag> result = new ArrayList<Tag>();
		for (Tag tag : data) {
			if (tag.getTagName().contains(tagName)) {
				result.add(tag);
			}
		}
		return result;
	}

	public Cart getCart() {
		return this.cart;
	}

}