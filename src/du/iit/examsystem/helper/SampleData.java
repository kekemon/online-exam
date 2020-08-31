package du.iit.examsystem.helper;

import java.util.*;
import du.iit.examsystem.*;

public class SampleData {
	
	public static void main(String[] args) {
		/*Teacher teacher = (Teacher) CommonUtits.addUser("Teacher Name", "teacher@iit.du.ac.bd","1545", "teacher");
		Student student1 = (Student) CommonUtits.addUser("First Student", "student1@example.com","1545", "student");
		CommonUtits.addUser("Second Student", "student2@example.com","1545", "student");
		
		Set<MCQ> mcqs = new HashSet<>();
		MCQ mcq1 = new MCQ("My Question", "option1", "option2", "option3", "option4", 1);
		MCQ mcq2 = new MCQ("My Question", "option1", "option2", "option3", "option4", 2);
		MCQ mcq3 = new MCQ("My Question", "option1", "option2", "option3", "option4", 3);
		
		mcqs.add(mcq1);
		mcqs.add(mcq2);
		mcqs.add(mcq3);
		
		CommonUtits.addExam(teacher.getID(), "subject", 10, mcqs);
		
		CommonUtits.addAnswer(student1.getID(), mcq1.getID(), 1);
		CommonUtits.addAnswer(student1.getID(), mcq1.getID(), 1);
		CommonUtits.addAnswer(student1.getID(), mcq2.getID(), 1);*/
		
		Date one = new Date();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date sec = new Date();
		
		long dif = sec.getTime() - one.getTime();
		System.out.println(dif);
	}
}
