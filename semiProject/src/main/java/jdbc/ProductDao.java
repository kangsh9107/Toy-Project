package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
	Connection      conn = null;
	PreparedStatement ps = null;
	ResultSet         rs = null;
	String           sql = "";
	
	public ProductDao() {
		try {
			conn = new DBConn().getConn();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if(rs   != null) rs.close();
			if(ps   != null) ps.close();
			if(conn != null) conn.close();
			
			rs   = null;
			ps   = null;
			conn = null;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public List<ProductVo> select(ProductPage pageVo) {
		if(conn == null) conn = new DBConn().getConn();
		
		List<ProductVo> list = new ArrayList<>();
		
		try {
			sql = "select count(serial) totSize from products "
				+ "where category = ? "
				+ "or    serial = ? "
				+ "or    productName = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, pageVo.getCategory());
			
			rs = ps.executeQuery();
			rs.next();
			int totSize = rs.getInt("totSize");
			pageVo.setTotSize(totSize);
			pageVo.compute();
			
			sql = "select * from products where category = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, pageVo.category);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				ProductVo productVo = new ProductVo();
				productVo.setCategory(rs.getString("category"));
				productVo.setSerial(rs.getInt("serial"));
				productVo.setProductName(rs.getString("productName"));
				productVo.setPrice(rs.getInt("price"));
				productVo.setStock(rs.getInt("stock"));
				productVo.setSalesRate(rs.getInt("salesRate"));
				productVo.setImg(rs.getString("img"));
				
				list.add(productVo);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return list;
	}

}
