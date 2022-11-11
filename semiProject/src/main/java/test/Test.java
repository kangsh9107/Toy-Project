package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdbc.DBConn;

@WebServlet(urlPatterns = "/test.do")
public class Test extends HttpServlet {
	String path = "test.jsp?inc=";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String job = req.getParameter("job");
		String url = "";
		boolean b = false;

		System.out.println("doPost:" + req.getParameter("id"));
		
		switch(job) {
		case "login":
			b = login(req, resp);
			
			if(b) {
				req.setAttribute("sessionId", req.getParameter("id"));
				url = path + "temp.html";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, resp);
			} else {
				req.setAttribute("sessionId", null);
				url = path + "temp.html";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, resp);
			}
			break;
		}
	}
	
	public boolean login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean b = false;
		
		try {
			Connection conn = new DBConn("semi").getConn();
			String sql = "select * from member where id = ? and pwd = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, req.getParameter("id"));
			ps.setString(2, req.getParameter("pwd"));
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				b = true;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return b;
	}

}
