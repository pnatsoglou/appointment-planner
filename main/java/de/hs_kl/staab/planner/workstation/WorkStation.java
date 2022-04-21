package de.hs_kl.staab.planner.workstation;

//MVP020
public class WorkStation {

	private static int ID_WORKSTATION = 1;

	private String workStationName; // Name der Bühne
	private final int numberWorkStation; // ID der jeweiligen Instanz ausgeben

	public WorkStation(String name) {
		this.numberWorkStation = ID_WORKSTATION++;
		this.workStationName = name;
	}

	@Override
	// Ausgeben
	public String toString() {
		return "(Arbeitsbühne) numberWorkStation: " + this.numberWorkStation + ", " + "workStationName: "
				+ this.workStationName;
	}

	// Getter und Setter

	public String getWorkStationName() {
		return workStationName;
	}

	public void setWorkStationName(String workStationName) {
		this.workStationName = workStationName;
	}

	public int getNumberWorkStation() {
		return numberWorkStation;
	}

}
