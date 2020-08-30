package du.iit.examsystem;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Result {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int ID;

	@ManyToOne(cascade = CascadeType.ALL)
	private User student;
	
	@Basic
    private Date startDateTime;
	@Basic
    private Date endDateTime;
	
	@Column
	private int marks;
	
	public Result() {}
	
	public Result(User student, Date startDateTime, Date endDateTime, int marks) {
		this.student = student;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.marks = marks;
	}
	
	public int getID() {
		return ID;
	}

	public void publishResult(int mark) {
		this.endDateTime = new Date();
		this.marks = mark;
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

	public int getMarks() {
		return marks;
	}
}
