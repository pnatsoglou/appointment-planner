package de.hs_kl.staab.planner.work;

import java.util.ArrayList;
import java.util.List;

public class WorkService implements WorkCRUD {

	private List<Work> workList = new ArrayList<>();

	@Override
	public void createNewWork(Work work) {
		workList.add(work); // fügt ein Work-Objekt in der Liste hinzu
	}

	@Override
	public void updateWork(int ID, String workName, int duration) {

		// Updated die Arbeit über Streams
		workList.stream().filter(work -> work.getNumberWork() == ID).forEach(work -> {
			work.setName(workName);
			work.setDuration(duration);
		});

	}

	@Override
	public Work getWorkByID(int workID) {
		if (workID <= 0) {
			throw new IndexOutOfBoundsException(
					"Es gibt keine Arbeit-ID die mit 0 beginnt. Bitte geben Sie eine Zahl > 0 ein.");
		}
		if (workID > workList.size()) {
			throw new IndexOutOfBoundsException(
					"Es gibt keine Arbeit-ID mit dieser Nummer. Es gibt insgesamt " + workList.size() + " Arbeiten.");
		}
		return workList.get(workID - 1); // gibt eine ID von einer Liste aus. Fängt nicht bei der 0ten ID an, sondern ab
											// der 1ten ID.
	}

	@Override
	public List<Work> getWork() {
		if (workList.isEmpty()) {
			System.err.println("Die Liste für Work ist leer.");
		}
		return workList; // gibt die Liste aus
	}

}
