package jdbc;

public class Admin_memberVo {
	String id, pwd, name, gender, postalCode, address1, address2, phone, email;
	int age, point;
	
	public String getId() { return id; }
	public String getPwd() { return pwd; }
	public String getName() { return name; }
	public String getGender() { return gender; }
	public String getPostalCode() { return postalCode; }
	public String getAddress1() { return address1; }
	public String getAddress2() { return address2; }
	public String getPhone() { return phone; }
	public String getEmail() { return email; }
	public int getAge() { return age; }
	public int getPoint() { return point; }
	
	public void setId(String id) { this.id = id; }
	public void setPwd(String pwd) { this.pwd = pwd; }
	public void setName(String name) { this.name = name; }
	public void setGender(String gender) { this.gender = gender; }
	public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
	public void setAddress1(String address1) { this.address1 = address1; }
	public void setAddress2(String address2) { this.address2 = address2; }
	public void setPhone(String phone) { this.phone = phone; }
	public void setEmail(String email) { this.email = email; }
	public void setAge(int age) { this.age = age; }
	public void setPoint(int point) { this.point = point; }
}
