package model;

public class Result {
	int userId;
	int quizId;
	int score;

	public Result(int userId, int quizId, int score) {
		this.userId = userId;
		this.quizId = quizId;
		this.score = score;
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

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "{\n\tuserId: " + userId + ",\n\tquizId: " + quizId + "\n\tscore: " + score + "\n}";
	}
}
