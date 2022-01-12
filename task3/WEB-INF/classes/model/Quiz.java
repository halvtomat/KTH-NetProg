package model;

public class Quiz implements java.io.Serializable {
	private String subject;
	private int id;

	public Quiz(String subject, int id) {
		this.subject = subject;
		this.id = id;
	}

	public Quiz() {
		this.subject = "";
		this.id = -1;
	}

	public String getSubject() {
		return subject;
	}

	public int getId() {
		return id;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "{\n\tsubject: " + subject + ",\n\tid: " + id + "\n}";
	}
}
