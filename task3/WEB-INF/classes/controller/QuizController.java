package controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QuizController extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			System.out.println("--- GET QUIZ ---");
			
			HttpSession session = request.getSession(true);

			response.sendRedirect("/app/quiz.jsp");
		}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
			System.out.println("--- POST QUIZ ---");

			
	}
}

