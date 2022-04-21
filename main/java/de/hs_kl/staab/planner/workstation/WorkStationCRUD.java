package de.hs_kl.staab.planner.workstation;

import java.util.List;

public interface WorkStationCRUD {

	void createNewWorkStation(WorkStation workStation);

	void updateWorkStation(int ID, String workStationName);

	WorkStation getWorkStationByID(int workStationID);

	List<WorkStation> getWorkStation();

}
