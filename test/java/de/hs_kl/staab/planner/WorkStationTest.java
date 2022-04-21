package de.hs_kl.staab.planner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.hs_kl.staab.planner.workstation.WorkStation;

public class WorkStationTest {

	WorkStation wStation1 = new WorkStation("Bühne 1");
	WorkStation wStation2 = new WorkStation("Bühne 2");
	WorkStation wStation3 = new WorkStation("Bühne 3");

	@Test
	public void getAllWorkStationTest() {
		assertEquals("Bühne 1", wStation1.getWorkStationName());
		assertEquals("Bühne 2", wStation2.getWorkStationName());
		assertEquals("Bühne 3", wStation3.getWorkStationName());

		wStation1.setWorkStationName("Bühne 1337");

		assertEquals("Bühne 1337", wStation1.getWorkStationName());

	}

}
