package de.hs_kl.staab.planner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.hs_kl.staab.planner.work.Work;
import de.hs_kl.staab.planner.work.WorkCRUD;
import de.hs_kl.staab.planner.work.WorkService;

public class WorkServiceTest {

	WorkCRUD wService = new WorkService();

	@Test
	public void getTestOfUpdateMethodByWorkService() {
		wService.createNewWork(new Work("Ölstand prüfen", 5));
		assertEquals("Ölstand prüfen", wService.getWorkByID(1).getName());
		assertEquals(5, wService.getWorkByID(1).getDuration());
	}

}
