package util;

import java.sql.*;

import model.*;

public class DBHandler {
	Connection con;
	Statement st;

	public DBHandler() {
		try {
			String url = "jdbc:mariadb://localhost:3306/netprog_project?user=netprog_project&password=project";
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
			ResultSet rs = st.executeQuery("SELECT * FROM users WHERE username= '" + user.getUsername() + "' AND password=MD5('" + user.getPassword() + "')");
			if(rs.next()) {
				user.setId(rs.getInt(1));				
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Queue[] getQueues() {
		System.out.println("--- DB GET QUEUES ---");
		Queue[] queues = null;
		int i = 0;
		try {
			ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM queues");
			rs.next();
			queues = new Queue[rs.getInt(1)];
			rs = st.executeQuery("SELECT * FROM queues");
			while (rs.next())
				queues[i++] = new Queue(rs.getString("name"), rs.getInt("id"));
		} catch (SQLException e) {
			System.out.println("--- DB FAILED TO GET QUEUES ---");
			e.printStackTrace();
		}
		for(Queue q : queues)
			System.out.println(q.toString());
		return queues;
	}

	public Participant[] getParticipants(int queueId) {
		System.out.println("--- DB GET PARTICIPANTS ---");
		Participant[] participants = null;
		int i = 0;
		try {
			ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM participants WHERE queue_id=" + queueId);
			rs.next();
			participants = new Participant[rs.getInt(1)];
			rs = st.executeQuery("SELECT * FROM participants WHERE queue_id=" + queueId);
			while (rs.next())
				participants[i++] = new Participant(rs.getInt("user_id"),
													rs.getInt("queue_id"),
													rs.getString("location"),
													rs.getString("comment"),
													rs.getBoolean("help"),
													rs.getBoolean("receiving_help"),
													rs.getDate("time_joined"));
		} catch (SQLException e) {
			System.out.println("--- DB FAILED TO GET PARTICIPANTS ---");
			e.printStackTrace();
		}
		for(Participant p : participants)
			System.out.println(p.toString());	
		return participants;
	}

	public String getUsername(int userId) {
		System.out.println("--- DB GET USERNAME ---");
		String username = null;
		try {
			ResultSet rs = st.executeQuery("SELECT username FROM users WHERE id=" + userId);
			rs.next();
			username = rs.getString(1);
		} catch (SQLException e) {
			System.out.println("--- DB FAILED TO GET USERNAME ---");
			e.printStackTrace();
		}
		return username;
	}

	public String getQueueName(int queueId) {
		System.out.println("--- DB GET QUEUE NAME ---");
		String queueName = null;
		try {
			ResultSet rs = st.executeQuery("SELECT name FROM queues WHERE id=" + queueId);
			rs.next();
			queueName = rs.getString(1);
		} catch (SQLException e) {
			System.out.println("--- DB FAILED TO GET QUEUE NAME ---");
			e.printStackTrace();
		}
		return queueName;
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
