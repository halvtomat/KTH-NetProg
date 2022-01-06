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

	public boolean registerUser(User user) {
		System.out.println("--- DB REGISTER USER ---");
		try {
			st.executeQuery("INSERT INTO users (username, password) VALUES ('" + user.getUsername() + "', MD5('" + user.getPassword() + "'))");
			ResultSet rs = st.executeQuery("SELECT * FROM users WHERE username= '" + user.getUsername() + "' AND password=MD5('" + user.getPassword() + "')");
			rs.next();
			user.setId(rs.getInt(1));
			return true;
		} catch (SQLException e) {
			System.out.println("--- DB FAILED TO REGISTER USER ---");
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
													rs.getTimestamp("time_joined"));
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

	public void enqueue(Participant participant) {
		System.out.println("--- DB ENQUEUE ---");
		try {
			ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM participants WHERE user_id=" + participant.getUserId() + " AND queue_id=" + participant.getQueueId());
			rs.next();
			if(rs.getInt(1) == 0)
				st.executeQuery("INSERT INTO participants (user_id, queue_id, location, comment, help) VALUES (" +
					participant.getUserId() + "," +
					participant.getQueueId() + ",\'" +
					participant.getLocation() + "\',\'" +
					participant.getComment() + "\'," +
					participant.getHelp() + ")");
		} catch (SQLException e) {
			System.out.println("--- DB FAILED TO ENQUEUE ---");
			e.printStackTrace();
		}
	}

	public void dequeue(int userId, int queueId) {
		System.out.println("--- DB DEQUEUE ---");
		try {
			ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM participants WHERE user_id=" + userId + " AND queue_id=" + queueId);
			rs.next();
			if(rs.getInt(1) != 0)
				st.executeQuery("DELETE FROM participants WHERE user_id=" + userId + " AND queue_id=" + queueId);
		} catch (SQLException e) {
			System.out.println("--- DB FAILED TO DEQUEUE ---");
			e.printStackTrace();
		}
	}

	public void receiveHelp(int userId, int queueId) {
		System.out.println("--- DB RECEIVE HELP ---");
		try {
			ResultSet rs  = st.executeQuery("SELECT COUNT(*) FROM participants WHERE user_id=" + userId + " AND queue_id=" + queueId);
			rs.next();
			if(rs.getInt(1) != 0)
				st.executeQuery("UPDATE participants SET receiving_help=" + true + " WHERE user_id=" + userId + " AND queue_id=" + queueId);
		} catch (SQLException e) {
			System.out.println("--- DB FAILED TO RECEIVE HELP ---");
			e.printStackTrace();
		}
	}
}
