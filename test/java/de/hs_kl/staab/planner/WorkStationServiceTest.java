package de.hs_kl.staab.planner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.hs_kl.staab.planner.workstation.WorkStation;
import de.hs_kl.staab.planner.workstation.WorkStationCRUD;
import de.hs_kl.staab.planner.workstation.WorkStationService;

public class WorkStationServiceTest {

	WorkStationCRUD workStationService = new WorkStationService();

	@Test
	public void getTestOfUpdateMethodByWorkStationService() {
		workStationService.createNewWorkStation(new WorkStation("Bühne 1"));
		assertEquals("Bühne 1", workStationService.getWorkStationByID(1).getWorkStationName());

	}

}
