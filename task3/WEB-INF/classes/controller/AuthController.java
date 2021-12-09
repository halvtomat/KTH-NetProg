package controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

public class AuthController extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			System.out.println("--- GET AUTH ---");
			
			HttpSession session = request.getSession(true);

			response.sendRedirect("/app/login.jsp");
		}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
			System.out.println("--- POST AUTH ---");

			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			User user = new User(email, username, password);
			System.out.println(user.toString());

			if(true) {
				System.out.println("--- --- SUCCESFUL LOGIN");
				response.sendRedirect("/app/menu");
			}
	}
}
