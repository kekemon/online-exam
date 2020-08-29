package du.iit.examsystem;

import javax.persistence.*;

@Entity
public class Student extends User {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int userID;
	
	public Student() {}
	
	public Student(String fullName, String email, String password, String role) {
		super(fullName, email, password, role);
		// TODO Auto-generated constructor stub
	}

}
