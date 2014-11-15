package fi.webshop.web.view;

public class CartItem {
	
	private int id;
	private String name;
	private int pcs;
	private double price;
	private boolean sufficient =true;
	
	
	
	public CartItem(){		
		
	}

	public String getId() {
		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append(this.id);
		String strid = sb.toString();
		
		return strid;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getPcs() {
		return pcs;
	}



	public void setPcs(int pcs) {
		this.pcs += pcs;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isSufficient() {
		return sufficient;
	}

	public void setSufficient(boolean sufficient) {
		this.sufficient = sufficient;
	}



	
	
	

	
	
	

}
