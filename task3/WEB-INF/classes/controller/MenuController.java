package controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Quiz;
import model.Result;
import model.User;
import util.DBHandler;

public class MenuController extends HttpServlet {

	DBHandler db = new DBHandler();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		System.out.println("--- GET MENU ---");

		HttpSession session = request.getSession(true);
		if(session.isNew() || (Boolean)session.getAttribute("auth") == Boolean.FALSE)
			response.sendRedirect("/app/login");
		else {
			session.setAttribute("quizzes", db.getQuizzes());
			session.setAttribute("results", db.getResults((User)session.getAttribute("user")));
			response.sendRedirect("/app/menu.jsp");
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
