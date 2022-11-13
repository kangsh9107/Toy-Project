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
import jdbc.MemberDao;
import jdbc.MemberVo;
import jdbc.Page;




@WebServlet(urlPatterns = "/action.admin")
public class AdminServlet extends HttpServlet {
	String path1 = "index.jsp?inc=";
	String path2 = "jsp/admin.jsp&inc2=";
	String url  = "";
	String job  = "";
	MemberDao dao;
	RequestDispatcher rd = null;
	PreparedStatement ps = null;
	
	public AdminServlet() {
		dao = new MemberDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(dao == null)  dao = new MemberDao();
				
				// view resolve
				System.out.println("get");
				
				Page pageVo = new Page();
				select(pageVo, req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(dao == null)  dao = new MemberDao();
				
				String job = req.getParameter("job");
				System.out.println(job);
				rd =null;
				
				Page pageVo = new Page();
				pageVo.setFindStr(req.getParameter("findStr"));
				pageVo.setNowPage(Integer.parseInt(req.getParameter("nowPage")));
				req.setAttribute("pageVo", pageVo);
				
				switch(job) {
				case "select" :
					select(pageVo,req,resp);
			        break;
				
					
				
				}
	}
	
	// 회원정보수정
		public void memberUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		}
		
		// 회원정보조회
		public void memberView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		}
		
		// 회원정보검색
		public void memberSearch( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		}
		
		// 주문정보추가
		public void orderInsert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		}
		
		// 주문정보수정
		public void orderUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		}
		
		// 주문정보삭제, 환불승인
		public void orderDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		}
		
		// 주문정보조회
		public void orderView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		}
		
		// 주문정보검색
		public void orderSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		}
		
		// 상품정보조회
		public void productView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		}
		
		// 주문정보검색
		public void productSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		}
		
		// 통계정보조회
		public void graphView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		}
		
		// 통계정보검색
		public void graphSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		}
		
		// select
		public void select(Page pageVo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			if(dao == null)   dao = new MemberDao();
			List<MemberVo> list = dao.memberSearch(pageVo);
			
			String url = path1+ path2 + "member.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			req.setAttribute("list", list);
			req.setAttribute("pageVo", pageVo);
			
			rd.forward(req, resp);
		}
		
		
		
		
		
}	
	