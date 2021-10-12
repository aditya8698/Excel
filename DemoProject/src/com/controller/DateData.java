package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.FileDao;
import com.model.Bean;

@WebServlet("/DateData")
public class DateData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FileDao dao;

	@Override
	public void init() throws ServletException {
		dao = new FileDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date1 =  request.getParameter("date1");
		String date2 = request.getParameter("date2");
		
		Bean b = new Bean();
		b.setDate1(date1);
		b.setDate1(date2);
		
		int y = dao.getDateData(b);
		RequestDispatcher rd = request.getRequestDispatcher("");
		rd.forward(request, response); 
	}

}
