package fi.webshop.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.webshop.users.dao.ProductsNotFoundException;
import fi.webshop.users.model.Product;
import fi.webshop.users.model.User;
import fi.webshop.users.model.UserRole;
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
	private Cart cart = new Cart();

	public void setProductService(ProductService ps) {
		this.productService = ps;

	}

	List<Tag> data = new ArrayList<Tag>();

	ProductController() {
		// init data for testing
		data.add(new Tag(1, "ryepowder"));
		data.add(new Tag(2, "wheatpowder"));
		data.add(new Tag(3, "rolledwheat"));
		data.add(new Tag(4, "oatmeal"));
		data.add(new Tag(5, "oatpowder"));
		data.add(new Tag(6, "crushed wheat"));

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
			model.addAttribute("errorMessage", e);

		}
		return "product";
	}

	// Preparing form asking product from DB by name.
	@RequestMapping(value = "/findproduct", method = RequestMethod.GET)
	public String viewSearchForm(ModelMap model) {
		Product productFormByName = new Product();
		model.addAttribute("p", productFormByName);

		return "product";
	}

	@RequestMapping(value = "/findproduct", method = RequestMethod.POST)
	public String processProductSearch(@ModelAttribute("p") Product pform,
			Map<String, Object> model, Model model2) {

		model2.addAttribute("product", new Product());

		model2.addAttribute("listProducts",
				this.productService.getProductByName(pform.getName()));
		return "product";
	}

	// Showing content of the cart
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String listProductsCart(Model model) {
		model.addAttribute("total", cart.getTotal());
		model.addAttribute("cart", this.cart.getProductList());
		return "cart";
	}

	@RequestMapping(value = "/addtoCart", method = RequestMethod.POST)
	public String processaddingcart(@ModelAttribute("product") Product p,
			ModelMap model) {
		CartItem ci = new CartItem();
		ci.setId(p.getId());
		ci.setName(p.getName());
		ci.setPrice(p.getPrice());
		ci.setPcs(p.getPcs());
		this.cart.addProduct(ci);
		return "redirect:/cart";

	}

	// Removing product from the DB.
	@RequestMapping("/remove/{id}")
	public String removeProduct(@PathVariable("id") int id) {

		this.productService.removeProduct(id);
		return "admin";
	}

	// Removing product from the cart.
	@RequestMapping("/removeItem/{id}")
	public String removeProductfromCart(@PathVariable("id") int id) {

		cart.empty();
		return "cart";
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

	@RequestMapping(value = "/getTags", method = RequestMethod.GET)
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

	@RequestMapping(value = "/editCart/{id}", method = RequestMethod.GET)
	public String processEditcart(@PathVariable("id") int id, Model model) {
		List<CartItem> productList = new ArrayList<CartItem>();
		productList.add(this.cart.getItem(id));
		model.addAttribute("cart", productList);

		return "editcart";

	}

	@RequestMapping(value = "/saveEditCart", method = RequestMethod.POST)
	public String processSaveEditcart(@ModelAttribute("product") CartItem ci,
			ModelMap model) {

		this.cart.editPcs(ci.getId(), ci.getPcs());

		return "redirect:/cart";

	}
	
	
	@RequestMapping (value="/addOredit",method =RequestMethod.POST)
	public String processEditForm(@ModelAttribute Product p){		
		productService.updateProduct(p);		
		return "redirect:/admin";
		
	}
	
	
	
	
	
	
	
	
	
	
	

}