package models;

import java.util.List;
import java.util.ArrayList;

public class SolutionAnswer {
	private String question;
	private List<String> answers = new ArrayList<String>();
	
	public SolutionAnswer(String question, List<String> answers) {
		this.question = question;
		this.answers = answers;
	}

	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question ;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
}
