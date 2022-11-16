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
import jdbc.Admin_graphDao;
import jdbc.Admin_graphVo;
import jdbc.Admin_mainDao;
import jdbc.Admin_mainVo;
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
	Admin_graphDao gDao;
	Admin_mainDao vDao;
	RequestDispatcher rd = null;
	PreparedStatement ps = null;
	
	public AdminServlet() {
		mDao = new Admin_memberDao();
		oDao = new Admin_orderDao(); 
		pDao = new Admin_productDao();
		gDao = new Admin_graphDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(mDao == null)  mDao = new Admin_memberDao();
		if(oDao == null)  oDao = new Admin_orderDao();
		if(pDao == null)  pDao = new Admin_productDao();
		if(gDao == null)  gDao = new Admin_graphDao();
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
				case "graphView" :
					graphView(req, resp);
					break;
				case "graphSearch" :
					graphSearch(pageVo,req, resp);
					break;
				
 				}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(mDao == null)  mDao = new Admin_memberDao();
		if(oDao == null)  oDao = new Admin_orderDao();
		if(pDao == null)  pDao = new Admin_productDao();
		if(gDao == null)  gDao = new Admin_graphDao();
		
				String job = req.getParameter("job");
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
				case "memberUpdate" :
					memberUpdate(req, resp);
					break;
				case "memberDelete" :
					memberDelete(req, resp);
					break;
				case "orderSearch" :
					orderSearch(pageVo,req,resp);
					break;
				case "orderView" :
					orderView(req,resp);
					break;
				case "orderUpdate" :
					orderUpdate(req, resp);
					break;
				case "orderDelete" :
					orderDelete(req, resp);
					break;
				case "productSearch" :
					productSearch(pageVo, req, resp);
					break;
				case "graphView" : 
					graphView(req,resp);
					break;
				case "graphSearch" : 
					graphSearch(pageVo,req,resp);
					break;
				
				}
	}
	
	// 회원정보수정
		public void memberUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			if(mDao == null) mDao= new Admin_memberDao();
			Page pageVo = (Page)req.getAttribute("pageVo");
			
			boolean b = mDao.modify(req);
			if(b) {
				memberSearch(pageVo, req, resp);
			}else {
				PrintWriter out = resp.getWriter();
				out.print("<script>");
				out.print("   alert('자료에 오류가 발생!');");
				out.print("   history.back();" );// 입력폼으로 다시 이동
				out.print("</script>");
			}
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
		
		//회원정보삭제
		public void memberDelete(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			if (mDao == null)
				mDao = new Admin_memberDao();
			Page pageVo = (Page) req.getAttribute("pageVo");
			boolean b = mDao.delete(req);
			if (b) {
				memberSearch(pageVo, req, resp);
			} else {
				PrintWriter out = resp.getWriter();
				out.print("<script>");
				out.print("   alert('자료 삭제시 오류가 발생!!!');");
				out.print("   history.back();");// 입력폼으로 다시 이동
				out.print("</script>");
			}

		}
			
		// 주문정보수정
		public void orderUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			if(oDao == null) oDao= new Admin_orderDao();
			Page pageVo = (Page)req.getAttribute("pageVo");
			
			boolean b = oDao.modify(req);
			if(b) {
				orderSearch(pageVo, req, resp);
			}else {
				PrintWriter out = resp.getWriter();
			}	
		}
		
		// 주문정보삭제, 환불승인
		public void orderDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			if(oDao == null) oDao = new Admin_orderDao();
			Page pageVo = (Page)req.getAttribute("pageVo");
			boolean b = oDao.delete(req);
			if(b) {
				orderSearch(pageVo, req, resp);
			}else{
				PrintWriter out = resp.getWriter();
				out.print("<script>");
				out.print("   alert('자료 삭제시 오류가 발생!!!');");
				out.print("   history.back();" );// 입력폼으로 다시 이동
				out.print("</script>");
			}
		}
		
		// 주문정보조회
		public void orderView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			if(oDao == null)  oDao = new Admin_orderDao();
	    	String url = path + "order_modify.jsp";
	    	
	    	Admin_orderVo vo = new Admin_orderVo();
	    	vo = oDao.orderView(req.getParameter("orderNumber"));
	    	
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
		
		// 상품정보검색
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
			if(gDao == null) gDao = new Admin_graphDao();
			
		    Admin_graphVo gVo = new Admin_graphVo();
		    gVo = gDao.graphView();
		  
     		String url = path + "graph.jsp";
     	    rd = req.getRequestDispatcher(url);
     	    req.setAttribute("gVo", gVo);
			rd.forward(req, resp);
		}
		
		// 통계정보검색
		public void graphSearch(Page pageVo,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			if(gDao == null) gDao = new Admin_graphDao();
			
			
			int SERIAL = Integer.parseInt(req.getParameter("SERIAL"));
			Admin_graphVo gVo = new Admin_graphVo();
			gVo = gDao.graphSearch(SERIAL);
			
			pageVo.setFindStr(req.getParameter("findStr"));
			
			String url = path + "graph.jsp";
		  	rd = req.getRequestDispatcher(url);
	    	req.setAttribute("gVo", gVo);
	    	req.setAttribute("pageVo", pageVo);
	    	rd.forward(req, resp);
		}
		
		//admin.jsp로 이동
		public void adminMain(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			if(vDao == null) vDao = new Admin_mainDao();
			String url = path + "admin_main.jsp";
			
			Admin_mainVo vo = new Admin_mainVo();
			vo = vDao.mainView();
			
			req.setAttribute("vo", vo);
			rd = req.getRequestDispatcher(url);
			rd.forward(req, resp);
		}
		
}	
	