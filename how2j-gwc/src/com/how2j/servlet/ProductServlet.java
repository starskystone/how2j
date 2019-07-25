package com.how2j.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.how2j.bean.Product;
import com.how2j.dao.ProductDao;

public class ProductServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductDao pd = new ProductDao();
		List<Product> list = pd.list();
		req.setAttribute("products", list);
		req.getRequestDispatcher("listProduct.jsp").forward(req, resp);
	}
	
}
