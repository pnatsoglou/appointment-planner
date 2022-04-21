package de.hs_kl.staab.planner.work;

// MVP010
public class Work {

	private static int ID_WORK = 1;

	private String workName; // z. B. die Reifen wechseln

	private int durationOfWork; // Dauer der Arbeit in Minuten.. Dauer kann sich verändern, deswegen nicht
	// final..
	private final int numberWork; // ID der jeweiligen Instanz ausgeben

	public Work(String workName, int durationOfWork) {
		this.numberWork = ID_WORK++;
		this.workName = workName;
		this.durationOfWork = durationOfWork;
	}

	// Getters & Setters

	public int getDuration() {
		return durationOfWork;
	}

	public void setDuration(int durationOfWork) { // Setter für Dauer, da sich die Dauer der Arbeit verändern kann
		this.durationOfWork = durationOfWork;
	}

	public void setName(String workName) {
		this.workName = workName;
	}

	public String getName() {
		return this.workName;
	}

	public int getNumberWork() {
		return numberWork;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numberWork;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Work other = (Work) obj;
		if (numberWork != other.numberWork)
			return false;
		return true;
	}

	@Override
	// Ausgeben
	public String toString() {
		return "(Arbeit) numberWork: " + this.numberWork + ", " + "workName: " + this.workName + ", "
				+ "durationOfWork: " + this.durationOfWork + " Minuten";
	}

}