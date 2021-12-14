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
		return quizzes;
	}

	public Result[] getResults(User user) {
		System.out.println("--- DB GET RESULTS ---");
		Result[] results = null;
		int i = 0;
		try {
			ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM results where user_id=" + user.getId());
			rs.next();
			results = new Result[rs.getInt(1)];
			rs = st.executeQuery("SELECT * FROM results WHERE user_id=" + user.getId());
			while (rs.next())
				results[i++] = new Result(rs.getInt("user_id"), rs.getInt("quiz_id"), rs.getInt("score"));
		} catch (SQLException e) {
			System.out.println("--- DB FAILED TO GET RESULTS ---");
			e.printStackTrace();
		}
		return results;
	}
}
