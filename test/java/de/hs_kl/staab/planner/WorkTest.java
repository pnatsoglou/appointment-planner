package de.hs_kl.staab.planner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.hs_kl.staab.planner.work.Work;

public class WorkTest {

	Work work1 = new Work("Bremsen wechseln", 30);
	Work work2 = new Work("Bremsscheiben wechseln", 15);
	Work work3 = new Work("Motoröl überprüfen", 5);
	Work work4 = new Work("Kraftstoffpumpe reparieren", 120);

	@Test
	public void getAllWorkTest() {
		assertEquals("Bremsen wechseln", work1.getName());
		assertEquals("Bremsscheiben wechseln", work2.getName());
		assertEquals("Motoröl überprüfen", work3.getName());
		assertEquals("Kraftstoffpumpe reparieren", work4.getName());

		assertEquals(30, work1.getDuration());
		assertEquals(15, work2.getDuration());
		assertEquals(5, work3.getDuration());
		assertEquals(120, work4.getDuration());
	}

}
