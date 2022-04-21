package de.hs_kl.staab.planner.user;

public class Dispatcher extends User {

	private static int ID_DISPATCHER = 1;
	private final int numberOfDispatcher;

	public Dispatcher(String username, String firstName, String lastName) {
		super(username, firstName, lastName);
		numberOfDispatcher = ID_DISPATCHER++;
	}

	public String toString() {
		return "(Disponent): numberOfDispatcher: " + this.numberOfDispatcher + ", " + super.toString();
	}

}
