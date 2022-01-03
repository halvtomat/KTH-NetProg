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

	DBHandler db = new DBHandler();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("--- GET AUTH ---");
		
		HttpSession session = request.getSession(true);
		if(!session.isNew() && (Boolean)session.getAttribute("auth") == Boolean.TRUE)
			response.sendRedirect("/app/menu");
		else
			response.sendRedirect("/app/login.jsp");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		System.out.println("--- POST AUTH ---");
		HttpSession session = request.getSession(true);

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		User user = new User(email, username, password);
		System.out.println(user.toString());

		if(db.authenticate(user)) {
			System.out.println("--- --- SUCCESFUL LOGIN");
			session.setAttribute("user", user);
			session.setAttribute("auth", true);
			response.sendRedirect("/app/menu");
		} else {
			System.out.println("--- --- UNSUCCESFUL LOGIN");
			session.setAttribute("user", null);
			session.setAttribute("auth", false);
			response.sendRedirect("/app/login");
		}
	}
}
