package de.hs_kl.staab.planner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import de.hs_kl.staab.planner.customer.Customer;
import de.hs_kl.staab.planner.user.CarMechanic;
import de.hs_kl.staab.planner.user.Consultant;
import de.hs_kl.staab.planner.user.Dispatcher;
import de.hs_kl.staab.planner.vehicles.Vehicles;
import de.hs_kl.staab.planner.work.Work;
import de.hs_kl.staab.planner.workstation.WorkStation;

public class PlanningCalendarTest {

	PlanningCalendar pCal = new PlanningCalendar();

	LocalDateTime wAppointment = LocalDateTime.of(2022, 3, 7, 12, 40);
	LocalDateTime cAppointment = LocalDateTime.of(2022, 5, 9, 15, 40);
	LocalDateTime clAppointment = LocalDateTime.of(2022, 5, 25, 16, 35);

	Work work = new Work("Motor reparieren", 180);
	WorkStation workStation = new WorkStation("Bühne 1");
	Vehicles vehicles = new Vehicles("KL-PN22", "BMW", "318i", LocalDateTime.of(2002, 3, 14, 0, 0),
			LocalDateTime.of(2004, 3, 14, 0, 0));
	CarMechanic carMechanic = new CarMechanic("mechanic1", "Mecha", "Nic");
	Consultant consultant = new Consultant("consultant1", "Consu", "Ltant");
	Dispatcher dispatcher = new Dispatcher("dispatcher1", "Dispa", "Tcher");
	Customer customer = new Customer("Panagiotis Natsoglou", "Schulstraße 14, 66849 Landstuhl",
			"pannat220299@gmail.com", vehicles);

	Appointment wApp = new WorkAppointment(wAppointment, "Hamburg", work, workStation, vehicles, carMechanic);
	Appointment cApp = new ConsultingAppointment(cAppointment, "Saarbrücken", customer, consultant);
	Appointment clApp = new CleaningAppointment(clAppointment, "Luxemburg", workStation, CleaningTypes.FAST,
			dispatcher);

	@Test
	public void getAllPlanningCalendarTest() {
		pCal.createNewAppointment(wApp);
		pCal.createNewAppointment(cApp);
		pCal.createNewAppointment(clApp);
		// Testen, ob return ID-1 klappt (d. h. wir fangen ab 1 an zu zählen, statt ab
		// 0)
		assertEquals(1, pCal.getAppointmentByID(1).getNumberAppointment());
		assertEquals(2, pCal.getAppointmentByID(2).getNumberAppointment());
		assertEquals(3, pCal.getAppointmentByID(3).getNumberAppointment());
		// Ort des Termins testen
		assertEquals("Hamburg", pCal.getAppointmentByID(1).getLocationOfAppointment());
		assertEquals("Saarbrücken", pCal.getAppointmentByID(2).getLocationOfAppointment());
		assertEquals("Luxemburg", pCal.getAppointmentByID(3).getLocationOfAppointment());

	}

}
