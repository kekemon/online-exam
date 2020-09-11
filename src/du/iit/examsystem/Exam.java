package du.iit.examsystem;

import java.util.Set;
import javax.persistence.*;

@Entity
public class Exam {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int ID;
	
	@Column
	private String subject;
	protected int duration;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Teacher teacher;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<MCQ> mcqs;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<ExamSheet> examSheets;
	
	public Exam() {}
	
	public Exam(Teacher teacher, String subject, int duration, Set<MCQ> mcqs) {
		this.teacher = teacher;
		this.subject = subject;
		this.duration = duration;
		this.mcqs = mcqs;
	}
	
	public int getID() {
		return ID;
	}

	public User getTeacher() {
		return teacher;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public int getDuration() {
		return duration;
	}

	public Set<MCQ> getMcqs() {
		return mcqs;
	}

	public Set<ExamSheet> getExamSheets() {
		return examSheets;
	}

	public ExamSheet getExamSheet(User student) {
		for (ExamSheet examSheet : examSheets) {
			if (examSheet.getStudent().equals(student)) {
				return examSheet;
			}
		}
		return null;
	}

	@Override
	public boolean equals(Object object) {
		Exam temp = (Exam) object;
		return this.getID() == temp.getID();
	}
}
