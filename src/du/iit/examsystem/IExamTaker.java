package du.iit.examsystem;

import java.util.Set;

public interface IExamTaker {
	public Exam addExam(String subject, int duration, Set<MCQ> mcqs);
}
