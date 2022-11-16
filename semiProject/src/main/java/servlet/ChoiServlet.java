package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdbc.MemberDao;
import jdbc.MemberVo;
import jdbc.Page;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/action.Choi")
public class ChoiServlet extends HttpServlet {
	String path = "index.jsp?inc1=jsp/";
	String url = "";
	MemberDao			dao = null;
	MemberVo			mVo = null;
	RequestDispatcher 	 rd = null;
	
    public ChoiServlet() {
       dao = new MemberDao();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(dao == null) dao = new MemberDao();
		
		String job = req.getParameter("job");
		
		switch(job) {
		case"saveForm":
			MemberVo mVo = new MemberVo();
			mVo.setId(req.getParameter("id"));
			mVo.setPwd(req.getParameter("pwd"));
			mVo.setName(req.getParameter("name"));
			mVo.setGender(req.getParameter("gender"));
			mVo.setAge(Integer.parseInt(req.getParameter("age")));
			mVo.setPostalCode(req.getParameter("postalCode"));
			mVo.setAddress1(req.getParameter("address1"));
			mVo.setAddress2(req.getParameter("address2"));
			mVo.setPhone(req.getParameter("phone"));
			mVo.setEmail(req.getParameter("email"));
			
			if( saveForm(mVo)) {
				// 로그인폼으로 연결
				url = path + "login.jsp?signup=success";
				rd = req.getRequestDispatcher(url);
				rd.forward(req, resp);
			} else {
				// 회원가입폼으로 연결
				url = path + "signup.jsp?signup=fail";
				rd = req.getRequestDispatcher(url);
				rd.forward(req, resp);
			}
			break;
		}
	}

	public boolean saveForm(MemberVo mVo) {
		if(dao == null) dao = new MemberDao();
		
		boolean b = dao.saveForm(mVo);
		
		return b;
	}
}
