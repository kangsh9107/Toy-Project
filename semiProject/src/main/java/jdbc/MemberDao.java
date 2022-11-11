package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDao {
	Connection conn;
	String sql = "";
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public MemberDao() {
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

	public void memberSignup() {
		if(conn == null) conn = new DBConn().getConn();
		
		boolean b = false;
		sql = "insert into member(id,pwd,name,gender,age,postalCode,address1,address2,phone,email) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, "0000aaa");
			ps.setString(2, "1111");
			ps.setString(3, "mmmm");
			ps.setString(4, "m");
			ps.setString(5, "19");
			ps.setString(6, "11111");
			ps.setString(7, "서울");
			ps.setString(8, "서울대입구");
			ps.setString(9, "010-1111-1111");
			ps.setString(10, "abc@naver.com");
			
			int cnt = ps.executeUpdate();
			
			if(cnt > 0) {
				conn.commit();
				b = true;
			} else {
				conn.rollback();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
}
