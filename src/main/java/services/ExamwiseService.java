package services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import db.Repository;
import models.Answer;
import models.Exam;
import models.SolutionAnswer;

public class ExamwiseService {
	public String createChoiceQuestion(String name, String description, List<Answer> answers) {
		String errorMessage = validateChoiceQuestion(name, answers);

		if (errorMessage != null) {
			return errorMessage;
		}
		
		return "Успешно създаден въпрос с затворен отговор.";
	}

	private String validateChoiceQuestion(String name, List<Answer> answers) {
		Set<String> answerNames = new HashSet<String>();
		boolean containsCorrectAnswer = false;

		for (Answer a : answers) {
			answerNames.add(a.getAnswer());

			if (!containsCorrectAnswer && a.isCorrect()) {
				containsCorrectAnswer = true;
			}
		}
		
		// Does not have a name
		if (name == null) {
			return "Моля добавете име на въпроса.";
		}
		
		// Does not have answers
		if (answers.size() <= 0) {
			return "Моля добавете поне един отговор.";
		}

		// Duplicate answers found
		if (answerNames.size() != answers.size()) {
			return "Въпросът съдържа дублирани отговори, моля премахнете дубликатите.";
		}

		// Does not contain correct answer
		if (!containsCorrectAnswer) {
			return "Въпросът трябва да съдържа поне един правилен отговор.";
		}

		return null;
	}

	public String createOpenQuestion(String name, String description) {
		String errorMessage = validateOpenQuestion(name);

		if (errorMessage != null) {
			return errorMessage;
		}
		
		return "Успешно създаден въпрос с отворен отговор.";
	}

	private String validateOpenQuestion(String name) {
		// Does not have a name
		if (name == null) {
			return "Моля добавете име на въпроса.";
		}

		return null;
	}
	
	public void openExam(String name) {
		for (Exam e : Repository.getExams()) {
    		if (e.getName().equals(name)) {
    			e.setOpen(true);
    		}
    	}
	}
	
	public void closeExam(String name) {
		for (Exam e : Repository.getExams()) {
    		if (e.getName().equals(name)) {
    			e.setOpen(false);
    		}
    	}
	}

	public Exam selectExam(String name) {
		for (Exam e : Repository.getExams()) {
    		if (e.getName().equals(name)) {
    			return e;
    		}
    	}
    	
    	return null;
	}
	
	public String startExam(Exam e) {
		if (!e.isOpen()) {
			return "Изпитът все още не е започнал!";
		}
		
		return null;
	}
	
	public String submitExam(Exam e, List<SolutionAnswer> solution) {
		if (solution.size() <= 0) {
			return "Вашето решение е празно, сигурни ли сте че искате да го изпратите?";
		}
		
		if (e.getQuestions().size() != solution.size()) {
			return "Вашето решение съдържа празни въпроси, сигурни ли сте че искате да го изпратите?";
		}
		
		return "Решението беше изпратено успешно!";
	}
	
	
	public String confirmDialog() {
		return "Решението беше изпратено успешно!";
	}

}
