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
			if(pageVo.getJob().equals("select")      ||
			   pageVo.getCategory().equals("outer")  ||
			   pageVo.getCategory().equals("top")    ||
			   pageVo.getCategory().equals("bottom") ||
			   pageVo.getCategory().equals("shoes")  ||
			   pageVo.getCategory().equals("acc")      ) {
				sql = "select count(serial) totSize from products "
					+ "where category = ?";
				
				ps = conn.prepareStatement(sql);
				ps.setString(1, pageVo.getCategory());
				rs = ps.executeQuery();
				rs.next();
				int totSize = rs.getInt("totSize");
				pageVo.setTotSize(totSize);
				pageVo.compute();
				
				sql = "select * from products "
					+ "where category = ? "
					+ "order by serial "
					+ "limit ?, ?";

				ps = conn.prepareStatement(sql);
				ps.setString(1, pageVo.getCategory());
				ps.setInt(2, pageVo.getStartNo());
				ps.setInt(3, pageVo.getListSize());
				
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
			} else if(pageVo.getJob().equals("search")) {
				sql = "select count(serial) totSize from products "
					+ "where serial like ? "
					+ "or    productName like ?";
				
				ps = conn.prepareStatement(sql);
				
				sql = "select * from products "
					+ "where serial like ? "
					+ "or    productName like ? "
					+ "order by serial "
					+ "limit ?, ?";
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		close();
		return list;
	}

}
