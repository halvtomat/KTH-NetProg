package controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import util.DBHandler;

public class AuthController extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("--- GET AUTH ---");
		
		HttpSession session = request.getSession(true);
		if(!session.isNew() && (Boolean)session.getAttribute("auth") == Boolean.TRUE)
			response.sendRedirect("/project/menu");
		else
			response.sendRedirect("/project/login.jsp");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		System.out.println("--- POST AUTH ---");

		HttpSession session = request.getSession(true);
		DBHandler db;
		if((db = (DBHandler)session.getAttribute("db")) == null) {
			db = new DBHandler();
			session.setAttribute("db", db);
		}

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User(username, password);
		System.out.println(user.toString());

		if(db.authenticate(user)) {
			System.out.println("--- --- SUCCESFUL LOGIN");
			session.setAttribute("user", user);
			session.setAttribute("auth", true);
			response.sendRedirect("/project/menu");
		} else {
			System.out.println("--- --- UNSUCCESFUL LOGIN");
			session.setAttribute("user", null);
			session.setAttribute("auth", false);
			response.sendRedirect("/project/login");
		}
	}
}
