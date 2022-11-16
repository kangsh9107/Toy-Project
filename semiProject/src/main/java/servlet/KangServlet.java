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
		case "select": // 카테고리 별 상품 & 검색 조건에 따른 상품 & 페이지이동 & Back to list
			pageVo = new ProductPage();
			pageVo.setCategory(req.getParameter("category"));
			select(pageVo, req, resp);
			break;
		case "moveLoginForm": // 네비게이션에서 로그인 클릭 시 로그인 폼 출력
			moveLoginForm(req, resp);
			break;
		case "logout": // 네비게이션에서 로그아웃 클릭 시 로그아웃
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
		pageVo.setNowPage(Integer.parseInt(req.getParameter("nowPage")));
		pVo = new ProductVo();
		
		switch(req.getParameter("job")) {
		case "moveSignup": // 로그인 폼에서 회원가입 클릭 시 회원가입 폼 출력
			moveSignup(req, resp);
			break;
		case "login":      // 로그인 시 아이디와 비밀번호 맞는지 체크하고 sessionId에 로그인 성공한 아이디 저장
			login(req, resp);
			break;
		case "select":     // 카테고리 별 상품 & 검색 조건에 따른 상품 & 페이지이동 & Back to list
			select(pageVo, req, resp);
			break;
		case "showCategoryDetail": // 상품 선택 시 해당 상품 디테일 출력
			pVo.setSerial(Integer.parseInt(req.getParameter("serial")));
			showCategoryDetail(pageVo, pVo, req, resp);
			break;
		case "buyRightNow":// 상품 디테일에서 Buy 클릭 시 주문완료
			HttpSession session = req.getSession();
			req.setAttribute("sessionId", session.getAttribute("sessionId"));
			pVo.setSerial(Integer.parseInt(req.getParameter("serial")));
			buyRightNow(pageVo, pVo, req, resp);
			break;
		}
	}
	
	public void select(ProductPage pageVo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(dao == null) dao = new ProductDao();
		
		List<ProductVo> list = dao.select(pageVo);
		url = path + "category.jsp";
		rd = req.getRequestDispatcher(url);
		req.setAttribute("list", list);
		req.setAttribute("pageVo", pageVo);
		rd.forward(req, resp);
	}
	
	public void moveLoginForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		url = path + "login.jsp";
		rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}
	
	public void moveSignup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		url = path + "signup.jsp";
		rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}
	
	public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
	
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setAttribute("sessionId", null);
		url = path + "main.jsp";
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
		
		List<ProductVo> list = dao.buyRightNow(pageVo, pVo, req, resp);
		
		if(list.get(1).getProductName().equals("temp")) {
			url = path + "category_detail.jsp?buy1=fail";
		} else {
			url = path + "category_detail.jsp?buy2=seccess";
		}
		rd = req.getRequestDispatcher(url);
		req.setAttribute("list", list);
		req.setAttribute("pageVo", pageVo);
		req.setAttribute("buyRefresh", "false");
		rd.forward(req, resp);
	}

}
