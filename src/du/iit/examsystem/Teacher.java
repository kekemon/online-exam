package du.iit.examsystem;

import javax.persistence.*;

@Entity
public class Teacher extends User {

	public Teacher() {}
	
	public Teacher(String fullName, String email, String password) {
		super(fullName, email, password);
		// TODO Auto-generated constructor stub
	}

}
