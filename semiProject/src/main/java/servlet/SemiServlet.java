package servlet;

import java.io.IOException;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdbc.MemberDao;

//@WebServlet(urlPatterns = "/action.do")
public class SemiServlet extends HttpServlet {
	String path = "index.jsp?inc=jsp/";
	String url  = "";
	String job  = "";
	MemberDao dao;
	RequestDispatcher rd = null;
	PreparedStatement ps = null;
	
	public SemiServlet() {
		dao = new MemberDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dao = new MemberDao();
		dao.memberSignup();
	}
	
	// 로그인
	public boolean login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean b = false;
		
		
		
		return b;
	}
	
	// 로그아웃
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	// 회원가입
	public void memberSignup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	// 회원탈퇴, 회원삭제
	public boolean memberQuit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean b = false;
		
		
		
		return b;
	}
	
	// 회원정보수정
	public void memberUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	// 회원정보조회
	public void memberView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	// 회원정보검색
	public void memberSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
	
	// 상품정보 상세정보페이지
	public void productDetailView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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

}
