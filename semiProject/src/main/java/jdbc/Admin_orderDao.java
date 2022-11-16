package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jdbc.DBConn;

public class Admin_orderDao {
	Connection conn;
	String sql="";
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public Admin_orderDao() {
		try {
			conn = new DBConn().getConn();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(conn != null) conn.close();
			
			rs = null;
			ps = null;
			conn = null;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

public List<Admin_orderVo> orderSearch(Page pageVo){
	if(conn==null) conn = new DBConn().getConn();
	List<Admin_orderVo> list = new ArrayList<Admin_orderVo>();
	
	String sql = "";
	ps = null;
	rs = null;
	
	try {
		sql = "select count(orderNumber) totSize from orders "
			+ "where orderNumber like ? "
			+ "or    id like ? "
			+ "or    category like ? "
			+ "or    SERIAL like ? "
			+ "or    productName like ? "
			+ "or    price like ? "
			+ "or    orderDate like ? "
			+ "or    status like ? ";
		
		ps = conn.prepareStatement(sql);
		ps.setString(1, "%" + pageVo.getFindStr() + "%");
		ps.setString(2, "%" + pageVo.getFindStr() + "%");
		ps.setString(3, "%" + pageVo.getFindStr() + "%");
		ps.setString(4, "%" + pageVo.getFindStr() + "%");
		ps.setString(5, "%" + pageVo.getFindStr() + "%");
		ps.setString(6, "%" + pageVo.getFindStr() + "%");
		ps.setString(7, "%" + pageVo.getFindStr() + "%");
		ps.setString(8, "%" + pageVo.getFindStr() + "%");
		
		rs = ps.executeQuery();
		rs.next();
		int totSize = rs.getInt("totSize");
		pageVo.setTotSize(totSize);
		pageVo.compute();
		
		/* 검색 */
		sql = "select * from orders "
				+ "where orderNumber like ? "
				+ "or    id like ? "
				+ "or    category like ? "
				+ "or    SERIAL like ? "
				+ "or    productName like ? "
				+ "or    price like ? "
				+ "or    orderDate like ? "
				+ "or    status like ? "
			+ "order by status desc,orderNumber "
			+ "limit ?, ?";
		
		ps = conn.prepareStatement(sql);
		ps.setString(1, "%" + pageVo.getFindStr() + "%");
		ps.setString(2, "%" + pageVo.getFindStr() + "%");
		ps.setString(3, "%" + pageVo.getFindStr() + "%");
		ps.setString(4, "%" + pageVo.getFindStr() + "%");
		ps.setString(5, "%" + pageVo.getFindStr() + "%");
		ps.setString(6, "%" + pageVo.getFindStr() + "%");
		ps.setString(7, "%" + pageVo.getFindStr() + "%");
		ps.setString(8, "%" + pageVo.getFindStr() + "%");
		ps.setInt(9, pageVo.getStartNo());
		ps.setInt(10, pageVo.getListSize());
		
		rs = ps.executeQuery();
		while(rs.next()) {
			Admin_orderVo vo = new Admin_orderVo();
			vo.setOrderNumber(rs.getInt("orderNumber"));
			vo.setId(rs.getString("id"));
			vo.setCategory(rs.getString("category"));
			vo.setSERIAL(rs.getInt("SERIAL"));
			vo.setProductName(rs.getString("productName"));
			vo.setPrice(rs.getInt("price"));
			vo.setOrderDate(rs.getString("orderDate"));
			vo.setStatus(rs.getInt("status"));
		
			list.add(vo);
		}
	
	} catch(Exception ex) {
		ex.printStackTrace();
	}
	close();
	return list;
   } 
   
public Admin_orderVo orderView(String orderNumber) {
	if(conn == null)   conn = new DBConn().getConn();
	Admin_orderVo vo = new Admin_orderVo();
	String sql = "select * from orders where orderNumber = ?";
	
	try {
		conn.setAutoCommit(false);
		ps = conn.prepareStatement(sql);
		ps.setString(1, orderNumber);
		
		rs = ps.executeQuery();
		
		while(rs.next()) {
			vo.setOrderNumber(rs.getInt("orderNumber"));
			vo.setId(rs.getString("id"));
			vo.setCategory(rs.getString("category"));
			vo.setSERIAL(rs.getInt("SERIAL"));
			vo.setProductName(rs.getString("productName"));
			vo.setPrice(rs.getInt("price"));
			vo.setOrderDate(rs.getString("orderDate"));
			vo.setStatus(rs.getInt("status"));
		}
	} catch(Exception ex) {
		ex.printStackTrace();
	}
	close();
	
	return vo;
    }
    
    //user 환불 요청
	public boolean modify(HttpServletRequest req) throws ServletException, IOException {
		if (conn == null)
			conn = new DBConn().getConn();
		
		boolean b = false;
		String sql = " UPDATE orders SET STATUS = 4 WHERE orderNumber=? ";
		
		try {
			if (conn == null)
				System.out.println("conn is null");
			if (ps == null)
				System.out.println("ps is null");
			
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, req.getParameter("orderNumber"));
			
			int cnt = ps.executeUpdate();
			if (cnt > 0) {
				b = true;
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
		
	}
	// 환불 승인, 주문 삭제
		public boolean delete(HttpServletRequest req) throws ServletException, IOException {
			if (conn == null)
				conn = new DBConn().getConn();
		
			boolean b = false;
			String sql = "delete from orders where orderNumber=? ";
			try {
				conn.setAutoCommit(false);
				ps = conn.prepareStatement(sql);
				ps.setString(1, req.getParameter("orderNumber"));
				int cnt = ps.executeUpdate();
				if (cnt > 0) {
					b = true;
					conn.commit();
				} else {
					conn.rollback();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return b;
		}

}
