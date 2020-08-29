package du.iit.examsystem.helper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import du.iit.examsystem.*;

public class Utits {
	
	public static Exam addExam(Teacher teacher, String subject, int duration, Set<MCQ> mcqs) {
		Exam exam = new Exam(teacher, subject, duration, mcqs);
		
		DatabaseUtils.save(exam);
		
		return exam;
	}
	
	public static User addUser(String fullName, String email, String password, String role) {
		User user;
		if (role.equals("teacher")) {
			user = new Teacher(fullName, email, password, role);
		} else {
			user = new Student(fullName, email, password, role);
		}
		
		DatabaseUtils.save(user);
		
		return user;
	}
	
	public static void answerMCQ(Student student, Exam exam, MCQ mcq, int studentChoice) {
		Answer answer = new Answer(student, mcq, studentChoice);
		exam.addAnswer(answer);

		DatabaseUtils.update(exam);
	}
	
	public static void main(String[] args) {
//		Teacher teacher = (Teacher) addUser("Teacher Name", "email@dd.iit.du.ac.bd","1545", "teacher");
//		Student student = (Student) addUser("Student Name", "email@example.com","1545", "student");
//		
//		Set<MCQ> mcqs = new HashSet<>();
//		MCQ mcq = new MCQ("My Question", "option1", "option2", "option3", "option4", 1);
//		mcqs.add(mcq);
//		Exam exam = addExam(teacher, "subject", 90, mcqs);
//		
//		answerMCQ(student, exam, mcq, 1);
		
		List<Object> aa = DatabaseUtils.getList(Exam.class);
		for (Object object : aa) {
			Exam exam = (Exam) object;
			System.out.println(exam.getSubject());
		}
	}
}
