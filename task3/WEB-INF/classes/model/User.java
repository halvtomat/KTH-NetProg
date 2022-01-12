package model;

public class User implements java.io.Serializable {
	private String email;
	private String username;
	private String password;
	private int id;

	public User(String email, String username, String password) {
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public User() {
		this.email = "";
		this.username = "";
		this.password = "";
		this.id = -1;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getId() {
		return id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "{\n\temail: " + email + ",\n\tusername: " + username + ",\n\tpassword: " + password + "\n}";
	}
}
