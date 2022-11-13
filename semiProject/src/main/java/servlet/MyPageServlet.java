package servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdbc.MemberDao;
import jdbc.MyPageMemberDao;
import jdbc.MyPageOrderDao;
import jdbc.OrderVo;

@WebServlet(urlPatterns = "/myPage")
public class MyPageServlet extends HttpServlet{
	
	String path = "index.jsp?inc1=jsp/mypage.jsp&incMY=";
	String url  = "";
	String job  = "";
	MyPageOrderDao mpoDao;
	MyPageMemberDao mpmDao;
	RequestDispatcher rd = null;
	PreparedStatement ps = null;
	
	public MyPageServlet() {
		mpoDao = new MyPageOrderDao();
		mpmDao = new MyPageMemberDao();
	}
	
	// mypage 주문목록에서 버튼으로 페이지 이동
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(mpoDao==null) mpoDao = new MyPageOrderDao();
		String myJob = req.getParameter("myJob");
		switch(myJob) {
		case "myPage":
			break;
		
		case "showOrder":
			String id = req.getParameter("id");
			showMyOrder(id, req, resp);
			break;
		case "quit":
			id = req.getParameter("id");
			quit(id, req, resp);
			break;
		}

	}
	
	// mypage에서 회원 탈퇴, 환불요청
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MyPageMemberDao mpmDao;
		String myJob = req.getParameter("myJob");
	}
			
	public void showMyOrder(String id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(mpoDao == null) mpoDao = new MyPageOrderDao();
		List<OrderVo> list = mpoDao.select(id);

		String url = path + "myorder.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		req.setAttribute("list", list);
		rd.forward(req, resp);
	}
	
	public void quit(String id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = path + "quit.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		req.setAttribute("id", id);
		rd.forward(req, resp);
	}

}
