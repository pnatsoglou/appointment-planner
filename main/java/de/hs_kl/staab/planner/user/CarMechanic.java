package de.hs_kl.staab.planner.user;

public class CarMechanic extends User {

	private static int ID_CARMECHANIC = 1;
	private final int numberOfCarMechanic;

	public CarMechanic(String username, String firstName, String lastName) {
		super(username, firstName, lastName);
		numberOfCarMechanic = ID_CARMECHANIC++;
	}

	public String toString() {
		return "(Automechaniker): numberOfCarMechanic: " + this.numberOfCarMechanic + ", " + super.toString();
	}
}