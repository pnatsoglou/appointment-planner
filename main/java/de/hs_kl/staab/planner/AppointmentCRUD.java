package de.hs_kl.staab.planner;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentCRUD {

	void createNewAppointment(Appointment newAppointment);

	void updateAppointment(LocalDateTime dateOfAppointment);

	Appointment getAppointmentByID(int appointmentID);

	List<Appointment> getAppointments();

}
