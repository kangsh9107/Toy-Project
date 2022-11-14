package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
	
	// rs, ps, conn close
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
	
	// 카테고리 선택 시 해당 카테고리의 상품 전체 출력
	public List<ProductVo> selectCategory(ProductPage pageVo) {
		if(conn == null) conn = new DBConn().getConn();
		
		List<ProductVo> list = new ArrayList<>();
		
		try {
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
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		close();
		return list;
	}
	
	// 로그인 시 아이디와 비밀번호가 맞는지 체크
	public boolean check(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(conn == null) conn = new DBConn().getConn();
		
		boolean b = false;
		
		try {
			conn = new DBConn().getConn();
			sql = "select * from member where id = ? and pwd = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, req.getParameter("id"));
			ps.setString(2, req.getParameter("pwd"));
			
			rs = ps.executeQuery();
			if(rs.next()) {
				b = true;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return b;
	}
	
	// 상품 선택 시 해당 상품 디테일 출력
	public List<ProductVo> selectCategoryDetail(ProductPage pageVo, ProductVo pVo) {
		if(conn == null) conn = new DBConn().getConn();
		
		List<ProductVo> list = new ArrayList<>();
		
		try {
			sql = "select * from products "
				+ "where serial = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pVo.getSerial());
			
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
		
		close();
		return list;
	}
	
	// 상품 디테일에서 Buy 클릭 시 주문완료. orders 테이블에 주문내용 추가+products 테이블 재고 감소+member 테이블 마일리지 증가
	public List<ProductVo> selectBuyRightNow(ProductPage pageVo, ProductVo pVo) {
		if(conn == null) conn = new DBConn().getConn();
		
		List<ProductVo> list = new ArrayList<>();
		
		try {
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		close();
		return list;
	}

}
