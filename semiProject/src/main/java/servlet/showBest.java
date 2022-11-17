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

@WebServlet(urlPatterns = "/showBest.do")
public class showBest extends HttpServlet {
	String path = "index.jsp?inc1=jsp/";
	String url  = "";
	ProductDao       dao = null;
	RequestDispatcher rd = null;

	public showBest() {
		dao = new ProductDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(dao == null) dao = new ProductDao();
		
		req.setAttribute("category", req.getParameter("job"));
		
		switch(req.getParameter("job")) {
		case "outer":
			showBest(req, resp);
			break;
		case "top":
			showBest(req, resp);
			break;
		case "bottom":
			showBest(req, resp);
			break;
		case "shoes":
			showBest(req, resp);
			break;
		case "acc":
			showBest(req, resp);
			break;
		}
	}
	
	public void showBest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(dao == null) dao = new ProductDao();
		
		List<ProductVo> list = dao.showBest(req, resp);
		url = path + "main.jsp";
		rd = req.getRequestDispatcher(url);
		req.setAttribute("list", list);
		req.setAttribute("category", req.getParameter("job"));
		rd.forward(req, resp);
	}

}