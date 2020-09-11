package du.iit.examsystem;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Teacher extends User implements IExamTaker{

	public Teacher() {}
	
	public Teacher(String fullName, String email, String password) {
		super(fullName, email, password);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Exam addExam(String subject, int duration, Set<MCQ> mcqs) {
		Exam exam = new Exam(this, subject, duration, mcqs);
		return exam;
	}

}
