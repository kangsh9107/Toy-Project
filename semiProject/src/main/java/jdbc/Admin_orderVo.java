package jdbc;

public class Admin_orderVo {
	
	String id, category, orderDate, productName;
	int SERIAL, price, orderNumber, status;
	
	
	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	public String getCategory() { return category; }
	public void setCategory(String category) { this.category = category; }
	public String getProductName() {return productName;}
	public void setProductName(String productName) {this.productName = productName;}
	public String getOrderDate() { return orderDate; }
	public void setOrderDate(String orderDate) { this.orderDate = orderDate; }
	public int getSERIAL() { return SERIAL; }
	public void setSERIAL(int sERIAL) { SERIAL = sERIAL; }
	public int getPrice() { return price; }
	public void setPrice(int price) { this.price = price; }
	public int getOrderNumber() { return orderNumber; }
	public void setOrderNumber(int orderNumber) { this.orderNumber = orderNumber; }
	public int getStatus() { return status; }
	public void setStatus(int status) { this.status = status; }
}
