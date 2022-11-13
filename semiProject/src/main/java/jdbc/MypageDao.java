package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.exceptions.RSAException;

import jdbc.DBConn;



public class MypageDao {
	Connection conn;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public MypageDao() {
		try {
			conn = new DBConn().getConn();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public List<Mypage_orderVo> select(String id){
		if(conn == null) conn = new DBConn().getConn();
		
		List<Mypage_orderVo> list = new ArrayList<Mypage_orderVo>();
		String sql = "";
		
		try {
			sql = " select * from orders where id=? ";
			ps=conn.prepareStatement(sql);
			ps.setString(1,id);
			rs=ps.executeQuery();
			rs.next();
			while(rs.next()) {
				Mypage_orderVo vo = new Mypage_orderVo();
				vo.setCategory (rs.getString("category"));
				vo.setSERIAL (rs.getInt("SERIAL"));
				vo.setPrice(rs.getInt("price"));
				vo.setOrderDate(rs.getString("orderDate"));
				vo.setStatus(rs.getInt("status"));
				
				list.add(vo);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}

}
