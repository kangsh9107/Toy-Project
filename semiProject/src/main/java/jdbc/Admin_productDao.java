package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Admin_productDao {
	Connection conn;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public Admin_productDao() {
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
	
	public List<Admin_productVo> productSearch(Page pageVo) {
		if(conn == null)   conn = new DBConn().getConn();
		
		List<Admin_productVo> list = new ArrayList<>();
		
		String sql = "";
		ps = null;
		rs = null;
		
		try {
			/* 검색된 전체 건수를 가져온다 */
			// 검색된 전체 건수를 가져오는 where 조건은 검색의 where 조건과 같아야 한다
			sql = "select count(SERIAL) totSize from products "
				+ "where SERIAL like ? "
				+ "or    category like ? "
				+ "or    productName like ? "
				+ "or    price like ? "
				+ "or    stock like ? "
				+ "or    salesRate like ? ";
	
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + pageVo.getFindStr() + "%");
			ps.setString(2, "%" + pageVo.getFindStr() + "%");
			ps.setString(3, "%" + pageVo.getFindStr() + "%");
			ps.setString(4, "%" + pageVo.getFindStr() + "%");
			ps.setString(5, "%" + pageVo.getFindStr() + "%");
			ps.setString(6, "%" + pageVo.getFindStr() + "%");
			
			rs = ps.executeQuery();
			rs.next();
			int totSize = rs.getInt("totSize");
			pageVo.setTotSize(totSize);
			pageVo.compute();
			
			/* 검색 */
			sql = "select * from products "
					+ "where SERIAL like ? "
					+ "or    category like ? "
					+ "or    productName like ? "
					+ "or    price like ? "
					+ "or    stock like ? "
					+ "or    salesRate like ? "
				+ "order by SERIAL "
				+ "limit ?, ?";
		
			//conn.setAutoCommit(false); // DML이 아니라 DQL이라 굳이 필요 없다
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + pageVo.getFindStr() + "%");
			ps.setString(2, "%" + pageVo.getFindStr() + "%");
			ps.setString(3, "%" + pageVo.getFindStr() + "%");
			ps.setString(4, "%" + pageVo.getFindStr() + "%");
			ps.setString(5, "%" + pageVo.getFindStr() + "%");
			ps.setString(6, "%" + pageVo.getFindStr() + "%");
			ps.setInt(7, pageVo.getStartNo());
			ps.setInt(8, pageVo.getListSize());
			
			rs = ps.executeQuery();
			while(rs.next()) {
				Admin_productVo vo = new Admin_productVo();
				vo.setSerial(rs.getInt("SERIAL"));
				vo.setCategory(rs.getString("category"));
				vo.setProductName(rs.getString("productName"));
				vo.setPrice(rs.getInt("price"));
				vo.setStock(rs.getInt("stock"));
				vo.setSalesRate(rs.getInt("salesRate"));			
				
				list.add(vo);
			}
		
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		close();
		return list;
	}
	
	public Admin_productVo productView(int SERIAL) {
		if(conn == null)   conn = new DBConn().getConn();
		
		Admin_productVo vo = new Admin_productVo();
		String sql = "select * from products where SERIAL = ?";
		
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, SERIAL);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				vo.setCategory(rs.getString("category"));
				vo.setSerial(rs.getInt("SERIAL"));
				vo.setProductName(rs.getString("productName"));
				vo.setPrice(rs.getInt("price"));
				vo.setStock(rs.getInt("stock"));
				vo.setSalesRate(rs.getInt("salesRate"));
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		close();
		
		return vo;
	  }

   }

