package du.iit.examsystem;

import javax.persistence.*;

@Entity
public class Answer {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int ID;

	@ManyToOne(cascade = CascadeType.ALL)
	private User student;
	
	@Column
	private int studentChoice;

	public Answer() {}
	
	public Answer(User student, MCQ mcq, int studentChoice) {
		this.student = student;
		this.studentChoice = studentChoice;
	}
	
	public int getID() {
		return ID;
	}

	public User getStudent() {
		return student;
	}

	public void setStudentChoice(int studentChoice) {
		this.studentChoice = studentChoice;
	}
	
	public int getStudentChoice() {
		return studentChoice;
	}

	@Override
	public boolean equals(Object object) {
		Answer temp = (Answer) object;
		return this.getID() == temp.getID();
	}
}
