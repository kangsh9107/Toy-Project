package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jdbc.DBConn;
import jdbc.ProductDao;
import jdbc.ProductPage;
import jdbc.ProductVo;

@WebServlet(urlPatterns = "/action.kang")
public class KangServlet extends HttpServlet {
	String path = "index.jsp?inc=jsp/";
	String url  = "";
	String inc  = "";
	ProductDao       dao = null;
	RequestDispatcher rd = null;
	
	public KangServlet() {
		dao = new ProductDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(dao == null) dao = new ProductDao();
		
		ProductPage pageVo = new ProductPage();
		pageVo.setCategory(req.getParameter("category"));
		inc = req.getParameter("inc");
		req.setAttribute("inc", inc);
		
		select(pageVo, req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(dao == null) dao = new ProductDao();
		
		inc = req.getParameter("inc");
		
		if(req.getParameter("category").equals("outer")  ||
		   req.getParameter("category").equals("top")    ||
		   req.getParameter("category").equals("bottom") ||
		   req.getParameter("category").equals("shoes")  ||
		   req.getParameter("category").equals("acc")      ) {
			ProductPage pageVo = new ProductPage();
			pageVo.setFindStr(req.getParameter("findStr"));
			pageVo.setNowPage(Integer.parseInt(req.getParameter("nowPage")));
			pageVo.setCategory(req.getParameter("category"));
			pageVo.setJob(req.getParameter("job"));
			req.setAttribute("pageVo", pageVo);
			
			select(pageVo, req, resp);
		} else if(req.getParameter("inc").equals("signup.jsp")) {
			ProductPage pageVo = new ProductPage();
			
			select(pageVo, req, resp);
		} else if(req.getParameter("inc").equals("main.jsp")) {
			ProductPage pageVo = new ProductPage();
			
			select(pageVo, req, resp);
		}
	}
	
	public void select(ProductPage pageVo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(dao == null) dao = new ProductDao();

		if(req.getParameter("category").equals("outer")  ||
		   req.getParameter("category").equals("top")    ||
		   req.getParameter("category").equals("bottom") ||
		   req.getParameter("category").equals("shoes")  ||
		   req.getParameter("category").equals("acc")      ) {
			List<ProductVo> list = dao.select(pageVo);
			url = path + "category.jsp";
			rd = req.getRequestDispatcher(url);
			req.setAttribute("list", list);
			req.setAttribute("pageVo", pageVo);
			
			rd.forward(req, resp);
		} else if(inc.equals("login.jsp")) {
			url = path + req.getParameter("inc");
			rd = req.getRequestDispatcher(url);
			
			rd.forward(req, resp);
		} else if(inc.equals("signup.jsp")) {
			url = path + req.getParameter("inc");
			rd = req.getRequestDispatcher(url);
			
			rd.forward(req, resp);
		} else if(inc.equals("main.jsp")) {
			boolean b = false;
			
			try {
				Connection conn = new DBConn().getConn();
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
			
			if(b) {
				HttpSession session = req.getSession();
				session.setAttribute("sessionId", req.getParameter("id"));
				url = path + req.getParameter("inc");
				rd = req.getRequestDispatcher(url);
				
				rd.forward(req, resp);
			} else {
				HttpSession session = req.getSession();
				session.setAttribute("sessionId", null);
				url = path + "login.jsp";
				rd = req.getRequestDispatcher(url);
				
				rd.forward(req, resp);
			}
		}
	}

}
