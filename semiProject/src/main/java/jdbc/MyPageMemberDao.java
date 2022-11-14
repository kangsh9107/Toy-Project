package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MyPageMemberDao {
	Connection conn;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public MyPageMemberDao() {
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
	//List<Mypage_mymainvo> list = new ArrayList<Mypage_mymainvo>();
	
	public Mypage_mymainvo Mymainselect(String id){
		if(conn == null) conn = new DBConn().getConn();
		Mypage_mymainvo mpmmVo = new Mypage_mymainvo();
		String sql1 = " select count(*) from orders where id=? ";
		String sql2 = " select sum(price) from orders where id=? ";
		String sql3 = " select point from member where id=?";
		String sql4 = " select name from member where id=?";
		try {
			PreparedStatement ps1=conn.prepareStatement(sql1);
			PreparedStatement ps2=conn.prepareStatement(sql2);
			PreparedStatement ps3=conn.prepareStatement(sql3);
			PreparedStatement ps4=conn.prepareStatement(sql4);
			ps1.setString(1,id);
			ps2.setString(1,id);
			ps3.setString(1,id);
			ps4.setString(1, id);	
			ResultSet rs1=ps1.executeQuery();
			ResultSet rs2=ps2.executeQuery();
			ResultSet rs3=ps3.executeQuery();
			ResultSet rs4=ps4.executeQuery();
			rs1.next();
			rs2.next();
			rs3.next();
			rs4.next();
			mpmmVo.setCountorder(rs1.getInt("count(*)"));
			mpmmVo.setTotalprice(rs2.getInt("sum(price)"));
			mpmmVo.setPoint(rs3.getInt("point"));
			mpmmVo.setName(rs4.getString("name"));
			
			System.out.println(mpmmVo.getCountorder());
			System.out.println(mpmmVo.getTotalprice());
			System.out.println(mpmmVo.getPoint());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mpmmVo;
	}
	
	public boolean quitRR(String id, String pwd) {
		boolean b = false;
		if(conn==null) conn = new DBConn().getConn();
		String sql = "delete from member where id = ? and pwd = ?";
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);
			
			int cnt = ps.executeUpdate();
			if(cnt>0) {
				b=true;
				conn.commit();
			}else {
				conn.rollback();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		close();
		return b;
	}
}
