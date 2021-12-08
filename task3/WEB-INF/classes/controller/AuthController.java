package controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthController extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			System.out.println("Username = " + username + " , Password = " + password);
	}
}
