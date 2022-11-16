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
	
	// 카테고리 선택, 검색, 페이지 이동 시 조건에 맞는 상품 출력
	public List<ProductVo> select(ProductPage pageVo) {
		if(conn == null) conn = new DBConn().getConn();
		
		List<ProductVo> list = new ArrayList<>();
		
		try {
			sql = "select count(category) totSize from products "
				+ "where category like ? "
				+ "and   productName like ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + pageVo.getCategory() + "%");
			ps.setString(2, "%" + pageVo.getFindStr().trim() + "%");
			
			rs = ps.executeQuery();
			rs.next();
			int totSize = rs.getInt("totSize");
			pageVo.setTotSize(totSize);
			pageVo.compute();
			
			sql = "select * from products "
				+ "where category like ? "
				+ "and   productName like ? "
				+ "order by serial "
				+ "limit ?, ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + pageVo.getCategory() + "%");
			ps.setString(2, "%" + pageVo.getFindStr().trim() + "%");
			ps.setInt(3, pageVo.getStartNo());
			ps.setInt(4, pageVo.getListSize());
			
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
		
		close();
		return b;
	}
	
	// 상품 선택 시 해당 상품 디테일 출력
	public List<ProductVo> selectCategoryDetail(ProductPage pageVo, ProductVo pVo) {
		if(conn == null) conn = new DBConn().getConn();
		
		List<ProductVo> list = new ArrayList<>();
		
		// 구매를 눌렀을 때 재고가 부족하면 리스트에 더미데이터 1개가 들어가고 list.size()를 비교해서 alert를 띄우고 category_detail에서 리스트에 있는 값들 중 제대로 된 값만 보여준다.
		// 하지만 구매를 눌렀을 때 재고가 부족한 경우가 아니라 상품을 눌러서 바로 디테일로 돌아간 경우
		
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
	
	// 상품 디테일에서 Buy 클릭 시 주문완료. orders 테이블에 주문 추가 + products 테이블 재고 감소, 판매량 증가 + member 테이블 마일리지 증가
	public List<ProductVo> buyRightNow(ProductPage pageVo, ProductVo pVo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(conn == null) conn = new DBConn().getConn();
		
		List<ProductVo> list = new ArrayList<>();
		
		try {
			// 구매하려는 수량이 재고보다 많은 경우 메서드 바로 종료
			sql = "select stock from products where serial = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pVo.getSerial());
			
			rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getInt("stock") - Integer.parseInt(req.getParameter("quantity")) < 0) {
					ProductVo temp = new ProductVo();
					temp.setCategory("");
					temp.setSerial(0);
					temp.setProductName("temp");
					temp.setPrice(0);
					temp.setStock(0);
					temp.setSalesRate(0);
					temp.setImg("");
					
					sql = "select * from products "
						+ "where serial = ?";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, pVo.getSerial());
					
					rs = ps.executeQuery();
					rs.next();
					ProductVo productVo = new ProductVo();
					productVo.setCategory(rs.getString("category"));
					productVo.setSerial(rs.getInt("serial"));
					productVo.setProductName(rs.getString("productName"));
					productVo.setPrice(rs.getInt("price"));
					productVo.setStock(rs.getInt("stock"));
					productVo.setSalesRate(rs.getInt("salesRate"));
					productVo.setImg(rs.getString("img"));
					
					list.add(productVo);
					list.add(temp);
					
					return list;
				}
			}
			
			// 해당 상품의 serial로 products 테이블에서 정보를 가져와 pVo에 set
			sql = "select * from products where serial = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pVo.getSerial());
			
			rs = ps.executeQuery();
			while(rs.next()) {
				pVo.setCategory(rs.getString("category"));
				pVo.setProductName(rs.getString("productName"));
				pVo.setPrice(rs.getInt("price"));
				pVo.setStock(rs.getInt("stock"));
				pVo.setSalesRate(rs.getInt("salesRate"));
				pVo.setImg(rs.getString("img"));
			}
			
			// sessionId와 pVo의 정보로 orders 테이블에 quantity 만큼 주문 추가
			conn.setAutoCommit(false);
			sql = "insert into orders(id, category, serial, "
				+ "productName, price, orderDate) "
				+ "values(?, ?, ?, ?, ?, now())";
			ps = conn.prepareStatement(sql);
			ps.setString(1, (String)req.getAttribute("sessionId"));
			ps.setString(2, pVo.getCategory());
			ps.setInt(3, pVo.getSerial());
			ps.setString(4, pVo.getProductName());
			ps.setInt(5, pVo.getPrice());
			
			boolean b = false;
			int cnt = ps.executeUpdate();
			if(cnt > 0) {
				for(int i=0; i<Integer.parseInt(req.getParameter("quantity"))-1; i++) {
					ps.executeUpdate();
				}
				conn.commit();
				b = true;
			} else {
				conn.rollback();
			}
			
			if(b) {
				// 해당 상품 재고를 구매수량만큼 감소
				sql = "update products set stock = ? "
					+ "where serial = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, pVo.getStock() - Integer.parseInt(req.getParameter("quantity")));
				ps.setInt(2, pVo.getSerial());
				
				int cnt2 = ps.executeUpdate();
				if(cnt2 > 0) {
					conn.commit();
				} else {
					conn.rollback();
				}
				
				// 해당 상품 판매량을 구매수량만큼 증가
				sql = "update products set salesRate = ? "
					+ "where serial = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, pVo.getSalesRate() + Integer.parseInt(req.getParameter("quantity")));
				ps.setInt(2, pVo.getSerial());
				
				cnt2 = ps.executeUpdate();
				if(cnt2 > 0) {
					conn.commit();
				} else {
					conn.rollback();
				}
				
				// sessionId를 사용해 해당 회원의 현재 point를 가져오기
				sql = "select point from member where id = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, (String)req.getAttribute("sessionId"));
				
				rs = ps.executeQuery();
				rs.next();
				int tempPoint = rs.getInt("point");
				
				// 해당 회원이 구매한 금액의 1퍼센트만큼 포인트를 증가
				sql = "update member set point = ? "
					+ "where id = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt( 1, tempPoint + (int)((pVo.getPrice() * Integer.parseInt(req.getParameter("quantity"))) * 0.01) );
				ps.setString( 2, (String)req.getAttribute("sessionId"));
				
				cnt2 = ps.executeUpdate();
				if(cnt2 > 0) {
					conn.commit();
				} else {
					conn.rollback();
				}
			}
			
			// 경우에 따라 내용이 처리되고 다시 상품 디테일 페이지에 해당 상품 출력
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
			
			sql = "select stock from products where serial = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pVo.getSerial());
			
			ProductVo temp = new ProductVo();
			temp.setCategory("");
			temp.setSerial(0);
			temp.setProductName("temp22");
			temp.setPrice(0);
			temp.setStock(0);
			temp.setSalesRate(0);
			temp.setImg("");
			
			list.add(temp);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		close();
		return list;
	}
	
	public List<ProductVo> showBest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(conn == null) conn = new DBConn().getConn();
		
		List<ProductVo> list = new ArrayList<>();
		
		try {			
			sql = " select * from products "
				+ " where category = ? "
				+ " order by salesRate desc "
				+ " limit 1, 8";
			ps = conn.prepareStatement(sql);
			ps.setString(1, (String)req.getAttribute("category"));
			
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
				System.out.println(productVo.getSerial());
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		close();
		return list;
	}

}
