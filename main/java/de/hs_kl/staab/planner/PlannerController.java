package de.hs_kl.staab.planner;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Klasse, die das REST-Interface für den Werkstattplaner zur Verfügung stellt
 * (optional!).
 * 
 * @author Staab
 *
 */
@RestController
@RequestMapping(path = "/planner")
public class PlannerController {

	private final PlanningCalendar planningCalendar = PlanningCalendar.getInstance();

	@RequestMapping(value = "/appointments", method = RequestMethod.GET)
	List<Appointment> getAppointments() {
		return planningCalendar.getAppointments();
	}

}
