package model;

public class User {
	String email;
	String username;
	String password;
	int id;

	public User(String email, String username, String password) {
		this.email = email;
		this.username = username;
		this.password = password;
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

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "{\n\temail: " + email + ",\n\tusername: " + username + ",\n\tpassword: " + password + "\n}";
	}
}
