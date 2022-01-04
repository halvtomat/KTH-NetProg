<%@ page import="model.Queue" %>
<%@ page import="model.Participant" %>
<%@ page import="model.User" %>
<%@ page import="util.Helper" %>

<html>
	<%
		Queue queue = (Queue)session.getAttribute("queue");
		Participant[] participants = (Participant[])session.getAttribute("participants");
		User user = (User)session.getAttribute("user");
		String[] usernames = (String[])session.getAttribute("usernames");
		out.println("<head><title>[" + participants.length + "] " + queue.getName() + " | Stay A While Longer</title></head>");
	%>
	<body>
		<div style="display: flex; flex-direction: column;">
			<header style="display: flex; flex-direction: row; background-color: #456990; justify-content: space-between; width: 100%;">
				<div style="display: flex; flex-direction: row;">
					<h2>stay a while longer</h2>
					<a style="color: rgba(255,255,255,.5); font-size: 1rem;"href="/project/menu">Home</a>
				</div>
				<div style="display: flex; flex-direction: row;">
					<%  out.println("<span style=color: rgba(255,255,255,.5); font-size: 1rem;>" + user.getUsername() + "</span>"); %>
					<a style="color: rgba(255,255,255,.5); font-size: 1rem;" href="/project/logout">Logout</a>
				</div>
			</header>
			
			<div style="display: flex; flex-direction: row;">
				<%
					out.println("<div>");
					out.println("<h1>" + queue.getName() + "</h1>");
					if(Helper.userInQueue(user, participants)) {
						out.println("<form action=queue method=post>");
						out.println("<input type=submit value=receiving name=action>");
						out.println("</form>");
						out.println("<form action=queue method=post>");
						out.println("<input type=submit value=dequeue name=action>");
						out.println("</form>");
					} else {
						out.println("<form action=queue method=post>");
						out.println("<table>");
						out.println("<tr>" +
										"<td>Location:</td>" +
										"<td><input type=text name=location></td>" +
									"</tr>" +
									"<tr>" +
										"<td>Comment:</td>" +
										"<td><input type=text name=comment></td>" +
									"</tr>" +
									"<tr>" +
										"<td>" +
											"<input type=radio name=help value=true id=help1>" +
											"<label for=help1>Help</label>" +
										"</td>" +
										"<td>" +
											"<input type=radio name=help value=false id=help2>" +
											"<label for=help2>Present</label>" +
										"</td>" +
									"</tr>");
						out.println("</table>");
						out.println("<input type=submit value=enqueue name=action>");
						out.println("</form>");
					}
					out.println("</div>");

					out.println("<div>");
					if(participants.length == 0)
						out.println("<h2>This queue is empty</h2>");
					else {
						out.println("<table>");
						out.println("<tr>" +
										"<th>#</th>" +
										"<th>User</th>" +
										"<th>Location</th>" +
										"<th></th>" +
										"<th>Comment</th>" +
										"<th>Time</th>" +
									"</tr>");
						for(int i = 0; i < participants.length; i++) {
							out.println("<tr>" +
											"<th>" + i + "</th>" +
											"<th>" + usernames[i] + "</th>" +
											"<th>" + participants[i].getUserId() + "</th>" +
											"<th>" + participants[i].getLocation() + "</th>" +
											"<th>" + (participants[i].getHelp() ? "help" : "present") + "</th>" +
											"<th>" + participants[i].getComment() + "</th>" +
											"<th>" + Helper.getTimeSince(participants[i].getTimeJoined()) + " ago</th>" +
										"</tr>");
						}
						out.println("</table>");
					}
					out.println("</div>");
				%>
			</div>
		</div>
	</body>
</html>