package du.iit.examsystem;

import javax.persistence.*;

@Entity
public abstract class User {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int ID;
	
	@Column
	private String fullName;
	private String email;
	private String password;
	
	public User() {}
	
	public User(String fullName, String email, String password) {
		this.fullName = fullName;
		this.email = email;
		this.password = password;
	}

	public int getID() {
		return ID;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean check(String email, String password) {
		return this.email.equals(email) && this.password.equals(password);
	}
	
}
