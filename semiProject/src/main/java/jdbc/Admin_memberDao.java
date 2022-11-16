package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;



public class Admin_memberDao {
	Connection conn;
	String sql = "";
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public Admin_memberDao() {
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
	
	public List<Admin_memberVo> memberSearch(Page pageVo) {
		if(conn == null)   conn = new DBConn().getConn();
		
		List<Admin_memberVo> list = new ArrayList<>();
		
		try {
			/* 검색된 전체 건수를 가져온다 */
			// 검색된 전체 건수를 가져오는 where 조건은 검색의 where 조건과 같아야 한다
			sql = "select count(id) totSize from member "
				+ "where id like ? "
				+ "or    name like ? "
				+ "or    gender like ? "
				+ "or    age like ? "
				+ "or    postalCode like ? "
				+ "or    address1 like ? "
				+ "or    address2 like ? "
				+ "or    phone like ? "
				+ "or    email like ?"
				+ "or    point like ? ";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + pageVo.getFindStr() + "%");
			ps.setString(2, "%" + pageVo.getFindStr() + "%");
			ps.setString(3, "%" + pageVo.getFindStr() + "%");
			ps.setString(4, "%" + pageVo.getFindStr() + "%");
			ps.setString(5, "%" + pageVo.getFindStr() + "%");
			ps.setString(6, "%" + pageVo.getFindStr() + "%");
			ps.setString(7, "%" + pageVo.getFindStr() + "%");
			ps.setString(8, "%" + pageVo.getFindStr() + "%");
			ps.setString(9, "%" + pageVo.getFindStr() + "%");
			ps.setString(10, "%" + pageVo.getFindStr() + "%");
			
			rs = ps.executeQuery();
			rs.next();
			int totSize = rs.getInt("totSize");
			pageVo.setTotSize(totSize);
			pageVo.compute();
			
			/* 검색 */
			sql = "select * from member "
					+ "where id like ? "
					+ "or    name like ? "
					+ "or    gender like ? "
					+ "or    age like ? "
					+ "or    postalCode like ? "
					+ "or    address1 like ? "
					+ "or    address2 like ? "
					+ "or    phone like ? "
					+ "or    email like ?"
					+ "or    point like ? "
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
			ps.setString(9, "%" + pageVo.getFindStr() + "%");
			ps.setString(10, "%" + pageVo.getFindStr() + "%");
			ps.setInt(11, pageVo.getStartNo());
			ps.setInt(12, pageVo.getListSize());
			
			rs = ps.executeQuery();
			while(rs.next()) {
				Admin_memberVo vo = new Admin_memberVo();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setAge(rs.getInt("age"));
				vo.setPostalCode(rs.getString("postalCode"));
				vo.setAddress1(rs.getString("address1"));
				vo.setAddress2(rs.getString("address2"));
				vo.setPhone(rs.getString("phone"));
				vo.setEmail(rs.getString("email"));
				vo.setPoint(rs.getInt("point"));				
				
				list.add(vo);
			}
		
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		close();
		return list;
	}
	
	public Admin_memberVo memberView(String id) {
		if(conn == null)   conn = new DBConn().getConn();
		
		Admin_memberVo vo = new Admin_memberVo();
		String sql = "select * from member where id = ?";
		
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setAge(rs.getInt("age"));
				vo.setPostalCode(rs.getString("postalCode"));
				vo.setAddress1(rs.getString("address1"));
				vo.setAddress2(rs.getString("address2"));
				vo.setPhone(rs.getString("phone"));
				vo.setEmail(rs.getString("email"));
				vo.setPoint(rs.getInt("point"));
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		close();
		
		return vo;
	}
	
	public boolean modify(HttpServletRequest req) throws ServletException, IOException  {
		if(conn == null) conn = new DBConn().getConn();

		boolean b=false;
		String sql = " update member set name=?, gender=?, age=?, postalCode=?, "
		           + " address1=?, address2=?, phone=?, email=?, point=? " 
				   + " where id=? ";
		try {
			if(conn == null) System.out.println("conn is null");
			if(ps == null) System.out.println("ps is null");
			
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, req.getParameter("name"));
			ps.setString(2, req.getParameter("gender"));
			ps.setString(3, req.getParameter("age"));
			ps.setString(4, req.getParameter("postalCode"));
			ps.setString(5, req.getParameter("address1"));
			ps.setString(6, req.getParameter("address2"));
			ps.setString(7, req.getParameter("phone"));
			ps.setString(8, req.getParameter("email"));
			ps.setString(9, req.getParameter("potin"));
			ps.setString(10, req.getParameter("id"));
			
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
		return b;		
	}
	
	public boolean delete(HttpServletRequest req) throws ServletException, IOException {
		if(conn == null) conn = new DBConn().getConn();
		
		boolean b=false;
		String sql = "delete from member where id=? ";
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, req.getParameter("id"));
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
		return b;
	}
}
