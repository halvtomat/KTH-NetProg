<%@ page import="model.Queue" %>
<%@ page import="model.Participant" %>
<%@ page import="model.User" %>

<html>
	<%
		Queue queue = (Queue)session.getAttribute("queue");
		Participant[] participants = (Participant[])session.getAttribute("participants");
		User[] users = (User[])session.getAttribute("users");
		out.println("<head><title>[" + participants.length + "] " + queue.getName() + " | Stay A While Longer</title></head>");
		out.println("<body>");
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
								"<th>" + users[i].getUsername() + "</th>" +
								"<th>" + participants[i].getLocation() + "</th>" +
								"<th>" + (participants[i].getHelp() ? "help" : "present") + "</th>" +
								"<th>" + participants[i].getComment() + "</th>" +
								"<th>" + participants[i].getTimeJoined() + "</th>" +
							"</tr>");
			}
			out.println("</table>")
		}
		out.println("<form action=enqueue method=post>");
		out.println("<table>");
		out.println("<tr>" +
						"<td>Location:</td>" +
						"<td><input type=text name=location></td>" +
					"</tr>");
		out.println("<tr>" +
						"<td>Comment:</td>" +
						"<td><input type=text name=comment></td>" +
					"</tr>");
		out.println("<tr>" +
						"<td>" +
							"<input type=radio name=help value=1 id=help1>" +
							"<label for=help1>Help</label>" +
						"</td>" +
						"<td>" +
							"<input type=radio name=help value=0 id=help2>" +
							"<label for=help2>Present</label>" +
						"</td>" +
					"</tr>");
		out.println("</table>");
		out.println("<input type=submit value=submit>");
		out.println("</body>");
	%>
</html>