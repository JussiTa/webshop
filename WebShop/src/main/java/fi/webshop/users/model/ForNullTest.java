package fi.webshop.users.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "fnt")
public class ForNullTest {
	
	private String name;
	
	
	
	public ForNullTest(String name){ 
		
		this.setName(name);
		
	}
	
	
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}
	

}
