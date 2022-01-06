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

		if(!session.isNew() && (Boolean)session.getAttribute("auth") == Boolean.TRUE && request.getServletPath() != "/logout")
			response.sendRedirect("/project/menu");
		else {
			session.invalidate();
			response.sendRedirect("/project/login.jsp");
		}
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

		String action = request.getParameter("action");
		switch(action) {
			case "login": 
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
				break;
			
			case "register":
				if(db.registerUser(user)) {
					System.out.println("--- --- SUCCESFUL REGISTRATION");
					session.setAttribute("user", user);
					session.setAttribute("auth", true);
					response.sendRedirect("/project/menu");
				} else {
					System.out.println("--- --- UNSUCCESFUL REGISTRATION");
					session.setAttribute("user", null);
					session.setAttribute("auth", false);
					response.sendRedirect("/project/login");
				}
				break;
		}


	}
}
