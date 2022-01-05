<%@ page import="model.Queue" %>
<%@ page import="model.User" %>

<html>
	<style>
	ul {
		list-style-type: none;
		padding: 0;
		width: 80%;
		margin: 0 auto;
	}
	li {
		margin: 20px;
		border: 1px solid rgba(0,0,0,.125);
		border-radius: 4px;
		text-align: center;
	}
	li a {
		display: block;
		padding: 30px;
		overflow: auto;
		text-decoration: none;
		color: #456990;
		font-size: 1.5rem;
	}
	li a:hover {
		background-color: rgba(0,0,0,.1);
	}
	</style>
	<head>
		<title>Stay A While Longer</title>
	</head>
	<body>
		<div style="display: flex; flex-direction: column;">
			<header style="display: flex; flex-direction: row; background-color: #456990; justify-content: space-between; width: 100%;">
				<div style="display: flex; flex-direction: row;">
					<h2 style="color: white">stay a while longer</h2>
					<a style="color: rgba(255,255,255,.5); font-size: 1rem;"href="/project/menu">Home</a>
				</div>
				<div style="display: flex; flex-direction: row;">
					<% User user = (User)session.getAttribute("user"); out.println("<span style=color: rgba(255,255,255,.5); font-size: 1rem;>" + user.getUsername() + "</span>"); %>
					<a style="color: rgba(255,255,255,.5); font-size: 1rem;" href="/project/logout">Logout</a>
				</div>
			</header>
			<div style="width 100%;">
				<ul>
					<%
					Queue[] queues = (Queue[])session.getAttribute("queues");
					for( Queue q : queues)
						out.println("<li style=padding: 1.25rem; flex: 1 1 auto;><a href=/project/queue?id=" + q.getId() +">" + q.getName() + "</a></li>");
					%>
				</ul>
			</div>
		</div>
	</body>
</html>