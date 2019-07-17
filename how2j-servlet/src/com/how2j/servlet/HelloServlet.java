package com.how2j.servlet;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3234559988104978288L;

	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse resp) {
		try {
			resp.getWriter().println("Hello");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}