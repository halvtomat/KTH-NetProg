<%@ page import="model.Queue" %>
<%@ page import="model.User" %>

<html>
	<style>
		header {
			display: flex;
			flex-direction: row;
			background-color: #456990;
			justify-content: space-between;
			width: 100%;
		}
		header * {
			top: 50%;
			display: block;
			text-decoration: none;
			margin: 10px;
		}
		header div {
			display: flex;
			flex-direction: row;
		}
		.mainContainer {
			display: flex;
			flex-direction: column;
			width: 100%;
			height: 100%;
		}
		.headerTitle {
			color: white;
		}
		.headerLink {
			color: rgba(255,255,255,.5);
			font-size: 1.2rem;
		}
		.username {
			color: white;
			font-size: 1.2rem;
		}
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
		.listContainer {
			width: 100%;
		}
	</style>
	<head>
		<title>Stay A While Longer</title>
	</head>
	<body>
		<div class="mainConatiner">
			<header>
				<div>
					<h2 class="headerTitle">stay a while longer</h2>
					<a class="headerLink" href="/project/menu">Home</a>
				</div>
				<div>
					<% User user = (User)session.getAttribute("user"); out.println("<p class=username>" + user.getUsername() + "</p>"); %>
					<a class="headerLink" href="/project/logout">Logout</a>
				</div>
			</header>
			<div class="listContainer">
				<ul>
					<%
					Queue[] queues = (Queue[])session.getAttribute("queues");
					for( Queue q : queues)
						out.println("<li><a href=/project/queue?id=" + q.getId() +">" + q.getName() + "</a></li>");
					%>
				</ul>
			</div>
		</div>
	</body>
</html>