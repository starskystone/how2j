package com.how2j.servlet;


import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8677893765388772913L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("获取单值参数name:" + request.getParameter("name"));
		String[] hobits = request.getParameterValues("hobits");
		System.out.println("获取具有多值的参数hobits: " + Arrays.asList(hobits));
		
		Map<String,String[]> map = request.getParameterMap();
		Set<String> sets = map.keySet();
		for (String set : sets) {
			String[] values = map.get(set);
			System.out.println(values + ":" + Arrays.asList(values));
		}
	}

	
}
