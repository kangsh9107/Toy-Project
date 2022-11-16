package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jdbc.DBConn;

public class MyPageOrderDao {
	Connection conn;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public MyPageOrderDao() {
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

	/*
	public List<OrderVo> select(String id){
		if(conn==null) conn = new DBConn().getConn();
		List<OrderVo> list = new ArrayList<OrderVo>();
		
		String sql ="";
		try {
			sql =  " select * from orders "
					+ " where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				OrderVo orderVo = new OrderVo();
				
				orderVo.setCategory(rs.getString("category"));
				orderVo.setSERIAL(rs.getInt("SERIAL"));
				orderVo.setPrice(rs.getInt("price"));
				orderVo.setOrderDate(rs.getString("orderDate"));
				orderVo.setStatus(rs.getInt("status"));
				orderVo.setOrderNumber(rs.getInt("orderNumber"));
				list.add(orderVo);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		close();
		return list;
	}
	*/
	
	public List<OrderVo> select(String id){
	      if(conn==null) conn = new DBConn().getConn();
	      List<OrderVo> list = new ArrayList<OrderVo>();
	      
	      String sql ="";
	      try {
	         OrderVo orderVo = new OrderVo();
	         sql =  " select * from orders "
	               + " where id = ?";
	         ps = conn.prepareStatement(sql);
	         ps.setString(1, id);
	         
	         rs = ps.executeQuery();
	         while(rs.next()) {
	            orderVo.setCategory(rs.getString("category"));
	            orderVo.setSERIAL(rs.getInt("SERIAL"));
	            orderVo.setPrice(rs.getInt("price"));
	            orderVo.setOrderDate(rs.getString("orderDate"));
	            orderVo.setStatus(rs.getInt("status"));
	            
	            list.add(orderVo);
	         }
	      }catch(Exception ex) {
	         ex.printStackTrace();
	      }
	      close();
	      return list;
	   }
	
	public List<OrderVo> select(String id, Page pageVo){
		if(conn==null) conn = new DBConn().getConn();
		List<OrderVo> list = new ArrayList<OrderVo>();
		
		String sql ="";
		try {
			sql = "select count(id) totSize from orders where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			rs.next();
			int totSize = rs.getInt("totSize");
			pageVo.setTotSize(totSize);
			pageVo.compute();
			
			sql =  " select * from orders "
					+ " where id = ?"
					+ " order by orderDate desc, status asc"
					+ " limit ?, ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setInt(2, pageVo.getStartNo());
			ps.setInt(3, pageVo.getListSize());
			rs = ps.executeQuery();
			while(rs.next()) {
				OrderVo orderVo = new OrderVo();
				orderVo.setCategory(rs.getString("category"));
				orderVo.setSERIAL(rs.getInt("SERIAL"));
				orderVo.setPrice(rs.getInt("price"));
				orderVo.setOrderDate(rs.getString("orderDate"));
				orderVo.setStatus(rs.getInt("status"));
				orderVo.setOrderNumber(rs.getInt("orderNumber"));
				list.add(orderVo);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		close();
		return list;
	}


	public List<OrderVo> search(String id, Page pageVo){
		if(conn==null) conn = new DBConn().getConn();
		List<OrderVo> list = new ArrayList<OrderVo>();
		
		String sql ="";
		try {
			sql =  " select count(SERIAL) totSize from orders "
					+ " where id = ?"
					+ " and   (category like ?"
					+ " or    SERIAL like ?"
					+ " or    price like ?"
					+ " or    orderDate like ?"
					+ " or    status like ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, "%" + pageVo.getFindStr() + "%");
			ps.setString(3, "%" + pageVo.getFindStr() + "%");
			ps.setString(4, "%" + pageVo.getFindStr() + "%");
			ps.setString(5, "%" + pageVo.getFindStr() + "%");
			ps.setString(6, "%" + pageVo.getFindStr() + "%");

			rs = ps.executeQuery();
			rs.next();
			int totSize = rs.getInt("totSize");
			pageVo.setTotSize(totSize);
			pageVo.compute();
			
			sql =  " select * from orders "
					+ " where id = ?"
					+ " and   (category like ?"
					+ " or    SERIAL like ?"
					+ " or    price like ?"
					+ " or    orderDate like ?"
					+ " or    status like ?)"
					+ " order by orderDate desc, status asc"
					+ " limit ?, ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, "%" + pageVo.getFindStr() + "%");
			ps.setString(3, "%" + pageVo.getFindStr() + "%");
			ps.setString(4, "%" + pageVo.getFindStr() + "%");
			ps.setString(5, "%" + pageVo.getFindStr() + "%");
			ps.setString(6, "%" + pageVo.getFindStr() + "%");
			ps.setInt(7, pageVo.getStartNo());
			ps.setInt(8, pageVo.getListSize());
			rs = ps.executeQuery();
			while(rs.next()) {
				OrderVo orderVo = new OrderVo();
				orderVo.setCategory(rs.getString("category"));
				orderVo.setSERIAL(rs.getInt("SERIAL"));
				orderVo.setPrice(rs.getInt("price"));
				orderVo.setOrderDate(rs.getString("orderDate"));
				orderVo.setStatus(rs.getInt("status"));
				orderVo.setOrderNumber(rs.getInt("orderNumber"));
				list.add(orderVo);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		close();
		return list;
	}

	
	public void myrefund(int orderNumber) {
	      if(conn == null) conn = new DBConn().getConn();
	      String sql = " update orders set status=4 where orderNumber=? ";
	      try {
	    	 conn.setAutoCommit(false);
	         PreparedStatement ps=conn.prepareStatement(sql);
	         ps.setInt(1, orderNumber);   
	         int cnt = ps.executeUpdate();
				if(cnt>0) {
					conn.commit();
				}else {
					conn.rollback();
				}
				
	      }catch(Exception ex) {
	         ex.printStackTrace();
	      }
	   }
	
	public OrderVo showOrderDetails(String orderNumber) {
		if(conn==null) conn = new DBConn().getConn();
		String sql ="";
		OrderVo orderVo = new OrderVo();
		try {
			sql =  " select * from orders "
					+ " where orderNumber = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNumber);
			rs = ps.executeQuery();
			if(rs.next()) {
				orderVo.setId(rs.getString("id"));
				orderVo.setCategory(rs.getString("category"));
				orderVo.setSERIAL(rs.getInt("SERIAL"));
				orderVo.setPrice(rs.getInt("price"));
				orderVo.setOrderDate(rs.getString("orderDate"));
				orderVo.setStatus(rs.getInt("status"));
				orderVo.setOrderNumber(rs.getInt("orderNumber"));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		close();
		return orderVo;

	}

}
