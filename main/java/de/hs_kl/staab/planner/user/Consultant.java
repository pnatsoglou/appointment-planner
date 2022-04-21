package de.hs_kl.staab.planner.user;

public class Consultant extends User {

	private static int ID_CONSULTANT = 1;
	private final int numberOfConsultant;

	public Consultant(String username, String firstName, String lastName) {
		super(username, firstName, lastName);
		numberOfConsultant = ID_CONSULTANT++;
	}

	public String toString() {
		return "(Kundenberater): numberOfConsultant: " + this.numberOfConsultant + ", " + super.toString();
	}
}
