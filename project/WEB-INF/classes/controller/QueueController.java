package controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.Participant;
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
			Participant[] participants = db.getParticipants(queueId);

			String[] usernames = new String[participants.length];
			for(int i = 0; i < usernames.length; i++)
				usernames[i] = db.getUsername(participants[i].getUserId());

			session.setAttribute("participants", participants);
			session.setAttribute("usernames", usernames);
			session.setAttribute("queue", queue);
			response.sendRedirect("/project/queue.jsp");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		System.out.println("--- POST QUEUE ---");

		HttpSession session = request.getSession(true);

		DBHandler db;
		if((db = (DBHandler)session.getAttribute("db")) == null) {
			db = new DBHandler();
			session.setAttribute("db", db);
		}

		if(session.isNew() || (Boolean)session.getAttribute("auth") == Boolean.FALSE)
			response.sendRedirect("/project/login");
		else {
			String action = request.getParameter("action");
			switch(action) {
				case "enqueue":
					Participant p = new Participant(
						((User)session.getAttribute("user")).getId(),
						((Queue)session.getAttribute("queue")).getId(),
						request.getParameter("location"),
						request.getParameter("comment"),
						Boolean.valueOf(request.getParameter("help")),
						false,
						null);
					db.enqueue(p);
					break;
				case "dequeue":
					db.dequeue(((User)session.getAttribute("user")).getId(), ((Queue)session.getAttribute("queue")).getId());
					break;
			}
			response.sendRedirect("/project/queue.jsp");
		}
	}
}

