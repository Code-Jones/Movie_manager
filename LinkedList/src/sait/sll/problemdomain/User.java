package sait.sll.problemdomain;

import java.io.Serializable;

/**
 * Represents a user.
 * @author Matt Jones
 * @author Fernando Arenas
 * @version 2020-03-30
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String email;
	private transient String password;
	
	/**
	 * Initializes a User object.
	 * @param id ID
	 * @param name Name
	 * @param email E-mail address
	 * @param password Password
	 */
	public User(int id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public int getId() { return this.id; }
	

	public String getName() { return this.name;	}
	

	public String getEmail() { return this.email; }
	
	/**
	 * Checks if the passed password is correct.
	 * @param password Password to check.
	 * @return True if password is correct.
	 */
	public boolean isCorrectPassword(String password) {
		if (this.password == null && password == null)
			return true;
		else if (this.password == null || password == null)
			return false;
		else
			return this.password.equals(password);
	}
	
	/**
	 * Checks if object is equal to another object.
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof User))
			return false;
		
		User other = (User) obj;
		
		return this.id == other.id && this.name.equals(other.name) && this.email.equals(other.email);
	}
}
