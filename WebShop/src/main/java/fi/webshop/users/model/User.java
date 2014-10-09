package fi.webshop.users.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "users")
public class User {
	
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String address;
	private String zipcode;
	private String town;
	private boolean enabled;


	public User() {
	}

	public User(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public User(String username, String password, boolean enabled, Set<UserRole> userRole) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
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
	
	
	
	@Column (name= "firstname", nullable =false,length = 10)
	
	public String getfirstname(){
		return this.firstname;
	}
	
	public void setFirstname(String firstname){
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
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "user",cascade = CascadeType.ALL)	
	private Set<UserRole> userRole = new HashSet<UserRole>();
	

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole= userRole;
	}
	
	
    @Column(name= "lastname", nullable =false, length = 20)
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
    @Column (name ="address",nullable =false,length=20)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
    @Column (name ="zipcode",nullable =false, length=5)
	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	@Column(name= "town",nullable = false, length =10)
	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}
	
	/*@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "STOCK_ID", unique = true, nullable = false)
	public Integer getStockId() {
		return this.stockId;
	}
	*/
	
	/*@Id()
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "id")*/
	
	
	
	@Id
	@GeneratedValue
	
	@Column(name = "user_id", unique = true, nullable = false)
	private Long id;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Set<UserRole> getRoles(){
		return this.userRole;
	}
	
	public void addRole(UserRole role){
		this.userRole.add(role);
		
		
	}

}