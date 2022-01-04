package util;

import java.sql.Timestamp;

import model.Participant;
import model.User;

public class Helper {
	public static String getTimeSince(Timestamp ts) {
		String timeSince;

		long millis = System.currentTimeMillis() - ts.getTime();
		long seconds = millis/1000;
		long minutes = seconds/60;
		long hours = minutes/60;

		if(hours > 0)
			timeSince = hours + " hours";
		else if(minutes > 0)
			timeSince = minutes + " minutes";
		else
			timeSince = seconds + " seconds";

		return timeSince;
	}

	public static boolean userInQueue(User u, Participant[] ps) {
		for(Participant p: ps)
			if(p.getUserId() == u.getId())
				return true;
		return false;
	}
}
