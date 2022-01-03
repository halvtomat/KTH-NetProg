<%@ page import="model.Queue" %>

<html>
	<head>
		<title>Stay A While Longer</title>
	</head>
	<body>
		<h1>Stay a while longer</h1>
		<ul>
			<%
			Queue[] queues = (Queue[])session.getAttribute("queues");
			for( Queue q : queues)
				out.println("<li><a href=/app/queue?id=" + q.getId() +">" + q.getName() + "</a></li>");
			%>
		</ul>
	</body>
</html>