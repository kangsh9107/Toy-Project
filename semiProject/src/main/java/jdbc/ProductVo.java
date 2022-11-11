package jdbc;

public class ProductVo {
	String category;
	int    serial;
	String productName;
	int    price;
	int    stock;
	int    salesRate;
	String img;
	
	public String getCategory() { return category; }
	public int getSerial() { return serial; }
	public String getProductName() { return productName; }
	public int getPrice() { return price; }
	public int getStock() { return stock; }
	public int getSalesRate() { return salesRate; }
	public String getImg() { return img; }
	
	public void setCategory(String category) { this.category = category; }
	public void setSerial(int serial) { this.serial = serial; }
	public void setProductName(String productName) { this.productName = productName; }
	public void setPrice(int price) { this.price = price; }
	public void setStock(int stock) { this.stock = stock; }
	public void setSalesRate(int salesRate) {this.salesRate = salesRate;}
	public void setImg(String img) { this.img = img; }
}
