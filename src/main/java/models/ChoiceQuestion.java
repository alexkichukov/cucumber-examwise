package models;

import java.util.List;
import java.util.ArrayList;

public class ChoiceQuestion extends Question {
	private List<Answer> answers = new ArrayList<Answer>();
	
	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	public void addAnswer(String answer, boolean isCorrect) {
		this.answers.add(new Answer(answer, isCorrect));
	}
}
