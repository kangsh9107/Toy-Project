package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jdbc.DBConn;

public class OrderDao {
	Connection conn;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public OrderDao() {
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

public List<OrderVo> select(MemberVo memberVo){
	if(conn==null) conn = new DBConn("mydb").getConn();
	List<OrderVo> list = new ArrayList<OrderVo>();
	
	String sql ="";
	try {
		MemberVo mVo = new MemberVo();
		sql =  " select * from orders "
				+ " where id = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, mVo.getId());
		
		rs = ps.executeQuery();
		while(rs.next()) {
			OrderVo orderVo = new OrderVo();
			orderVo.setCategory(rs.getString("category"));
			orderVo.setSERIAL(rs.getInt("serial"));
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
