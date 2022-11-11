package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdbc.ProductDao;
import jdbc.ProductPage;
import jdbc.ProductVo;

@WebServlet(urlPatterns = "/action.kang")
public class KangServlet extends HttpServlet {
	String path = "index.jsp?inc=jsp/";
	String url  = "";
	String job  = "";
	ProductDao       dao = null;
	RequestDispatcher rd = null;
	
	public KangServlet() {
		dao = new ProductDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(dao == null) dao = new ProductDao();
		
		job = req.getParameter("job");
		ProductPage pageVo = new ProductPage();
		pageVo.setCategory(job);
		
		select(pageVo, req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
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

}
