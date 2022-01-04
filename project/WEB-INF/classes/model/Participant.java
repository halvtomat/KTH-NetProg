package model;

import java.util.Date;

public class Participant {
	int userId;
	int queueId;
	String location;
	String comment;
	boolean help;
	boolean receivingHelp;
	Date timeJoined;

	public Participant(int userId, int queueId, String location, String comment, boolean help, boolean receivingHelp, Date timeJoined) {
		this.userId = userId;
		this.queueId = queueId;
		this.location = location;
		this.comment = comment;
		this.help = help;
		this.receivingHelp = receivingHelp;
		this.timeJoined = timeJoined;
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
