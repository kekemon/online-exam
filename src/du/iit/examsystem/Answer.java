package du.iit.examsystem;

import javax.persistence.*;

@Entity
public class Answer {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int answerID;

	@OneToOne(cascade = CascadeType.ALL)
	private Student student;
	@OneToOne(cascade = CascadeType.ALL)
	private MCQ mcq;
	
	@Column
	private int studentChoice;

	public Answer() {}
	
	public Answer(Student student, MCQ mcq, int studentChoice) {
		this.student = student;
		this.mcq = mcq;
		this.studentChoice = studentChoice;
	}

	public Student getStudent() {
		return student;
	}

	public MCQ getMcq() {
		return mcq;
	}

	public int getStudentChoice() {
		return studentChoice;
	}
	
}
