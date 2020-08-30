package du.iit.examsystem.helper;

import java.util.*;
import du.iit.examsystem.*;

public class CommonUtits {
	
	public static final String SESSION_KEY_USERID = "SESSION_KEY_USERID";
	
	public static User getUser(int ID) {
		List<Object> objects = DatabaseUtils.getList(User.class);
		for (Object object : objects) {
			User mObject = (User) object;
			if (mObject.getID() == ID) {
				return mObject;
			}
		}
		return null;
	}
	
	public static Exam getExam(int ID) {
		List<Object> objects = DatabaseUtils.getList(Exam.class);
		for (Object object : objects) {
			Exam mObject = (Exam) object;
			if (mObject.getID() == ID) {
				return mObject;
			}
		}
		return null;
	}

	public static int getNextMCQ(int examID, int currentMCQID) {
		Exam exam = getExam(examID);
		
		Set<MCQ> mcqs = exam.getMcqs();

		int[] arr = new int[mcqs.size()];
		int i = 0;
		for (MCQ mcq : mcqs) {
			arr[i++] = mcq.getID();
		}
		Arrays.sort(arr);
		int index = Arrays.binarySearch(arr, currentMCQID);
		return index+1 >= mcqs.size() ? -1 : arr[index+1];
	}

	public static int getPrevMCQ(int examID, int currentMCQID) {
		Exam exam = getExam(examID);
		
		Set<MCQ> mcqs = exam.getMcqs();
		int[] arr = new int[mcqs.size()];
		int i = 0;
		for (MCQ mcq : mcqs) {
			arr[i++] = mcq.getID();
		}
		Arrays.sort(arr);
		int index = Arrays.binarySearch(arr, currentMCQID);
		return index-1 < 0 ? -1 : arr[index-1];
	}

	
	public static MCQ getMCQ(int ID) {
		List<Object> objects = DatabaseUtils.getList(MCQ.class);
		for (Object object : objects) {
			MCQ mObject = (MCQ) object;
			if (mObject.getID() == ID) {
				return mObject;
			}
		}
		return null;
	}

	public static String getAnswer(int studentID, int mcqID) {
		System.out.println(studentID + " " + mcqID);
		MCQ mcq = getMCQ(mcqID);
		for (Answer answer : mcq.getAnswers()) {
			if (answer.getStudent().getID() == studentID) {
				System.out.println("N " +answer.getStudent().getID());
				return answer.getStudentChoice() +"";
			}
		}
		return "";
	}
	
	public static Exam addExam(int teacherID, String subject, int duration, Set<MCQ> mcqs) {
		Exam exam = new Exam(getUser(teacherID), subject, duration, mcqs);
		
		DatabaseUtils.save(exam);
		
		return exam;
	}
	
	public static User addUser(String fullName, String email, String password, String role) {
		User user;
		if (role.equals("teacher")) {
			user = new Teacher(fullName, email, password);
		} else {
			user = new Student(fullName, email, password);
		}
		
		DatabaseUtils.save(user);
		
		return user;
	}
	
	public static void addAnswer(int studentID, int mcqID, int studentChoice) {
		User student = getUser(studentID);
		MCQ mcq = getMCQ(mcqID);
		Set<Answer> answers = mcq.getAnswers();
		for (Answer answer : answers) {
			if (answer.getStudent().getID() == studentID) {
				answer.setStudentChoice(studentChoice);
				DatabaseUtils.update(answer);
				return;
			}
		}
		
		Answer answer = new Answer(student, mcq, studentChoice);
		mcq.getAnswers().add(answer);
		//exam.setAnswers(answers);
		DatabaseUtils.update(mcq);
	}
	
	public static boolean startExam(int studentID, int examID) {
		Exam exam = getExam(examID);
		
		for (Result result : exam.getResults()) {
			if (result.getStudent().getID() == studentID) {
				if (result.getEndDateTime() != null) {
					return false;
				}
				return true;
			}
		}
		Result result = new Result(getUser(studentID), new Date(), null, 0);
		exam.getResults().add(result);
		DatabaseUtils.update(exam);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1000 * 60 * exam.getDuration());
					result.publishResult(10);
					DatabaseUtils.update(result);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		return true;
	}
}
