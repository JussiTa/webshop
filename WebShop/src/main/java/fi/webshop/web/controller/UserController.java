package fi.webshop.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;

import fi.webshop.users.dao.ProductsNotFoundException;
import fi.webshop.users.model.Product;
import fi.webshop.users.model.User;
import fi.webshop.users.model.UserRole;
import fi.webshop.users.service.ProductService;
import fi.webshop.users.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	private UserRole ur;
	@Autowired
	private ProductService productService;

	
	public void setUserService(UserService us) {
		this.userService = us;

	}

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView("home");

		return model;

	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String aboutPage(ModelMap model) {

		return "about";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(ModelMap model) {

		return "logout";

	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String showProductsInadminPage(Model model) {

		model.addAttribute("product", new Product());

		try {
			model.addAttribute("listProducts",
					this.productService.listProducts());

		} catch (ProductsNotFoundException e) {
			model.addAttribute("errorMessage", "No products!");

		}

		model.addAttribute("title", "WebShop");
		model.addAttribute("message", "This page is for ROLE_ADMIN only!");

		return "admin";

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error",
					getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");

		}
		model.setViewName("login");

		return model;

	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession()
				.getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("403");
		return model;

	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String viewRegistrationForm(ModelMap model) {
		User userForm = new User();
		model.addAttribute("userForm", userForm);

		return "registration";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processRegistration( @Valid @ModelAttribute ("userForm") User userForm,
			BindingResult br, Map<String, Object>model) {
		
		if(br.hasErrors())
			return "registration";

		ur = new UserRole(userForm, "ROLE_USER");
		ur.setUser(userForm);
		userForm.addRole(ur);
		userForm.setEnabled(true);

		userService.addNewUser(userForm);
		return "registrationSuccess";
	}
	
	
	
	
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String processUserEdits(@ModelAttribute("editform")User user,
			Map<String,Object> model){
		
		userService.updateUser(user);
		
		return "";
	}
}
