package models;

public class Answer {
	private String answer;
	private boolean isCorrect;
	
	public Answer(String answer, boolean isCorrect) {
		this.answer = answer;
		this.isCorrect = isCorrect;
	}
	
	public boolean isCorrect() {
		return isCorrect;
	}
	
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
