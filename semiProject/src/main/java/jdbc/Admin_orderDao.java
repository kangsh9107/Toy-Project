package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
		/* 검색된 전체 건수를 가져온다 */
		// 검색된 전체 건수를 가져오는 where 조건은 검색의 where 조건과 같아야 한다
		sql = "select count(id) totSize from orders "
			+ "where id like ? "
			+ "or    category like ? "
			+ "or    SERIAL like ? "
			+ "or    productName like ? "
			+ "or    price like ? "
			+ "or    orderNumber like ? "
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
				+ "where id like ? "
				+ "or    category like ? "
				+ "or    SERIAL like ? "
				+ "or    productName like ? "
				+ "or    price like ? "
				+ "or    orderNumber like ? "
				+ "or    orderDate like ? "
				+ "or    status like ? "
			+ "order by id "
			+ "limit ?, ?";
	
		//conn.setAutoCommit(false); // DML이 아니라 DQL이라 굳이 필요 없다
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
			vo.setId(rs.getString("id"));
			vo.setCategory(rs.getString("category"));
			vo.setSERIAL(rs.getInt("SERIAL"));
			vo.setProductName(rs.getString("productName"));
			vo.setPrice(rs.getInt("price"));
			vo.setOrderNumber(rs.getInt("orderNumber"));
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
   
public Admin_orderVo orderView(String id) {
	if(conn == null)   conn = new DBConn().getConn();
	
	Admin_orderVo vo = new Admin_orderVo();
	String sql = "select * from orders where id = ?";
	
	try {
		conn.setAutoCommit(false);
		ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		
		rs = ps.executeQuery();
		while(rs.next()) {
			vo.setId(rs.getString("id"));
			vo.setCategory(rs.getString("category"));
			vo.setSERIAL(rs.getInt("SERIAL"));
			vo.setProductName(rs.getString("productName"));
			vo.setPrice(rs.getInt("price"));
			vo.setOrderNumber(rs.getInt("orderNumber"));
			vo.setOrderDate(rs.getString("orderDate"));
			vo.setStatus(rs.getInt("status"));
		}
	} catch(Exception ex) {
		ex.printStackTrace();
	}
	close();
	
	return vo;
}

}
