package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin_mainDao {
	Connection conn;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public Admin_mainDao() {
		try {
			conn = new DBConn().getConn();
		} catch (Exception ex) {
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
	public Admin_mainVo mainView() {
		if(conn == null)   conn = new DBConn().getConn();
		
		Admin_mainVo vo = new Admin_mainVo();		
		try {
			String sql1 = "select sum(price) as today_tot from orders where orderDate = DATE_FORMAT(now(), '%Y-%m-%d')";
			String sql2 = "select sum(price) as weekly_tot from orders where orderDate BETWEEN DATE_ADD(NOW(),INTERVAL -1 WEEK ) AND NOW()";
			String sql3 = "select sum(price) as monthly_tot from orders where orderDate BETWEEN DATE_ADD(NOW(),INTERVAL -1 month ) AND NOW()";
			String sql4 = "select round(rand()*1000) as today_visit";
			String sql5 = "select count(*) as today_orders from orders where orderDate = DATE_FORMAT(now(), '%Y-%m-%d')";
			String sql6 = "SELECT count(*) as st_1 FROM orders WHERE STATUS = 1"; 
			String sql7 = "SELECT count(*) as st_2 FROM orders WHERE STATUS = 2";
			String sql8 = "SELECT count(*) as st_3 FROM orders WHERE STATUS = 3";
			String sql9 = "SELECT count(*) as st_4 FROM orders WHERE STATUS = 4";
		
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			PreparedStatement ps3 = conn.prepareStatement(sql3);
			PreparedStatement ps4 = conn.prepareStatement(sql4);
			PreparedStatement ps5 = conn.prepareStatement(sql5);
			PreparedStatement ps6 = conn.prepareStatement(sql6);
			PreparedStatement ps7 = conn.prepareStatement(sql7);
			PreparedStatement ps8 = conn.prepareStatement(sql8);
			PreparedStatement ps9 = conn.prepareStatement(sql9);
			
			ResultSet rs1= ps1.executeQuery();
			ResultSet rs2= ps2.executeQuery();
			ResultSet rs3= ps3.executeQuery();
			ResultSet rs4= ps4.executeQuery();
			ResultSet rs5= ps5.executeQuery();
			ResultSet rs6= ps6.executeQuery();
			ResultSet rs7= ps7.executeQuery();
			ResultSet rs8= ps8.executeQuery();
			ResultSet rs9= ps9.executeQuery();
			
			rs1.next();
			rs2.next();
			rs3.next();
			rs4.next();
			rs5.next();
			rs6.next();
			rs7.next();
			rs8.next();
			rs9.next();
			
			vo.setToday_tot(rs1.getInt("today_tot"));
			vo.setWeekly_tot(rs2.getInt("weekly_tot"));
			vo.setMonthly_tot(rs3.getInt("monthly_tot"));
			vo.setToday_visit(rs4.getInt("today_visit"));
			vo.setToday_orders(rs5.getInt("today_orders"));
			vo.setStatus1(rs6.getInt("st_1"));
			vo.setStatus2(rs7.getInt("st_2"));
			vo.setStatus3(rs8.getInt("st_3"));
			vo.setStatus4(rs9.getInt("st_4"));
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		close();
		return vo;
	}
}
