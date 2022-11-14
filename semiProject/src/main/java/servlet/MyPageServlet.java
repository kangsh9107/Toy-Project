package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jdbc.MemberDao;
import jdbc.MyPageMemberDao;
import jdbc.MyPageOrderDao;
import jdbc.Mypage_mymainvo;
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
		if(mpmDao==null) mpmDao = new MyPageMemberDao();
		String id ="";
		String myJob = req.getParameter("myJob");
		
		switch(myJob) {
		
		case "myMain":
			HttpSession session = req.getSession();
			id = (String)session.getAttribute("sessionId");
			showMyPage(id, req, resp);
			break;
		
		case "myPage":
			id = req.getParameter("id");
			showMyPage(id, req, resp);
			break;
		
		case "showOrder":
			id = req.getParameter("id");
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
		String myJob = req.getParameter("myJob");
		switch(myJob) {
		case "myPage":
			break;
		
		case "showOrder":
			String id = req.getParameter("id");
			showMyOrder(id, req, resp);
			break;
		case "quitR":
			id = req.getParameter("seolId");
			String pwd = req.getParameter("seolPwd");
			quitR(id, pwd, req, resp);
			break;
			
		case "myRefund":
	         int orderNumber = Integer.parseInt(req.getParameter("orderNumber"));
	         refund(orderNumber, req, resp);
	         break;
		}
		
	}
			
	public void showMyPage(String id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(mpmDao == null) mpmDao = new MyPageMemberDao();
		Mypage_mymainvo mpmmVo = new Mypage_mymainvo();
		mpmmVo = mpmDao.Mymainselect(id);
		String url = path + "mymain.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		req.setAttribute("mpmmVo", mpmmVo);
		rd.forward(req, resp);
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
	public void quitR(String id, String pwd, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(mpmDao==null) mpmDao = new MyPageMemberDao();
		String url = "index.jsp";
		String mId = id;
		String mPwd = pwd;
		boolean b = mpmDao.quitRR(mId, mPwd);
		if(b) {
			HttpSession session = req.getSession();
	        session.setAttribute("sessionId", null);
	        url = "index.jsp?inc1=jsp/main.jsp";
	        rd = req.getRequestDispatcher(url);
	        rd.forward(req, resp);
			PrintWriter out = resp.getWriter();
			out.print("<script>");
			out.print("    alert('sign out success');");
			out.print("     location.href = 'index.jsp';");
			out.print("</script>");
		}else {
			PrintWriter out = resp.getWriter();
			out.print("<script>");
			out.print("    alert('error!!!');");
			out.print("     history.back();");
			out.print("</script>");	
		}
	}
	
	public void refund(int orderNumber, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	      if(mpoDao == null) mpoDao = new MyPageOrderDao();
	      mpoDao.myrefund(orderNumber);
	      String url = path + "myorder.jsp";
	      RequestDispatcher rd = req.getRequestDispatcher(url);
	      rd.forward(req, resp);
	   } 

}
