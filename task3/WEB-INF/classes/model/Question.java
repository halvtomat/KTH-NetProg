package model;

public class Question {
	String text;
	String[] options;
	int[] answer;

	public Question(String text, String[] options, int[] answer) {
		this.text = text;
		this.options = options;
		this.answer = answer;
	}

	public String getText() {
		return text;
	}

	public String[] getOptions() {
		return options;
	}

	public int[] getAnswer() {
		return answer;
	}

	@Override
	public String toString() {
		return "{\n\ttext: " + text + ",\n\toptions: " + options[0] + "/" + options[1] + "/" + options[2] + ",\n\tanswer: " + answer[0] + "/" + answer[1] + "/" + answer[2] + "\n}";
	}
}
