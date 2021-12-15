<%@ page import="model.Quiz" %>
<%@ page import="model.Question" %>

<html>
	<head>
		<title>ANSWER THE QUESTIONS</title>
	</head>
	<body>
		<h1>Please respond</h1>
		<form action="quiz" method="post">
			<%
				Question[] questions= (Question[])session.getAttribute("questions");
				for(int i = 0; i < questions.length; i++) {
					out.println("<p>" + questions[i].getText() + "</p>");
					String[] options = questions[i].getOptions();
					for (int j = 0; j < options.length; j++) {
						String inp = "<input type=checkbox id=q" + i + "o" + j + " name=q" + i + "o" + j + ">";
						String lab = "<label for=q" + i + "o" + j + " >" + options[j] + "</label>";
						out.println(inp + lab + "<br>");
					}
				}
			%>
			<input type="submit" value="answer">
		</form>
	</body>
</html>