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
import jdbc.Admin_memberDao;
import jdbc.Admin_memberVo;
import jdbc.Admin_orderDao;
import jdbc.Admin_orderVo;
import jdbc.Admin_productDao;
import jdbc.Admin_productVo;
import jdbc.Page;




@WebServlet(urlPatterns = "/action.admin")
public class AdminServlet extends HttpServlet {
	String path = "index.jsp?inc1=jsp/admin.jsp&inc2=";
	String url  = "";
	String job  = "";
	Admin_memberDao mDao;
	Admin_orderDao oDao;
	Admin_productDao pDao;
	RequestDispatcher rd = null;
	PreparedStatement ps = null;
	
	public AdminServlet() {
		mDao = new Admin_memberDao();
		oDao = new Admin_orderDao(); 
		pDao = new Admin_productDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(mDao == null)  mDao = new Admin_memberDao();
		if(oDao == null)  oDao = new Admin_orderDao();
		if(pDao == null)  pDao = new Admin_productDao();
		Page pageVo = new Page();
		        
				job = req.getParameter("job");
				switch(job) {
				case "adminMain" :
					adminMain(req,resp);
					break;				
				case "memberSearch" :
					memberSearch(pageVo, req, resp);
			        break;
				case "orderSearch" :
					orderSearch(pageVo, req, resp);
			     	break;
				case "productSearch" :
					productSearch(pageVo,req,resp);
					break;
 				}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(mDao == null)  mDao = new Admin_memberDao();
		if(oDao == null)  oDao = new Admin_orderDao();
		//if(pDao == null)  pDao = new Admin_productDao();
		
				String job = req.getParameter("job");
				System.out.println(job);
				rd =null;
				
				Page pageVo = new Page();
				pageVo.setFindStr(req.getParameter("findStr"));
				pageVo.setNowPage(Integer.parseInt(req.getParameter("nowPage")));
				req.setAttribute("pageVo", pageVo);
				
				switch(job) {
				case "memberSearch" :
					memberSearch(pageVo,req,resp);
			        break;
				case "memberView" :
					memberView(req,resp);
					break;
				case "orderSearch" :
					orderSearch(pageVo,req,resp);
					break;
				case "orderView" :
					orderView(req,resp);
					break;
				case "productSearch" :
					productSearch(pageVo, req, resp);
					break;
				case "memberModify" : 
					break;
					
				
				}
	}
	
	// 회원정보수정
		public void memberUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		}
		
		// 회원정보조회
		public void memberView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			if(mDao == null)  mDao = new Admin_memberDao();
	    	String url = path + "member_modify.jsp";
	    	String id = req.getParameter("id");
	    	Admin_memberVo vo = mDao.memberView(id);
	    	
	    	Page pageVo = new Page();
			pageVo.setFindStr(req.getParameter("findStr"));
			pageVo.setNowPage(Integer.parseInt(req.getParameter("nowPage")));
			
	    	RequestDispatcher rd = req.getRequestDispatcher(url);
	    	req.setAttribute("vo", vo);
	    	req.setAttribute("pageVo", pageVo);
	    	
	    	rd.forward(req, resp);
		}
		
		// 회원정보검색
		public void memberSearch(Page pageVo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			if(mDao == null)  mDao = new Admin_memberDao();
			List<Admin_memberVo> list = mDao.memberSearch(pageVo);
			
			String url = path + "member.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			req.setAttribute("list", list);
			req.setAttribute("pageVo", pageVo);
			
			rd.forward(req, resp);
		}
			
		// 주문정보수정
		public void orderUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		}
		
		// 주문정보삭제, 환불승인
		public void orderDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		}
		
		// 주문정보조회
		public void orderView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			if(oDao == null)  oDao = new Admin_orderDao();
	    	String url = path + "order_modify.jsp";
	    	String id = req.getParameter("id");
	    	Admin_orderVo vo = oDao.orderView(id);
	    	
	    	Page pageVo = new Page();
			pageVo.setFindStr(req.getParameter("findStr"));
			pageVo.setNowPage(Integer.parseInt(req.getParameter("nowPage")));
			
	    	RequestDispatcher rd = req.getRequestDispatcher(url);
	    	req.setAttribute("vo", vo);
	    	req.setAttribute("pageVo", pageVo);
	    	
	    	rd.forward(req, resp);
		}
		
		// 주문정보검색
		public void orderSearch(Page pageVo,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			if(oDao == null)  oDao = new Admin_orderDao();
			List<Admin_orderVo> list = oDao.orderSearch(pageVo);
			
			String url = path + "order.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			req.setAttribute("list", list);
			req.setAttribute("pageVo", pageVo);
			
			rd.forward(req, resp);
		}
		
		// 주문정보검색
		public void productSearch(Page pageVo,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			if(pDao == null)  pDao = new Admin_productDao();
			List<Admin_productVo> list = pDao.productSearch(pageVo);
			
			String url = path + "product.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			req.setAttribute("list", list);
			req.setAttribute("pageVo", pageVo);
			
			rd.forward(req, resp);
		}
		
		// 통계정보조회
		public void graphView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		}
		
		// 통계정보검색
		public void graphSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		}
		
		//admin.jsp로 이동
		public void adminMain(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String url = path + "admin_main.jsp";
			rd = req.getRequestDispatcher(url);
			rd.forward(req, resp);
		}
		
}	
	