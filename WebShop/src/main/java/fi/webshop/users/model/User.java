package fi.webshop.users.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "users")
public class User {
	@NotEmpty (message = "Please enter your username.")
	private String username;
	
	@Size(min=6, max=12, message = "Your password must between 6 and 15 characters")
	private String password;
	
	@NotEmpty(message = "Please enter your firstname.")
	private String firstname;
	@NotEmpty(message = "Please enter your lastname.")
	private String lastname;
	@NotEmpty(message = "Please enter your addresss.")
	private String address;		
	@Size(min=5, max=5,message="Must be five number")	
	private String zipcode;
	@NotEmpty(message = "Please enter your town.")
	private String town;
	private boolean enabled;
	
	@NotEmpty(message = "Please enter your e-mail.")
	private String email;
	
	

	public User() {
	}

	public User(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public User(String username, String password, boolean enabled,
			Set<UserRole> userRole) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
	}
	
	@Id
	@GeneratedValue
	@Column(name = "user_id", unique = true)
	private Long user_id;

	public Long getId() {
		return user_id;
	}

	public void setId(Long id) {
		this.user_id = id;
	}	

	@Column(name = "username", unique = true, nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 60)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "firstname", nullable = false, length = 10)
	public String getfirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "enabled", nullable = false)
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/*
	 * Setting up relations between user and it's roles (one to many)
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	private Set<UserRole> userRole = new HashSet<UserRole>();

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	@Column(name = "lastname", nullable = false, length = 20)
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name = "address", nullable = false, length = 20)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "zipcode", nullable = false, length = 5)
	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Column(name = "town", nullable = false, length = 10)
	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}	
	
	@Column(name = "email", unique = true, nullable = false, length = 45)
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email){
		this.email = email;
		
	}
	
	
	

	public Set<UserRole> getRoles() {
		return this.userRole;
	}

	public void addRole(UserRole role) {
		this.userRole.add(role);

	}

	@OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private Set<Order> orders = new HashSet<Order>();

	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> orders) {

		this.orders = orders;

	}

	public void addOrder(Order o) {
		this.orders.add(o);

	}

}