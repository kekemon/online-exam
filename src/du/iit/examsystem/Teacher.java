package du.iit.examsystem;

import javax.persistence.*;

@Entity
public class Teacher extends User {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int userID;

	public Teacher() {}
	
	public Teacher(String fullName, String email, String password, String role) {
		super(fullName, email, password, role);
		// TODO Auto-generated constructor stub
	}

}
