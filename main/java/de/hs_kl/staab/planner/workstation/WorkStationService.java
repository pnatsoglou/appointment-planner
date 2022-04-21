package de.hs_kl.staab.planner.workstation;

import java.util.ArrayList;
import java.util.List;

public class WorkStationService implements WorkStationCRUD {

	private List<WorkStation> workStationList = new ArrayList<>();

	@Override
	public void createNewWorkStation(WorkStation workStation) {
		workStationList.add(workStation); // fügt neue Arbeitsbühnen in der Liste hinzu
	}

	@Override
	public void updateWorkStation(int ID, String workStationName) { // updated die Arbeitsbühne über Streams
		workStationList.stream().filter(workStation -> workStation.getNumberWorkStation() == ID)
				.forEach(workStation -> workStation.setWorkStationName(workStationName));
	}

	@Override
	public WorkStation getWorkStationByID(int workStationID) {
		if (workStationID <= 0) {
			throw new IndexOutOfBoundsException(
					"Es gibt keine Arbeitsbühnen-ID die mit 0 beginnt. Bitte geben Sie eine Zahl > 0 ein.");
		}
		if (workStationID > workStationList.size()) {
			throw new IndexOutOfBoundsException("Es gibt keine Arbeitsbühnen-ID mit dieser Nummer. Es gibt insgesamt "
					+ workStationList.size() + " Arbeitsbühnen.");
		}
		return workStationList.get(workStationID - 1); // gibt eine ID von einer Liste aus. Fängt nicht bei der 0ten ID
														// an, sondern ab der 1ten ID.
	}

	@Override
	public List<WorkStation> getWorkStation() {
		if (workStationList.isEmpty()) {
			System.err.println("Die Liste für WorkStation ist leer.");
		}
		return workStationList;
	}

}
