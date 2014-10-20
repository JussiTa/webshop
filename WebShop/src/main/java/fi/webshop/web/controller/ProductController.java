package fi.webshop.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fi.webshop.users.dao.ProductsNotFoundException;
import fi.webshop.users.model.Product;
import fi.webshop.users.service.ProductService;
import fi.webshop.web.view.Cart;

/**
 * @author Jussi
 * 
 *         This controller class handler product from db to web site and to
 *         cart.
 * 
 */
@Controller
@Scope("session")
public class ProductController {

	@Autowired(required = true)
	private ProductService productService;
	Product p;
	@Autowired
	private Cart cart = new Cart();
	private List<Product> lista = new ArrayList<Product>();

	@Qualifier(value = "productService")
	public void setProductService(ProductService ps) {
		this.productService = ps;

	}

	// Adding cart to the session
	@RequestMapping(method = RequestMethod.GET)
	public String getCartMetod(HttpServletRequest request, HttpSession session) {
		request.getSession().setAttribute("cart", cart);
		cart = (Cart) session.getAttribute("cart");
		return "cart";
	}

	// Getting products from database and showing them via model.

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String listProducts(Model model) {
		
		model.addAttribute("product", new Product());
		
		try{
			model.addAttribute("listProducts", this.productService.listProducts());
			
		
		} catch(NullPointerException e){
			model.addAttribute("errorMessage", "No products!");
			
		}
		
		
		return "product";
	}

	// Showing content of the cart
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String listProductsCart(Model model) {
		model.addAttribute("cart", new Product());
		model.addAttribute("listProducts", this.cart.getProductList());
		return "cart";
	}

	// Adding product to cart

	@RequestMapping(value = "/addtocart/{id}/addtocart2/{name}/addtocart3/{price}", method = RequestMethod.GET)
	public String addToCart(@PathVariable final int id,
			@PathVariable final String name, @PathVariable final double price) {
		Product p = new Product();

		p.setId(id);
		p.setName(name);
		p.setPrice(price);
		this.cart.addProduct(p);
		return "redirect:/cart";

	}

	// Removing product from the cart.
	@RequestMapping("/remove/{id}")
	public String removeProduct(@PathVariable("id") int id) {

		this.productService.removeProduct(id);
		return "redirect:/products";
	}

	@RequestMapping("/edit/{id}")
	public String editProduct(@PathVariable("id") int id, Model model) {
		model.addAttribute("product", this.productService.getProductById(id));
		try {
			model.addAttribute("listProducts", this.productService.listProducts());
		} catch (ProductsNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "product";
	}

}