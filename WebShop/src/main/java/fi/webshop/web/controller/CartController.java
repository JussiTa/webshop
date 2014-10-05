package fi.webshop.web.controller;
/**
 * 
 *//*
package fi.kauppa;

*//**
 * @author justapis
 *
 *//*


	import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

	import fi.kauppa.model.Product;
import fi.kauppa.service.ProductService;
	 

	
	@Controller
	
	@SessionAttributes("myRequestObject")
	public class CartController {    
	     private ShoppingCart cart;
	     
	     
	  // Adding cart to the session
	 	@RequestMapping(method = RequestMethod.GET)
	 	public String getCartMetod(HttpServletRequest request, HttpSession session) {
	 		request.getSession().setAttribute("cart", cart);
	 		cart = (ShoppingCart) session.getAttribute("cart");
	 		return "cart";
	 	}

	     
	     
	     
	     
	     
	     
	     
	     @RequestMapping(value = "/checkout/", method = RequestMethod.GET)
		    public String getEmployee(Model model,@PathVariable("id") int id) {
		        
		         
		        
		        model.addAttribute("id", id);
		        model.addAttribute("name", "jussi");
		         
		        return "customer";
		    }
		 
	



}
*/