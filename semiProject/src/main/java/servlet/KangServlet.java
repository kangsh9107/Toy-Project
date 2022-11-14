package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jdbc.ProductDao;
import jdbc.ProductPage;
import jdbc.ProductVo;

@WebServlet(urlPatterns = "/action.kang")
public class KangServlet extends HttpServlet {
	String path = "index.jsp?inc1=jsp/";
	String url  = "";
	ProductPage   pageVo = null;
	ProductDao       dao = null;
	ProductVo        pVo = null;
	RequestDispatcher rd = null;
	
	public KangServlet() {
		dao = new ProductDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(dao == null) dao = new ProductDao();
		
		switch(req.getParameter("job")) {
		case "showCategory": // 카테고리 선택 시 해당 카테고리의 상품 전체 출력
			pageVo = new ProductPage();
			pageVo.setCategory(req.getParameter("category"));
			showCategory(pageVo, req, resp);
			break;
		case "moveLogin":    // 네비게이션에서 로그인 클릭 시 로그인 폼 출력
			moveLogin(req, resp);
			break;
		case "logout":       // 네비게이션에서 로그아웃 클릭 시 로그아웃
			logout(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(dao == null) dao = new ProductDao();
		
		pageVo = new ProductPage();
		pageVo.setCategory(req.getParameter("category"));
		pageVo.setFindStr(req.getParameter("findStr"));
		
		switch(req.getParameter("job")) {
		case "checkLogin": // 로그인 시 아이디와 비밀번호 맞는지 체크하고 sessionId에 로그인 성공한 아이디 저장
			checkLogin(req, resp);
			break;
		case "moveSignup": // 로그인 폼에서 회원가입 클릭 시 회원가입 폼 출력
			moveSignup(req, resp);
			break;
		case "movePage":   // 페이지 버튼 클릭시 클릭한 페이지 출력
			pageVo.setNowPage(Integer.parseInt(req.getParameter("nowPage")));
			showCategory(pageVo, req, resp);
			break;
		case "showCategoryDetail": // 상품 선택 시 해당 상품 디테일 출력
			pageVo.setNowPage(Integer.parseInt(req.getParameter("nowPage")));
			pVo = new ProductVo();
			pVo.setSerial(Integer.parseInt(req.getParameter("serial")));
			showCategoryDetail(pageVo, pVo, req, resp);
			break;
		case "backToList": // 상품 디테일에서 Back to list 클릭 시 보던 페이지 목록 출력
			pageVo.setNowPage(Integer.parseInt(req.getParameter("nowPage")));
			showCategory(pageVo, req, resp);
			break;
		case "buyRightNow":// 상품 디테일에서 Buy 클릭 시 주문완료
			pageVo.setNowPage(Integer.parseInt(req.getParameter("nowPage")));
			pVo = new ProductVo();
			pVo.setSerial(Integer.parseInt(req.getParameter("serial")));
			buyRightNow(pageVo, pVo, req, resp);
			break;
		}
	}
	
	public void showCategory(ProductPage pageVo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(dao == null) dao = new ProductDao();
		
		List<ProductVo> list = dao.selectCategory(pageVo);
		url = path + "category.jsp";
		rd = req.getRequestDispatcher(url);
		req.setAttribute("list", list);
		req.setAttribute("pageVo", pageVo);
		rd.forward(req, resp);
	}
	
	public void moveLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		url = path + "login.jsp";
		rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}
	
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setAttribute("sessionId", null);
		url = path + "main.jsp";
		rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}
	
	public void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(dao == null) dao = new ProductDao();
		
		HttpSession session = req.getSession();
		boolean b = false;
		b = dao.check(req, resp);
		
		if(b) {
			session.setAttribute("sessionId", req.getParameter("id"));
			url = path + "main.jsp";
			rd = req.getRequestDispatcher(url);
			rd.forward(req, resp);
		} else {
			session.setAttribute("sessionId", null);
			url = path + "login.jsp?alert=fail";
			rd = req.getRequestDispatcher(url);
			rd.forward(req, resp);
		}
	}
	
	public void moveSignup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		url = path + "signup.jsp";
		rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}
	
	public void showCategoryDetail(ProductPage pageVo, ProductVo pVo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(dao == null) dao = new ProductDao();
		
		List<ProductVo> list = dao.selectCategoryDetail(pageVo, pVo);
		url = path + "category_detail.jsp";
		rd = req.getRequestDispatcher(url);
		req.setAttribute("list", list);
		req.setAttribute("pageVo", pageVo);
		rd.forward(req, resp);
	}
	
	public void buyRightNow(ProductPage pageVo, ProductVo pVo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(dao == null) dao = new ProductDao();
		
		List<ProductVo> list = dao.selectCategoryDetail(pageVo, pVo);
		url = path + "category_detail.jsp";
		rd = req.getRequestDispatcher(url);
		req.setAttribute("list", list);
		req.setAttribute("pageVo", pageVo);
		rd.forward(req, resp);
	}

}
