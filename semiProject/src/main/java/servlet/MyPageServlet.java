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
import jdbc.MemberVo;
import jdbc.MyPageMemberDao;
import jdbc.MyPageOrderDao;
import jdbc.Mypage_mymainvo;
import jdbc.OrderDao;
import jdbc.OrderVo;
import jdbc.Page;

@WebServlet(urlPatterns = "/myPage")
public class MyPageServlet extends HttpServlet{
	
	String path = "index.jsp?inc1=jsp/mypage.jsp&incMY=";
	String url  = "";
	String job  = "";
	MyPageOrderDao mpoDao;
	MyPageMemberDao mpmDao;
	OrderDao oDao;
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
			Page pageVo = new Page();
			showMyOrder(id, pageVo, req, resp);
			break;
			
			
		case "quit":
			id = req.getParameter("id");
			quit(id, req, resp);
			break;
			
		case "myModify":
			id = req.getParameter("id");
			myModify(id, req, resp);
			break;
		}

	}
	
	// mypage에서 회원 탈퇴, 환불요청
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String myJob = req.getParameter("myJob");
		String id ="";
		HttpSession session = req.getSession();
		Page pageVo = new Page();
		pageVo.setFindStr(req.getParameter("findStr"));
		pageVo.setNowPage(Integer.parseInt(req.getParameter("nowPage")));
		req.setAttribute("pageVo", pageVo);
		switch(myJob) {
		case "myPage":
			break;
		
		
		case "quitR":
			id = req.getParameter("seolId");
			String pwd = req.getParameter("seolPwd");
			quitR(id, pwd, req, resp);
			break;
			
		case "myRefund":
	         int orderNumber = Integer.parseInt(req.getParameter("ordernumber"));
	         refund(orderNumber, pageVo, req, resp);
	         break;
		    
		case "showOrder":
			id = (String)session.getAttribute("sessionId");
			showMyOrder(id, pageVo, req, resp);
			break;
			
		case "searchMyOrder":
			id = (String)session.getAttribute("sessionId");
			searchMyOrder(id, pageVo, req, resp);
			break;

		case "showOrderDetails":
			showOrderDetails(req, resp);
			break;

		case "myModifyR":
			myModifyR(req, resp);
			break;
		}
		
	}

//mymain.jsp에서 회원정보수정버튼 눌렀을때 member_modify.jsp 이동되며 해당 ID로 셀렉트된 정보들을 인풋텍스트밸류값으로 
	public void myModify(String id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(mpmDao == null) mpmDao = new MyPageMemberDao();
		MemberVo vo = new MemberVo();
		vo = mpmDao.myModify(id);
		String url = path + "member_modify.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		req.setAttribute("vo", vo);
		rd.forward(req, resp);
		
	}
	public void myModifyR(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		if(mpmDao == null) mpmDao = new MyPageMemberDao();
		
		MemberVo vo = (MemberVo)req.getAttribute("mVo");
		
		boolean b = mpmDao.myModifyR(req,resp);
		
		if(b) {	//modify가 정상적으로 되면 b는 트루
			HttpSession session = req.getSession();
			String id = (String)session.getAttribute("sessionId");
			showMyPage(id,req, resp);
		}else {
			PrintWriter out = resp.getWriter();
			out.print("<script>");
			out.print("alert('check your id , pwd');");
			out.print("history.back();");	//입력폼으로 다시 이동
			out.print("</script>");
		}
		
		
	}

	
	public void refund(int orderNumber, Page pageVo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	      if(mpoDao == null) mpoDao = new MyPageOrderDao();
	      HttpSession session = req.getSession();
	      mpoDao.myrefund(orderNumber);
	      
	      List<OrderVo> list = mpoDao.select((String)session.getAttribute("sessionId"), pageVo);
	      System.out.println((String)session.getAttribute("sessionId"));
	      String url = path + "myorder.jsp";
	      RequestDispatcher rd = req.getRequestDispatcher(url);
	      req.setAttribute("list", list);
	      rd.forward(req, resp);
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
	
	/*
	public void showMyOrder(String id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(mpoDao == null) mpoDao = new MyPageOrderDao();
		List<OrderVo> list = mpoDao.select(id);
		String url = path + "myorder.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		req.setAttribute("list", list);
		rd.forward(req, resp);
		
	}
	*/
	
	public void showMyOrder(String id, Page pageVo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(mpoDao == null) mpoDao = new MyPageOrderDao();
		List<OrderVo> list = mpoDao.select(id, pageVo);
		String url = path + "myorder.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		req.setAttribute("list", list);
		req.setAttribute("pageVo", pageVo);
		rd.forward(req, resp);
	}
	
	public void searchMyOrder(String id, Page pageVo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(mpoDao == null) mpoDao = new MyPageOrderDao();
		List<OrderVo> list = mpoDao.search(id, pageVo);
		String url = path + "myorder.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		req.setAttribute("list", list);
		req.setAttribute("pageVo", pageVo);
		rd.forward(req, resp);	
	}
	
	public void showOrderDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(mpoDao == null) mpoDao = new MyPageOrderDao();
		String url = path + "orderDetail.jsp";
		String id = req.getParameter("id");
		String orderNumber = req.getParameter("orderNumber");
		OrderVo oVo = mpoDao.showOrderDetails(orderNumber);
		Page pageVo = new Page();
		pageVo.setFindStr(req.getParameter("findStr"));
		pageVo.setNowPage(Integer.parseInt(req.getParameter("nowPage")));
		RequestDispatcher rd = req.getRequestDispatcher(url);
		req.setAttribute("vo", oVo);
		req.setAttribute("pageVo", pageVo);
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
			
			PrintWriter out = resp.getWriter();
			out.print("<script>");
			out.print("    alert('sign out success');");
			out.print("     location.href = 'index.jsp';");
			out.print("</script>");
			
			HttpSession session = req.getSession();
			session.setAttribute("sessionId", null);
			url = "index.jsp?inc1=jsp/main.jsp";
			rd = req.getRequestDispatcher(url);
			rd.forward(req, resp);
			
		}else {
			PrintWriter out = resp.getWriter();
			out.print("<script>");
			out.print("    alert('error!!!');");
			out.print("     history.back();");
			out.print("</script>");	
		}
	}
	
}
