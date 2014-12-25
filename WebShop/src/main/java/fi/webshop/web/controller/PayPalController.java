package fi.webshop.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.core.rest.APIContext;
import com.paypal.core.rest.PayPalRESTException;
import com.paypal.core.rest.PayPalResource;

import fi.webshop.users.model.Order;
import fi.webshop.users.model.OrderItem;
import fi.webshop.users.model.Product;
import fi.webshop.users.model.User;
import fi.webshop.users.service.OrderService;
import fi.webshop.users.service.ProductService;
import fi.webshop.users.service.UserService;
import fi.webshop.web.view.Cart;
import fi.webshop.web.view.CartItem;
import fi.webshop.email.MyMailSender;
import fi.webshop.paypal.*;

@SuppressWarnings("unused")
@Controller
public class PayPalController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	private Cart cart;
	private String username;
	private OrderItem oi;
	private CartItem ci;
	@Autowired
	private MyMailSender mailSender;
	@Autowired
	private OrderService orderservice;

	private String guid;
	private Amount amount;
	private static final Logger LOGGER = Logger
			.getLogger(PayPalController.class);
	Map<String, String> map = new HashMap<String, String>();

	public void init(ServletConfig servletConfig) throws ServletException {
		// ##Load Configuration
		// Load SDK configuration for
		// the resource. This intialization code can be
		// done as Init Servlet.
		InputStream is = PayPalController.class
				.getResourceAsStream("/sdk_config.properties");
		try {
			PayPalResource.initConfig(is);
		} catch (PayPalRESTException e) {
			LOGGER.fatal(e.getMessage());
		}

	}

	public void setMailSender(MyMailSender mailSender) {
		this.mailSender = mailSender;
	}

	// If PayPal succeed
	@RequestMapping(value = "/paymentwithpaypal", method = RequestMethod.GET)
	public String confirm(Model model, HttpServletRequest request,
			HttpSession session) {

		cart = (Cart) session.getAttribute("cart");

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();

		username = userDetail.getUsername();

		final User user = userService.getUserByUsername(username);
		Order order = new Order();
		order.setOrder_name(user.getLastname());
		order.setUsername(username);
		for (ListIterator<CartItem> iterator = cart.getProductList()
				.listIterator(); iterator.hasNext();) {
			ci = iterator.next();
			oi = new OrderItem(ci.getName(), ci.getPcs(), ci.getPrice());
			order.addOrderItem(oi);

		}

		// user.addOrder(order);
		userService.updateUser(order, user.getUsername());

		// model.addAttribute("orderlist", cart.getProductList());
		Double total = (Double) session.getAttribute("total");
		model.addAttribute("name",
				"" + user.getfirstname() + " " + user.getLastname());
		model.addAttribute("address", user.getAddress());
		

		model.addAttribute("total", total);
		cart.empty();
		List<Order> orders=orderservice.getOrderByUsername(username);
        
		try {
			mailSender = new MyMailSender();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Get system properties
		Session sendsession = Session.getDefaultInstance(
				mailSender.getProperties(), new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(mailSender
								.getUsername(), mailSender.getPassword());
					}
				});

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(sendsession);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(mailSender.getFrom()));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					user.getAddress()));

			// Set Subject: header field
			message.setSubject("Your order information!");

			// Now set the actual message
			message.setText("Thank you for your order! ");

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

		return "orderDone";
	}

	// If PayPal canceled

	@RequestMapping(value = "/cancelpaymentwithpaypal", method = RequestMethod.GET)
	public String afterPaypal(Model model) {

		model.addAttribute("cancel", "Order canceled!");

		return "response";
	}

	// Preparing all needed http and other values for PayPal
	@RequestMapping(value = "/paypal", method = RequestMethod.POST)
	public String checkout(Model model, HttpServletRequest req,
			HttpServletResponse resp, HttpSession session,
			@ModelAttribute("shipping") String sh) throws ServletException,
			IOException {

		cart = (Cart) session.getAttribute("cart");
		model.addAttribute("cartItem", new CartItem());
		model.addAttribute("cart", cart.getProductList());

		cart = productService.updateProductAmount(cart);

		APIContext apiContext = null;
		String accessToken = null;
		try {
			accessToken = GenerateAccessToken.getAccessToken();

			// ### Api Context
			// Pass in a `ApiContext` object to authenticate
			// the call and to send a unique request id
			// (that ensures idempotency). The SDK generates
			// a request id if you do not pass one explicitly.
			apiContext = new APIContext(accessToken);
			// Use this variant if you want to pass in a request id
			// that is meaningful in your application, ideally
			// a order id.
			/*
			 * String requestId = Long.toString(System.nanoTime(); APIContext
			 * apiContext = new APIContext(accessToken, requestId ));
			 */
		} catch (PayPalRESTException e) {
			req.setAttribute("error", e.getMessage());
		}
		if (req.getParameter("PayerID") != null) {
			Payment payment = new Payment();
			if (req.getParameter("guid") != null) {
				payment.setId(map.get(req.getParameter("guid")));
			}

			PaymentExecution paymentExecution = new PaymentExecution();
			paymentExecution.setPayerId(req.getParameter("PayerID"));
			try {
				payment.execute(apiContext, paymentExecution);
				req.setAttribute("response", Payment.getLastResponse());
			} catch (PayPalRESTException e) {
				req.setAttribute("error", e.getMessage());
			}
		} else {

			// ###Details
			// Let's you specify details of a payment amount.
			double shipping;
			shipping = Double.parseDouble(sh);
			
			Details details = new Details();
			details.setShipping(sh);
			details.setSubtotal(cart.toString());
			details.setTax("0");
			

			double total;			
			total =cart.getTotal()+shipping;
			String totalString = Double.toString(total);	
			System.out.println("TOTAL  "+ totalString);
			// Let's you specify a payment amount.
			amount = new Amount();
			amount.setCurrency("EUR");
			// Total must be equal to sum of shipping, tax and subtotal.
			amount.setTotal(totalString);
			amount.setDetails(details);
			model.addAttribute("cart", cart);
			model.addAttribute("shipping", details.getShipping());
			model.addAttribute("total", totalString);
			session.setAttribute("total", total);

			// ###Transaction
			// A transaction defines the contract of a
			// payment - what is the payment for and who
			// is fulfilling it. Transaction is created with
			// a `Payee` and `Amount` types
			Transaction transaction = new Transaction();
			transaction.setAmount(amount);
			transaction
					.setDescription("This is the payment transaction description.");

			// The Payment creation API requires a list of
			// Transaction; add the created `Transaction`
			// to a List
			List<Transaction> transactions = new ArrayList<Transaction>();
			transactions.add(transaction);

			// ###Payer
			// A resource representing a Payer that funds a payment
			// Payment Method
			// as 'paypal'
			Payer payer = new Payer();
			payer.setPaymentMethod("paypal");

			// ###Payment
			// A Payment Resource; create one using
			// the above types and intent as 'sale'
			Payment payment = new Payment();
			payment.setIntent("sale");
			payment.setPayer(payer);
			payment.setTransactions(transactions);

			// ###Redirect URLs
			RedirectUrls redirectUrls = new RedirectUrls();
			guid = UUID.randomUUID().toString().replaceAll("-", "");
			redirectUrls.setCancelUrl(req.getScheme() + "://"
					+ req.getServerName() + ":" + req.getServerPort()
					+ req.getContextPath() + "/cancelpaymentwithpaypal?guid="
					+ guid);
			redirectUrls.setReturnUrl(req.getScheme() + "://"
					+ req.getServerName() + ":" + req.getServerPort()
					+ req.getContextPath() + "/paymentwithpaypal?guid=" + guid);
			payment.setRedirectUrls(redirectUrls);

			// Create a payment by posting to the APIService
			// using a valid AccessToken
			// The return object contains the status;
			Iterator<Links> links;

			try {
				Payment createdPayment = payment.create(apiContext);
				LOGGER.info("Created payment with id = "
						+ createdPayment.getId() + " and status = "
						+ createdPayment.getState());
				// ###Payment Approval Url

				links = createdPayment.getLinks().iterator();

				while (links.hasNext()) {
					Links link = links.next();
					if (link.getRel().equalsIgnoreCase("approval_url")) {
						req.setAttribute("redirectURL", link.getHref());

					}
				}
				req.setAttribute("response", Payment.getLastResponse());
				map.put(guid, createdPayment.getId());
			} catch (PayPalRESTException e) {
				req.setAttribute("error", e.getMessage());
			}
		}
		req.setAttribute("request", Payment.getLastRequest());

		return "response";

	}

}
