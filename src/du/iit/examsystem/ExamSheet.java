package du.iit.examsystem;

import java.util.Date;
import javax.persistence.*;

@Entity
public class ExamSheet {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int ID;

	@ManyToOne(cascade = CascadeType.ALL)
	private Student student;
	
	@Basic
    private Date startDateTime;
	@Basic
    private Date endDateTime;
	
	@Column
	private int correct;
	
	@Column
	private int skipped;
	
	@Column
	private int wronng;
	
	public ExamSheet() {}
	
	public ExamSheet(Student student, Date startDateTime) {
		this.student = student;
		this.startDateTime = startDateTime;
	}
	
	public int getID() {
		return ID;
	}

	public void mark(int correct, int skipped, int wrong) {
		this.endDateTime = new Date();
		this.correct = correct;
		this.skipped = skipped;
		this.wronng = wrong;
	}

	public User getStudent() {
		return student;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public int getCorrect() {
		return correct;
	}
	
	public int getSkipped() {
		return skipped;
	}
	
	public int getWrong() {
		return wronng;
	}
	
	@Override
	public boolean equals(Object object) {
		ExamSheet temp = (ExamSheet) object;
		return this.getID() == temp.getID();
	}
}
