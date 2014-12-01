package fi.webshop.web.view;

public class CartItem {

	private int id;
	private String name;
	private int pcs=0;
	private double price=0;
	private boolean sufficient = true;
	private double total = 0;
	private String noOryes;
	private int dbamount;

	public CartItem() {

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
		total += pcs* this.price;
		
	}
	
	public double editPcs(int pcs) {
		this.pcs = pcs;
		double resultsum=0;
		resultsum = total-(this.price*pcs);
		this.total = pcs*this.price;
		return resultsum;
		
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

		if (!this.sufficient)
			this.setNoOryes("No sufficient amount of products");

		else
			setNoOryes("OK");

	}

	public double geTotal() {
		return this.total;
	}

	public boolean getIsSufficient() {
		return this.sufficient;
	}

	public String getNoOryes() {
		return noOryes;
	}

	public void setNoOryes(String noOryes) {
		this.noOryes = noOryes;
		
	}

	public int getDbamount() {
		return dbamount;
	}

	public void setDbamount(int dbamount) {
		this.dbamount = dbamount;
	}

}
