package examwise;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import models.Answer;
import models.Exam;
import models.SolutionAnswer;
import services.ExamwiseService;

public class StepDefinitions {
	private String questionType;
	private String questionName;
	private String description;
	private List<Answer> answers = new ArrayList<Answer>();
	private Exam exam;
	private List<SolutionAnswer> solution = new ArrayList<SolutionAnswer>();
	private String message;

    @Given("Потребителят е студент и отваря страницата за изпити")
    public void loginAsStudentAndOpenExamPage() {
        // No real app yet
    }

    @Given("изпит {string} все още не е започнал")
    public void examIsInactive(String name) {
    	ExamwiseService service = new ExamwiseService();
    	
    	service.closeExam(name);
    }

    @When("потребителят отвори изпит {string} за решаване")
    public void selectExam(String name) {
    	ExamwiseService service = new ExamwiseService();
    	
    	Exam exam = service.selectExam(name);
    	
    	System.out.println("SELECTED GUY:");
    	System.out.println(name);
    	System.out.println(exam.getName());
    	
    	this.exam = exam;
    	
    	this.message = service.startExam(exam);
    }

    @When("натисне бутона за изпращане на решението")
    public void submitSolution() {
    	ExamwiseService service = new ExamwiseService();
    	
    	this.message = service.submitExam(this.exam, this.solution);
    }

    @Then("вижда диалог за потвърждение {string}")
    public void openConfirmDialog(String expectedMessage) {
    	assertEquals(expectedMessage, message);
    }

    @Then("потвърждава диалога")
    public void confirmDialog() {
    	ExamwiseService service = new ExamwiseService();
    	
    	this.message = service.confirmDialog();
    }

    @When("отговори на следните въпроси от изпита:")
    public void answerExamQuestion(DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
	      
        for (Map<String, String> row : rows) {
            String question = row.get("Въпрос");
            String answer = row.get("Отговор");
            List<String> answers = new ArrayList<String>();
            answers.add(answer);
            solution.add(new SolutionAnswer(question, answers));
        }
    }
    
    @When("въведе {string} за въпроса {string}")
    public void въведе_за_въпроса(String answer, String question) {
    	List<String> answers = new ArrayList<String>();
        answers.add(answer);
        solution.add(new SolutionAnswer(question, answers));
    }

    @Given("времето за решаване е изтекло")
    public void examCloses() {
		ExamwiseService service = new ExamwiseService();
    	
    	service.closeExam(this.exam.getName());
    }

    @Given("Потребителят е учител и отваря формата за създаване на въпрос")
    public void loginAsTeacherAndOpenCreateQuestionPage() {
        // No real app yet
    }

    @When("избeре {string}")
    public void setQuestionType(String type) {
        questionType = type;
    }

    @When("въведе {string} в полето за име на въпрос")
    public void setQuestionName(String name) {
    	questionName = name;
    }

    @When("добави следните отговори:")
    public void addAnswers(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
      
        for (Map<String, String> row : rows) {
            String answer = row.get("Отговор");
            Boolean isCorrect = row.get("Верен").equals("true");
            answers.add(new Answer(answer, isCorrect));
        }
    }

    @When("натисне бутона за създаване")
    public void createQuestion() {
    	ExamwiseService service = new ExamwiseService();
        
    	if (questionType.equals("затворен отговор")) {
    		this.message = service.createChoiceQuestion(questionName, description, answers);
    	} else if (questionType.equals("отворен отговор")) {
    		this.message = service.createOpenQuestion(questionName, description);
    	}
    }

    @Then("вижда съобщение {string}")
    public void showMessage(String expectedMessage) {
    	assertEquals(expectedMessage, message);
    }

    @Then("вижда съобщение за грешка {string}")
    public void showErrorMessage(String expectedMessage) {
    	assertEquals(expectedMessage, message);
    }

    @When("въведе {string} в полето за допълнителен текст")
    public void setDescription(String desc) {
        description = desc;
    }
}

