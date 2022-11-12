package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

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
	
	public List<ProductVo> select(ProductPage pageVo, HttpServletRequest req) throws ServletException, IOException {
		if(conn == null) conn = new DBConn().getConn();
		
		List<ProductVo> list = new ArrayList<>();
		
		try {
			if(req.getParameter("job").equals("search"))
			sql = "select count(serial) totSize from products "
				+ "where category = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, pageVo.getCategory());
			rs = ps.executeQuery();
			rs.next();
			int totSize = rs.getInt("totSize");
			pageVo.setTotSize(totSize);
			pageVo.compute();
			
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
		
		close();
		return list;
	}

}
