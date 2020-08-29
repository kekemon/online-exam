package du.iit.examsystem;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Exam {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int examID;

	@Column
	private String subject;
	protected int duration;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Teacher teacher;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<MCQ> mcqs;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Answer> answers;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Result> results;
	
	public Exam() {}
	
	public Exam(Teacher teacher, String subject, int duration, Set<MCQ> mcqs) {
		this.teacher = teacher;
		this.subject = subject;
		this.duration = duration;
		this.mcqs = mcqs;
		this.answers = new HashSet<>();
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void addAnswer(Answer answer) {
		this.answers.add(answer);
	}
}
