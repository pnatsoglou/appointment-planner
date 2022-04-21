package de.hs_kl.staab.planner.work;

import java.util.List;

public interface WorkCRUD {

	void createNewWork(Work work);

	void updateWork(int ID, String workName, int duration);

	Work getWorkByID(int workID);

	List<Work> getWork();
}