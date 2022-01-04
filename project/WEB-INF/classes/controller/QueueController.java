package controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.Queue;
import util.DBHandler;

public class QueueController extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("--- GET QUEUE ---");
		
		HttpSession session = request.getSession(true);
		DBHandler db;
		if((db = (DBHandler)session.getAttribute("db")) == null) {
			db = new DBHandler();
			session.setAttribute("db", db);
		}

		if(session.isNew() || (Boolean)session.getAttribute("auth") == Boolean.FALSE)
			response.sendRedirect("/project/login");
		else {
			int queueId = Integer.parseInt(request.getParameter("id"));
			String queueName = db.getQueueName(queueId);
			Queue queue = new Queue(queueName, queueId);
			session.setAttribute("participants", db.getParticipants(queueId));
			session.setAttribute("queue", queue);
			response.sendRedirect("/project/queue.jsp");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		System.out.println("--- POST QUEUE ---");

		HttpSession session = request.getSession(true);
		/*if(session.isNew() || (Boolean)session.getAttribute("auth") == Boolean.FALSE)
			response.sendRedirect("/app/login");
		else {
			Question[] questions = (Question[])session.getAttribute("questions");
			int score = 0;
			for(int i = 0; i < questions.length; i++) {
				int[] answers = new int[questions[i].getOptions().length];
				for(int j = 0; j < answers.length; j++) {
					String s = request.getParameter("q"+i+"o"+j);
					if(s != null && s.equals("on"))
						answers[j] = 1;
					else
						answers[j] = 0;
				}
				System.out.println("{\n\tsupplied answer: " + Arrays.toString(answers) + "\n}");
				if(Arrays.equals(answers, questions[i].getAnswer()))
					score++;
			}
			User user = (User)session.getAttribute("user");
			int quizId = (int)session.getAttribute("quizId");
			db.newResult(user, quizId, score);
			response.sendRedirect("/app/menu");
		}*/
	}
}

