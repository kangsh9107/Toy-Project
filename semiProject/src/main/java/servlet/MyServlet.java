package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdbc.MypageDao;
import jdbc.Mypage_orderVo;


@WebServlet(urlPatterns = "/action.my")
public class MyServlet extends HttpServlet {
	String path = "index.jsp?inc1=jsp/mypage.jsp&incMY=";
	String url  = "";
	String job  = "";
	MypageDao dao;
	public MyServlet() {	
		MypageDao dao = new MypageDao();
	}	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if( dao ==null) {MypageDao dao = new MypageDao();}
		
		List<Mypage_orderVo> list = dao.select("f0083");
		
		url =  path + "myorder.jsp";
		RequestDispatcher rd=req.getRequestDispatcher(url);
		req.setAttribute("list",list);
		
		rd.forward(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
if( dao ==null) {MypageDao dao = new MypageDao();}
		
		List<Mypage_orderVo> list = dao.select("f0083");
		
		url =  path + "myorder.jsp";
		RequestDispatcher rd=req.getRequestDispatcher(url);
		req.setAttribute("list",list);
		
		rd.forward(req,resp);
	}
	
	
}
