<%@ page import="model.Quiz" %>
<%@ page import="model.Result" %>

<html>
	<head>
		<title>Quiz selection time</title>
	</head>
	<body>
		<h1>Select a quiz below</h1>
		<ul>
			<%
			Quiz[] quizzes = (Quiz[])session.getAttribute("quizzes");
			for( Quiz q : quizzes) {
				out.println("<li><a href=/app/Quiz?id=" + q.getId() +">" + q.getSubject() + "</a></li>");
			}
			%>
		</ul>
		<h2>Previous scores</h2>
		<ul>
			<%
			Result[] results = (Result[])session.getAttribute("results");
			for( Result r : results) {
				out.println("<li>Quiz: " + r.getQuizId() + " , Score: " + r.getScore() + "</li>");
			}
			%>
		</ul>
	</body>
</html>