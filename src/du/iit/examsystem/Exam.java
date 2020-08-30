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
	private User teacher;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<MCQ> mcqs;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Result> results;
	
	public Exam() {}
	
	public Exam(User teacher, String subject, int duration, Set<MCQ> mcqs) {
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

	public Set<Result> getResults() {
		return results;
	}
}
