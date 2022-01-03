package model;

public class Participant {
	int userId;
	int queueId;
	String location;
	String comment;
	boolean help;
	boolean receivingHelp;
	Date timeJoined;

	public Result(int userId, int quizId, String location, String comment, boolean help) {
		this.userId = userId;
		this.quizId = quizId;
		this.location = location;
		this.comment = comment;
		this.help = help;
		this.receivingHelp = false;
		this.timeJoined = null;
	}

	public int getUserId() {
		return userId;
	}

	public int getQueueId() {
		return queueId;
	}

	public String getLocation() {
		return location;
	}

	public String getComment() {
		return comment;
	}

	public boolean getHelp() {
		return help;
	}

	public boolean getReceivingHelp() {
		return receivingHelp;
	}

	public Date getTimeJoined() {
		return timeJoined;
	}

	public void setReceiving() {
		this.receivingHelp = true;
	}

	public void update(String location, String comment, boolean help) {
		this.location = location;
		this.comment = comment;
		this.help = help;
	}

	@Override
	public String toString() {
		return  "{\n\tuserId: " + userId +
				",\n\tqueueId: " + queueId +
				"\n\tlocation: " + location +
				"\n\tcomment: " + comment +
				"\n\thelp: " + help +
				"\n\treceivingHelp: " + receivingHelp +
				"\n\ttimeJoined: " + timeJoined +
				"\n}";
	}
}
