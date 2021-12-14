package model;

public class Quiz {
	String subject;
	int id;

	public Quiz(String subject, int id) {
		this.subject = subject;
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "{\n\tsubject: " + subject + ",\n\tid: " + id + "\n}";
	}
}
