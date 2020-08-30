package du.iit.examsystem;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class MCQ{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int ID;

	@Column
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	
	private int correctAns;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Answer> answers;
	
	public MCQ() {}
	
	public MCQ(String question, String option1, String option2, String option3, String option4, int correctAns) {
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.correctAns = correctAns;
		this.answers = new HashSet<>();
	}

	public int getID() {
		return ID;
	}
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOption1() {
		return option1;
	}
	
	public String getOption2() {
		return option2;
	}

	public String getOption3() {
		return option3;
	}

	public String getOption4() {
		return option4;
	}

	public int getCorrectAns() {
		return correctAns;
	}
	
	public Set<Answer> getAnswers() {
		return answers;
	}
	
}
