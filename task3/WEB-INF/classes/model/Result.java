package model;

public class Result implements java.io.Serializable {
	private int userId;
	private int quizId;
	private int score;

	public Result(int userId, int quizId, int score) {
		this.userId = userId;
		this.quizId = quizId;
		this.score = score;
	}

	public Result() {
		this.userId = -1;
		this.quizId = -1;
		this.score = 0;
	}

	public int getUserId() {
		return userId;
	}

	public int getQuizId() {
		return quizId;
	}

	public int getScore() {
		return score;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "{\n\tuserId: " + userId + ",\n\tquizId: " + quizId + "\n\tscore: " + score + "\n}";
	}
}
