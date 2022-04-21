package de.hs_kl.staab.planner.user;

public abstract class User {

	protected static int ID_USER = 1;
	protected String username;
	protected String firstName;
	protected String lastName;
	protected final int numberUserID;

	public User(String username, String firstName, String lastName) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.numberUserID = ID_USER++;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastname() {
		return lastName;
	}

	public void setLastname(String lastName) {
		this.lastName = lastName;
	}

	public int getNumberUserID() {
		return numberUserID;
	}

	public String toString() {
		return "username: " + this.username + ", " + "firstName: " + this.firstName + ", " + "lastName: "
				+ this.lastName + ", ";
	}
}
