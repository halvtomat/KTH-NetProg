package controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import util.DBHandler;

public class MenuController extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		System.out.println("--- GET MENU ---");

		HttpSession session = request.getSession(true);
		DBHandler db;
		if((db = (DBHandler)session.getAttribute("db")) == null) {
			db = new DBHandler();
			session.setAttribute("db", db);
		}

		if(session.isNew() || (Boolean)session.getAttribute("auth") == Boolean.FALSE)
			response.sendRedirect("/project/login");
		else {
			session.setAttribute("queues", db.getQueues());
			response.sendRedirect("/project/menu.jsp");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("--- POST MENU ---");

		HttpSession session = request.getSession(true);
		if(session.isNew() || (Boolean)session.getAttribute("auth") == Boolean.FALSE)
			response.sendRedirect("/app/login");
		else {

		}
	}
}
