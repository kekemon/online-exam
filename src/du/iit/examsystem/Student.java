package du.iit.examsystem;

import javax.persistence.*;

@Entity
public class Student extends User {

	public Student() {}
	
	public Student(String fullName, String email, String password) {
		super(fullName, email, password);
		// TODO Auto-generated constructor stub
	}

}
