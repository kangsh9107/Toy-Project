package jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
	
	public boolean myModifyR(HttpServletRequest req,HttpServletResponse resp) {
		if(conn == null) conn = new DBConn().getConn();
		
		boolean b = false;
		String sql = " UPDATE member SET name=?, gender=?, age=?, postalCode=?, address1=?, "
				+ " address2=?, phone=?, email=?, point=?   WHERE id=? and pwd=? ";
		try {
			
			conn.setAutoCommit(false);
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,req.getParameter("name"));
			ps.setString(2,req.getParameter("gender"));
			ps.setString(3,req.getParameter("age"));
			ps.setString(4,req.getParameter("postalCode"));
			ps.setString(5,req.getParameter("address1"));
			ps.setString(6,req.getParameter("address2"));
			ps.setString(7,req.getParameter("phone"));
			ps.setString(8,req.getParameter("email"));
			ps.setString(9,req.getParameter("point"));
			ps.setString(10,req.getParameter("id"));
			ps.setString(11,req.getParameter("pwd"));
		
			int cnt = ps.executeUpdate();
			if(cnt>0) {
				b=true;
				conn.commit();
				PrintWriter out = resp.getWriter();
				out.print("<script>");
				out.print("alert('success update yours');");
				out.print("history.back();");	//입력폼으로 다시 이동
				out.print("</script>");
			}else {
				conn.rollback();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return b;
		
	}
	public MemberVo myModify(String id) {
		if(conn == null) conn = new DBConn().getConn();
		MemberVo mVo = new MemberVo();
		String sql = " select * from member where id=? ";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			mVo.setId(rs.getString("id"));
			mVo.setPwd(rs.getString("pwd"));			
			mVo.setName(rs.getString("name"));
			mVo.setGender(rs.getString("gender"));
			mVo.setAge(rs.getInt("age"));
			mVo.setPostalCode(rs.getString("postalcode"));
			mVo.setAddress1(rs.getString("address1"));
			mVo.setAddress2(rs.getString("address2"));
			mVo.setPhone(rs.getString("phone"));
			mVo.setEmail(rs.getString("email"));
			mVo.setPoint(rs.getInt("point"));
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return mVo;
	}
}
