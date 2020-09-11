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

	public static int[] getMCQs(int examID) {
		Exam exam = getExam(examID);
		
		Set<MCQ> mcqs = exam.getMcqs();
		int[] arr = new int[mcqs.size()];
		int i = 0;
		for (MCQ mcq : mcqs) {
			arr[i++] = mcq.getID();
		}
		Arrays.sort(arr);
		return arr;
	}
	
	public static int getNextMCQ(int examID, int currentMCQID) {
		int[] arr = getMCQs(examID);
		int index = Arrays.binarySearch(arr, currentMCQID);
		return index+1 >= arr.length ? -1 : arr[index+1];
	}
	
	public static int getPrevMCQ(int examID, int currentMCQID) {
		int[] arr = getMCQs(examID);
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
		MCQ mcq = getMCQ(mcqID);
		for (Answer answer : mcq.getAnswers()) {
			if (answer.getStudent().getID() == studentID) {
				return answer.getStudentChoice() +"";
			}
		}
		return "";
	}
	
	public static Exam addExam(int teacherID, String subject, int duration, Set<MCQ> mcqs) {
		Teacher teacher = (Teacher)getUser(teacherID);
		Exam exam = teacher.addExam(subject, duration, mcqs);
		
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
		Student student = (Student)getUser(studentID);
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
		DatabaseUtils.update(mcq);
	}
	
	public static ExamSheet getExamSheet(int studentID, int examID) {
		Exam exam = getExam(examID);
		
		for (ExamSheet examSheet : exam.getExamSheets()) {
			if (examSheet.getStudent().getID() == studentID) {
				return examSheet;
			}
		}
		
		return null;
	}
	
	public static long getRemainTime(int studentID, int examID) {
		ExamSheet examSheet = getExamSheet(studentID, examID);
		Exam exam = getExam(examID);
		Date now = new Date();
		long remain = exam.getDuration()*60*1000 - (now.getTime()-examSheet.getStartDateTime().getTime());
		return remain/1000;
	}
	
	
	public static boolean isFinisedExam(int studentID, int examID) {
		ExamSheet examSheet = getExamSheet(studentID, examID);
		if (examSheet == null || examSheet.getEndDateTime() == null) {
			return false;
		} 
		return true;
	}	
	
	public static void finisExam(Exam exam, ExamSheet examSheet) {
		if (examSheet.getEndDateTime() == null) {
			int correct = 0;
			int wrong = 0;
			int skiped = 0;
			
			for (MCQ mcq : exam.getMcqs()) {
				Answer answer = mcq.getAnswer(examSheet.getStudent());
				if (answer == null) {
					skiped++;
				} else if (answer != null && answer.getStudentChoice() == mcq.getCorrectAns()) {
					correct++;
				} else {
					wrong++;
				}
			}
			
			examSheet.mark(correct, skiped, wrong);
			DatabaseUtils.update(examSheet);
		}
	}
		
	
	public static boolean startExam(int studentID, int examID) {
		ExamSheet examSheet = getExamSheet(studentID, examID);
		if (examSheet != null) { // If student already started exam
			if (examSheet.getEndDateTime() == null) { // If exam not finish yet
				return true;
			}
			return false;
		} 
		
		// If student not started exam yet
		Exam exam = getExam(examID);
		ExamSheet mexamSheet = new ExamSheet((Student)getUser(studentID), new Date());
		exam.getExamSheets().add(mexamSheet);
		DatabaseUtils.update(exam);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1000 * 60 * exam.getDuration());
					CommonUtits.finisExam(exam, mexamSheet);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		return true;
	}
}
