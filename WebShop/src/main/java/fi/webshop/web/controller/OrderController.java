package fi.webshop.web.controller;

import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.webshop.users.model.Order;
import fi.webshop.users.model.OrderItem;
import fi.webshop.users.model.Product;
import fi.webshop.users.model.User;
import fi.webshop.users.service.OrderService;
import fi.webshop.users.service.ProductService;
import fi.webshop.users.service.UserService;
import fi.webshop.web.view.Cart;
import fi.webshop.web.view.CartItem;


@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	private Cart cart;
	
	@Autowired
	private OrderService orderservice;

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String checkout(Model model, HttpServletRequest request,
			HttpSession session) {
		cart = (Cart) session.getAttribute("cart");
		model.addAttribute("cartItem", new CartItem());
		model.addAttribute("cart", cart.getProductList());

		cart = productService.updateProductAmount(cart);

		return "checkout";

	}
	
	
	
	

/*	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String confirm(Model model, HttpServletRequest request,
			HttpSession session) {

		cart = (Cart) session.getAttribute("cart");

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();

		username = userDetail.getUsername();
		
		User user = userService.getUserByUsername(username);
		Order order = new Order();
		order.setOrder_name(user.getLastname());
        order.setUsername(username);
		for (ListIterator<CartItem> iterator = cart.getProductList()
				.listIterator(); iterator.hasNext();) {
			ci = iterator.next();
			oi = new OrderItem(ci.getName(), ci.getPcs(), ci.getPrice());			
			System.out.println("#########USERNAME############" + username);
			order.addOrderItem(oi);

		}

		// user.addOrder(order);
		userService.updateUser(order, user.getUsername());

		model.addAttribute("orderlist", cart.getProductList());

		cart.empty();

		return "orderDone";

	}*/

	@RequestMapping(value = "/myPage", method = RequestMethod.GET)
	public String getUser(Model model) {
        model.addAttribute("order", new Order());
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		model.addAttribute("orderlist",
				orderservice.getOrderByUsername(userDetail.getUsername()));

		return "myPage";

	}

}
