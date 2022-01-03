package util;

import java.sql.*;
import model.*;

public class DBHandler {
	Connection con;
	Statement st;

	public DBHandler() {
		try {
			String url = "jdbc:mariadb://localhost:3306/netprog_task3?user=task3&password=task3";
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url);
			st = con.createStatement();
			System.out.println("--- CONNECTED TO DATABASE ---");
		} catch( ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean authenticate(User user) {
		try {
			ResultSet rs = st.executeQuery("SELECT * FROM users WHERE username= '" + user.getEmail() + "' AND password=MD5('" + user.getPassword() + "')");
			if(rs.next()) {
				user.setId(rs.getInt(1));				
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Quiz[] getQuizzes() {
		System.out.println("--- DB GET QUIZZES ---");
		Quiz[] quizzes = null;
		int i = 0;
		try {
			ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM quizzes");
			rs.next();
			quizzes = new Quiz[rs.getInt(1)];
			rs = st.executeQuery("SELECT * FROM quizzes");
			while (rs.next())
				quizzes[i++] = new Quiz(rs.getString("subject"), rs.getInt("id"));
		} catch (SQLException e) {
			System.out.println("--- DB FAILED TO GET QUIZZES ---");
			e.printStackTrace();
		}
		for(Quiz q : quizzes)
			System.out.println(q.toString());
		return quizzes;
	}

	public Result[] getResults(User user) {
		System.out.println("--- DB GET RESULTS ---");
		Result[] results = null;
		int i = 0;
		try {
			ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM results WHERE user_id=" + user.getId());
			rs.next();
			results = new Result[rs.getInt(1)];
			rs = st.executeQuery("SELECT * FROM results WHERE user_id=" + user.getId());
			while (rs.next())
				results[i++] = new Result(rs.getInt("user_id"), rs.getInt("quiz_id"), rs.getInt("score"));
		} catch (SQLException e) {
			System.out.println("--- DB FAILED TO GET RESULTS ---");
			e.printStackTrace();
		}
		for(Result r : results)
			System.out.println(r.toString());	
		return results;
	}

	public Question[] getQuestions(int quizId) {
		System.out.println("--- DB GET QUESTIONS ---");
		Question[] questions = null;
		int i = 0;
		try {
			ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM selector WHERE quiz_id=" + quizId);
			rs.next();
			questions = new Question[rs.getInt(1)];
			int[] ids = new int[rs.getInt(1)];
			rs = st.executeQuery("SELECT * FROM selector WHERE quiz_id=" + quizId);
			while(rs.next())
				ids[i++] = rs.getInt("question_id");
			i = 0;
			for (int j : ids) {
				rs = st.executeQuery("SELECT * FROM questions WHERE id=" + j);
				rs.next();
				String[] stringAnswers = rs.getString("answer").split("/");
				int[] answers = new int[stringAnswers.length];
				for (int k = 0; k < stringAnswers.length; k++)
					answers[k] = Integer.parseInt(stringAnswers[k]);
				questions[i++] = new Question(rs.getString("text"), rs.getString("options").split("/"), answers);
			}
		} catch (SQLException e) {
			System.out.println("--- DB FAILED TO GET QUESTIONS ---");
			e.printStackTrace();
		}
		for(Question q : questions)
			System.out.println(q.toString());	
		return questions;
	}

	public void newResult(User user, int quizId, int score) {
		System.out.println("--- DB NEW RESULT ---");
		try {
			ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM results WHERE user_id=" + user.getId() + " AND quiz_id=" + quizId);
			rs.next();
			if(rs.getInt(1) == 0)
				st.executeQuery("INSERT INTO results (user_id, quiz_id, score) VALUES (" + user.getId() + ", " + quizId + ", " + score + ")");
			else
				st.executeQuery("UPDATE results SET score=" + score + " WHERE user_id=" + user.getId() + " AND quiz_id=" + quizId);
		} catch (SQLException e) {
			System.out.println("--- DB FAILED TO UPDATE RESULT ---");
			e.printStackTrace();
		}
	}
}
