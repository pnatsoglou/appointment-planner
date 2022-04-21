package de.hs_kl.staab.planner;

import java.time.LocalDateTime;
import java.time.temporal.IsoFields;

// MVP050
public abstract class Appointment { // Super-Klasse abstrakt gemacht, da wir nur die Subklassen verwenden werden
	private static int ID_APPOINTMENT = 1;

	protected LocalDateTime dateOfAppointment;
	protected final String locationOfAppointment;
	protected final int numberAppointment;

	protected final int calendarWeekOfAppointment;
	protected LocalDateTime endOfAppointment;

	public Appointment(LocalDateTime dateOfAppointment, String locationOfAppointment) {
		this.numberAppointment = ID_APPOINTMENT++;
		this.dateOfAppointment = dateOfAppointment;
		this.locationOfAppointment = locationOfAppointment;

		this.calendarWeekOfAppointment = dateOfAppointment.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR); // Gibt die
																									// Kalenderwoche des
																									// Termins aus

	}

	@Override
	public String toString() {
		return "numberAppointment: " + this.numberAppointment + ", " + "dateOfAppointment: "
				+ this.dateOfAppointment.toLocalDate() + ", " + "timeOfDateAppointment: "
				+ this.dateOfAppointment.toLocalTime() + ", " + "endOfAppointment: "
				+ this.endOfAppointment.toLocalTime() + ", " + "locationOfAppointment: " + this.locationOfAppointment
				+ ", ";
	}

	public LocalDateTime getDateOfAppointment() {
		return dateOfAppointment;
	}

	public void setDateOfAppointment(LocalDateTime dateOfAppointment) {
		this.dateOfAppointment = dateOfAppointment;
	}

	public String getLocationOfAppointment() {
		return locationOfAppointment;
	}

	public int getNumberAppointment() {
		return numberAppointment;
	}

	public int getCalendarWeekOfAppointment() {
		return calendarWeekOfAppointment;
	}

	public LocalDateTime getEndOfAppointment() {
		return endOfAppointment;
	}

	public void setEndOfAppointment(LocalDateTime endOfAppointment) {
		this.endOfAppointment = endOfAppointment;
	}

}
