package du.iit.examsystem.helper;

import java.util.*;
import du.iit.examsystem.*;

public class SampleData {
	
	public static void main(String[] args) {
		Teacher teacher = (Teacher) CommonUtits.addUser("Teacher Name", "email@dd.iit.du.ac.bd","1545", "teacher");
		Student student = (Student) CommonUtits.addUser("Student Name", "email@example.com","1545", "student");
		
		Set<MCQ> mcqs = new HashSet<>();
		MCQ mcq1 = new MCQ("My Question", "option1", "option2", "option3", "option4", 1);
		MCQ mcq2 = new MCQ("My Question", "option1", "option2", "option3", "option4", 2);
		MCQ mcq3 = new MCQ("My Question", "option1", "option2", "option3", "option4", 3);
		
		mcqs.add(mcq1);
		mcqs.add(mcq2);
		mcqs.add(mcq3);
		
		CommonUtits.addExam(teacher.getID(), "subject", 10, mcqs);
		
		CommonUtits.addAnswer(student.getID(), mcq1.getID(), 1);
		CommonUtits.addAnswer(student.getID(), mcq1.getID(), 1);
		CommonUtits.addAnswer(student.getID(), mcq2.getID(), 1);
		
		//Utits.addAnswer(2, 1, 2, 1);
		
	}
}
