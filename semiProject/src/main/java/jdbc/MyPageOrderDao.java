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

}
