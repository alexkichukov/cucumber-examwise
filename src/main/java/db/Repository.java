package db;

import models.Question;
import models.ChoiceQuestion;
import models.Exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository
{
    private static List<Exam> exams = new ArrayList<Exam>();

    static
    {
    	// Create exam
    	Exam exam = new Exam();
    	exam.setId(1);
    	exam.setName("Изпит 1");
    	exam.setOpen(true);
    	exam.setDescription("Допълнителен текст");
    	
    	List<Question> examQuestions = new ArrayList<Question>();
    	
    	// Create question 1
    	ChoiceQuestion question1 = new ChoiceQuestion();
    	question1.setId(1);
    	question1.setName("Колко планети има в нашата слънчева система?");
    	question1.addAnswer("7", false);
    	question1.addAnswer("8", true);
    	question1.addAnswer("9", false);
    	question1.addAnswer("10", false);
    	
    	// Create question 2
    	ChoiceQuestion question2 = new ChoiceQuestion();
    	question2.setId(2);
    	question2.setName("Коя е столицата на Франция?");
    	question2.addAnswer("Лондон", false);
    	question2.addAnswer("Париж", true);
    	question2.addAnswer("Москва", false);
    	question2.addAnswer("София", false);
    	
    	// Add questions to exam
    	examQuestions.add(question1);
    	examQuestions.add(question2);
    	exam.setQuestions(examQuestions);
    	
    	// Store in model
    	exams.add(exam);
    }

    public static List<Exam> getExams()
    {
        return exams;
    }
}
