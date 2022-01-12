package model;

public class Question implements java.io.Serializable {
	private String text;
	private String[] options;
	private int[] answer;

	public Question(String text, String[] options, int[] answer) {
		this.text = text;
		this.options = options;
		this.answer = answer;
	}

	public Question() {
		this.text = "";
		this.options = new String[0];
		this.answer = new int[0];
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

	public void setText(String text) {
		this.text = text;
	}
	
	public void setOptions(String[] options) {
		this.options = options;
	}

	public void setAnswer(int[] answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "{\n\ttext: " + text + ",\n\toptions: " + options[0] + "/" + options[1] + "/" + options[2] + ",\n\tanswer: " + answer[0] + "/" + answer[1] + "/" + answer[2] + "\n}";
	}
}
