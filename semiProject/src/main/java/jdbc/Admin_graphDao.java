package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

public class Admin_graphDao {
	Connection conn;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public Admin_graphDao() {
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
	public Admin_graphVo graphView() {
		if(conn == null)   conn = new DBConn().getConn();
		
		Admin_graphVo vo = new Admin_graphVo();		
		try {
			String sql1= "select count(*)-1 as mSize from member where gender='m'";
			String sql2= "select count(*) as fSize from member where gender='f'";
			String sql3= "select count(*) as tenSize from member where age>=10 and age <20";
			String sql4= "select count(*) as twentySize from member where age>=20 and age <30";
			String sql5= "select count(*) as thirtySize from member where age>=30 and age <40";
			String sql6= "select count(*) as fourtySize from member where age>=40 and age <50";
			String sql7= "select count(*) as fiftySize from member where age>=50";
			
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			PreparedStatement ps3 = conn.prepareStatement(sql3);
			PreparedStatement ps4 = conn.prepareStatement(sql4);
			PreparedStatement ps5 = conn.prepareStatement(sql5);
			PreparedStatement ps6 = conn.prepareStatement(sql6);
			PreparedStatement ps7 = conn.prepareStatement(sql7);
			
			ResultSet rs1= ps1.executeQuery();
			ResultSet rs2= ps2.executeQuery();
			ResultSet rs3= ps3.executeQuery();
			ResultSet rs4= ps4.executeQuery();
			ResultSet rs5= ps5.executeQuery();
			ResultSet rs6= ps6.executeQuery();
			ResultSet rs7= ps7.executeQuery();
			
			rs1.next();
			rs2.next();
			rs3.next();
			rs4.next();
			rs5.next();
			rs6.next();
			rs7.next();
			
		    vo.setTitle("전체 회원 통계");
			vo.setmSize(rs1.getInt("mSize"));
			vo.setfSize(rs2.getInt("fSize"));
			vo.setTenSize(rs3.getInt("tenSize"));	
			vo.setTwentySize(rs4.getInt("twentySize"));
			vo.setThirtySize(rs5.getInt("thirtySize"));
			vo.setFourtySize(rs6.getInt("fourtySize"));
			vo.setFiftySize(rs7.getInt("fiftySize"));
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	public Admin_graphVo graphSearch(int SERIAL) {
		if(conn == null)   conn = new DBConn().getConn();
		
		Admin_graphVo vo = new Admin_graphVo();
		
		try {
		String sql1 = "select count(*) as mSize from member m join orders o "
				    + "on m.id = o.id "
				    + "where SERIAL= ? and gender='m'";
		String sql2 = "select count(*) as fSize from member m join orders o "
				    + "on m.id = o.id "
				    + "where SERIAL= ? and gender='f'";
		String sql3 = "select count(*) as tenSize from member m join orders o "
				    + "on m.id = o.id "
				    + "where SERIAL= ? and age>=10 and age <20";
		String sql4 = "select count(*) as twentySize from member m join orders o "
			        +"on m.id = o.id "
			        + "where SERIAL= ? and age>=20 and age <30";
		String sql5 = "select count(*) as thirtySize from member m join orders o "
			        + "on m.id = o.id "
			        + "where SERIAL= ? and age>=30 and age <40";
		String sql6 = "select count(*) as fourtySize from member m join orders o "
			        + "on m.id = o.id "
			        + "where SERIAL= ? and age>=40 and age <50";
		String sql7 = "select count(*) as fiftySize from member m join orders o "
			        + "on m.id = o.id "
			        + "where SERIAL= ? and age>=50";
		String sql8 = "select productName as productName from products where serial =? ";
		
		conn.setAutoCommit(true);
		PreparedStatement ps1 = conn.prepareStatement(sql1);
		PreparedStatement ps2 = conn.prepareStatement(sql2);
		PreparedStatement ps3 = conn.prepareStatement(sql3);
		PreparedStatement ps4 = conn.prepareStatement(sql4);
		PreparedStatement ps5 = conn.prepareStatement(sql5);
		PreparedStatement ps6 = conn.prepareStatement(sql6);
		PreparedStatement ps7 = conn.prepareStatement(sql7);
		PreparedStatement ps8 = conn.prepareStatement(sql8);
		
		ps1.setInt(1, SERIAL);
		ps2.setInt(1, SERIAL);
		ps3.setInt(1, SERIAL);
		ps4.setInt(1, SERIAL);
		ps5.setInt(1, SERIAL);
		ps6.setInt(1, SERIAL);
		ps7.setInt(1, SERIAL);
		ps8.setInt(1, SERIAL);
		
		ResultSet rs1= ps1.executeQuery();
		ResultSet rs2= ps2.executeQuery();
		ResultSet rs3= ps3.executeQuery();
		ResultSet rs4= ps4.executeQuery();
		ResultSet rs5= ps5.executeQuery();
		ResultSet rs6= ps6.executeQuery();
		ResultSet rs7= ps7.executeQuery();
		ResultSet rs8= ps8.executeQuery();
		
		rs1.next();
		rs2.next();
		rs3.next();
		rs4.next();
		rs5.next();
		rs6.next();
		rs7.next();
		rs8.next();
		
		vo.setmSize(rs1.getInt("mSize"));
		vo.setfSize(rs2.getInt("fSize"));
		vo.setTenSize(rs3.getInt("tenSize"));	
		vo.setTwentySize(rs4.getInt("twentySize"));
		vo.setThirtySize(rs5.getInt("thirtySize"));
		vo.setFourtySize(rs6.getInt("fourtySize"));
		vo.setFiftySize(rs7.getInt("fiftySize"));
		vo.setTitle(rs8.getString("productName")+"상품 주문통계");
		
	

		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return vo;
	}
	

}
