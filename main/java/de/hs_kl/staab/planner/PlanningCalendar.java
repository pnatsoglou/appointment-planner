package de.hs_kl.staab.planner;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hs_kl.staab.planner.vehicles.Vehicles;
import de.hs_kl.staab.planner.workstation.WorkStation;

public class PlanningCalendar implements AppointmentCRUD {

	private static PlanningCalendar INSTANCE;
	private boolean doesCollide;

	public static PlanningCalendar getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PlanningCalendar();
		}
		return INSTANCE;
	}

	private List<Appointment> appointmentList = new ArrayList<>();

	@Override
	public void createNewAppointment(Appointment newAppointment) {
		for (Appointment currentAppointment : appointmentList) {
			if (doesCollide(newAppointment, currentAppointment)) {
				System.err.println(
						"Der Termin" + newAppointment + "\nkann nicht hinzugefügt werden, da er mit der Termin-ID: "
								+ currentAppointment.getNumberAppointment()
								+ " kollidiert. Bitte ändere den Termin oder benutze eine andere Arbeitsbühne.");
				// bricht die Funktion ab wenn der neue Termin mit einen bereits vorhanden
				// Termin kollidiert
				return;
			}
		}
		appointmentList.add(newAppointment);
	}

	@Override
	public void updateAppointment(LocalDateTime dateOfAppointment) {
		for (Appointment appointment : appointmentList) {
			if (appointment.getDateOfAppointment().equals(dateOfAppointment)) {
				appointment.setDateOfAppointment(dateOfAppointment);
			}
		}
	}

	@Override
	public Appointment getAppointmentByID(int appointmentID) {
		return appointmentList.get(appointmentID - 1);
	}

	@Override
	public List<Appointment> getAppointments() {
		if (appointmentList.isEmpty()) {
			System.err.println("Es gibt keine Termine.");
		}
		return appointmentList;
	}

	// MVP060
	public void getWeeklyOverview(int year, int week) {
		List<Appointment> listOfFoundAppointments = new ArrayList<>();

		if (year > 0 && (week > 0 && week <= 52)) {
			for (Appointment appointment : appointmentList) {
				if (appointment.getCalendarWeekOfAppointment() == week
						&& appointment.getDateOfAppointment().getYear() == year) {
					listOfFoundAppointments.add(appointment);
				}
			}
		} else {
			System.err.println("Bitte gib ein gültiges Jahr bzw. eine gültige Kalenderwoche ein.");
			return;
		}

		if (listOfFoundAppointments.size() >= 1) {
			System.out.print(
					"Liste gefundener Termine für das Jahr: " + year + ", Woche: " + week + "\n####################");
			for (Appointment appointment : listOfFoundAppointments) {
				System.out.println(appointment);
			}
			System.out.println("####################");
		} else {
			System.err.println("Es wurden keine Termine gefunden zu der Kalenderwoche " + week + " des Jahres " + year
					+ " gefunden.");
		}

	}

	// MMP040

	public void getAllWorkAppointmentsFromYesterday() {
		List<WorkAppointment> listOfAllFoundAppointmentsFromYesterday = new ArrayList<>();
		LocalDate getDayOfYesterday = LocalDate.now().minusDays(1);
		for (Appointment appointment : appointmentList) {
			if (appointment instanceof WorkAppointment) {
				// Überprüfung, ob der Termin als erledigt markiert wurde mit der Methode
				// "isMarkAsCompleted()" sowie ob es ein Termin gibt mit dem gestrigen Datum.
				// Da wir mit LocalDateTime gearbeitet haben, müssen wir noch "toLocalDate()"
				// benutzen, um die Uhrzeit wegzulassen, damit wir es mit der Variable
				// "getDayOfYesterday" vergleichen können.
				if (((WorkAppointment) appointment).isMarkAsCompleted()
						&& appointment.getDateOfAppointment().toLocalDate().equals(getDayOfYesterday)) {
					listOfAllFoundAppointmentsFromYesterday.add((WorkAppointment) appointment);
				}
			}
		}
		if (listOfAllFoundAppointmentsFromYesterday.size() >= 1) {
			System.out.println("######################### \n");
			System.out.println("Alle Arbeitstermine des gestrigen Tages, die als markiert abgeschlossen wurden: \n");
			for (WorkAppointment workAppointment : listOfAllFoundAppointmentsFromYesterday) {
				System.out.println(workAppointment);
			}
			System.out.print("#########################");
		} else {
			System.err.println("Es wurden keine Arbeitstermine zu dem Tag " + getDayOfYesterday + " gefunden.");
		}
	}

	// MMP050
	public void getHistoryOfDoneWorkForCertainVehicle(Vehicles vehicles) {
		List<WorkAppointment> getHistoryListOfDoneWorkForCertainVehicle = new ArrayList<>();
		for (Appointment appointment : appointmentList) {
			if (appointment instanceof WorkAppointment) {
				if (vehicles.equals(((WorkAppointment) appointment).getVehicles())) {
					getHistoryListOfDoneWorkForCertainVehicle.add((WorkAppointment) appointment);
				}
			}
		}

		if (getHistoryListOfDoneWorkForCertainVehicle.size() >= 1)

		{
			for (WorkAppointment workAppointment : getHistoryListOfDoneWorkForCertainVehicle) {
				System.out.println("###########################");
				System.out.println("Reparaturhistorie für das Fahrzeug " + workAppointment.getVehicles().getBrandName()
						+ " " + workAppointment.getVehicles().getModelName() + " mit dem Kennzeichen "
						+ workAppointment.getVehicles().getLicensePlate() + ", sowie der Fahrzeug-ID: "
						+ workAppointment.getVehicles().getNumberVehicles());
				System.out.println(workAppointment.getWork());
				System.out.println("###########################");
			}
		} else {
			System.err.println("Es gibt keine Arbeiten zu dem Fahrzeug, wonach du gesucht hast.");
		}
	};

	// MLP010

	public void getOverviewOfWorkForCertainWorkStation(WorkStation workStation) {
		List<WorkAppointment> getOverviewListOfWorkForCertainWorkStation = new ArrayList<>();
		LocalDate getTimeOfToday = LocalDate.now();
		for (Appointment appointment : appointmentList) {
			if (appointment instanceof WorkAppointment) {
				// Überprüfen ob die Arbeit noch nicht abgeschlossen wurde und ob die Arbeit für
				// heute ansteht
				if (workStation.equals(((WorkAppointment) appointment).getWorkStation())
						&& ((WorkAppointment) appointment).isMarkAsCompleted() == false
						&& appointment.getDateOfAppointment().toLocalDate().equals(getTimeOfToday)) {
					getOverviewListOfWorkForCertainWorkStation.add((WorkAppointment) appointment);
				}
			}
		}

		if (getOverviewListOfWorkForCertainWorkStation.size() >= 1) {
			for (WorkAppointment workAppointment : getOverviewListOfWorkForCertainWorkStation) {
				System.out.println("###########################");
				System.out.println("Auf der " + workAppointment.getWorkStation().getWorkStationName() + " mit der ID: "
						+ workAppointment.getWorkStation().getNumberWorkStation()
						+ " müssen noch folgende Arbeiten durchgeführt werden: \n" + workAppointment.getWork());
				System.out.println("###########################");

			}
		} else {
			System.err.println(
					"Auf dieser Bühne sind heute keine Arbeiten vorhanden oder die Arbeiten für heute wurden bereits abgeschlossen.");
		}
	}

	// MLP020

	public boolean doesCollide(Appointment firstAppointment, Appointment secondAppointment) {

		if (firstAppointment instanceof Collidable && secondAppointment instanceof Collidable) {

			// casten von Appointment zu Collidable, damit man Zugriff auf getWorkStation
			// bekommt
			Collidable firstAppointmentCollidable = (Collidable) firstAppointment;
			Collidable secondAppointmentCollidable = (Collidable) secondAppointment;

			if (firstAppointmentCollidable.getDateOfAppointment()
					.equals(secondAppointmentCollidable.getDateOfAppointment())
					&& firstAppointmentCollidable.getWorkStation().getWorkStationName()
							.equals(secondAppointmentCollidable.getWorkStation().getWorkStationName())) {
				this.doesCollide = true;
				return this.doesCollide;
			}
		}
		this.doesCollide = false;
		return this.doesCollide;
	}

	// MLP030 - Man erstellt sich selbst einen Reinigungstermin. Wenn er kollidiert,
	// erstellt er den nächstmöglichen Termin. Wenn er nicht kollidiert, dann wird
	// dieser entsprechende Termin genommen. Ebenso kann man eine andere Bühne
	// wählen, wenn ein Termin kollidiert.

	public void createAutomaticallyNewCleaningAppointment(CleaningAppointment newCleaningAppointment) {
		List<CleaningAppointment> getListOfAutomaticallyCleaningAppointment = new ArrayList<>();

		for (Appointment appointment : appointmentList) {
			if (appointment instanceof CleaningAppointment) {
				if ((appointment.getDateOfAppointment().equals(newCleaningAppointment.getDateOfAppointment())
						|| appointment.getEndOfAppointment().equals(newCleaningAppointment.getEndOfAppointment())
						|| appointment.getDateOfAppointment().equals(newCleaningAppointment.getEndOfAppointment())
						|| appointment.getEndOfAppointment().equals(newCleaningAppointment.getDateOfAppointment()))
						&& ((CleaningAppointment) appointment).getWorkStation().getWorkStationName()
								.equals(newCleaningAppointment.getWorkStation().getWorkStationName())) {
					System.err.println("KOLLIDIERT mit der Termin-ID: " + appointment.getNumberAppointment()
							+ ". Terminzeitraum wurde geändert. Termin-ID ist: "
							+ newCleaningAppointment.getNumberAppointment() + ".");

					newCleaningAppointment.setDateOfAppointment(appointment.getEndOfAppointment().plusMinutes(60));

					newCleaningAppointment.setEndOfAppointment(newCleaningAppointment.getDateOfAppointment()
							.plusMinutes((long) newCleaningAppointment.getCleaningTime()));

					getListOfAutomaticallyCleaningAppointment.add(newCleaningAppointment);
					break;

				} else if (!((appointment.getDateOfAppointment().equals(newCleaningAppointment.getDateOfAppointment())
						|| appointment.getEndOfAppointment().equals(newCleaningAppointment.getEndOfAppointment()))
						|| appointment.getDateOfAppointment().equals(newCleaningAppointment.getEndOfAppointment())
						|| appointment.getEndOfAppointment().equals(newCleaningAppointment.getDateOfAppointment()))
						&& ((CleaningAppointment) appointment).getWorkStation().getWorkStationName()
								.equals(newCleaningAppointment.getWorkStation().getWorkStationName())) {
					System.out.println("Der Termin kollidiert mit keinen Reinigungstermin. Die Termin-ID "
							+ newCleaningAppointment.getNumberAppointment() + " wurde hinzugefügt.");
					getListOfAutomaticallyCleaningAppointment.add(newCleaningAppointment);
					break;
				}
			}
		}
		if (getListOfAutomaticallyCleaningAppointment.size() >= 1) {
			for (CleaningAppointment cleaningAppointment : getListOfAutomaticallyCleaningAppointment) {
				appointmentList.add(cleaningAppointment);
			}
		}
	}
	// MLP040

	public void getNextThreePossibleWorkAppointments() {
		List<WorkAppointment> listOfWorkAppointments = new ArrayList<>();

		final LocalTime startTimeOfDay = LocalTime.of(8, 00);
		final LocalTime endTimeOfDay = LocalTime.of(16, 00);

		for (Appointment currentAppointment : appointmentList) {
			if (currentAppointment instanceof WorkAppointment) {
				listOfWorkAppointments.add((WorkAppointment) currentAppointment);
			}
		}

		// Termin-Liste nach Datum sortieren
		Collections.sort(listOfWorkAppointments,
				(a, b) -> a.getDateOfAppointment().compareTo(b.getDateOfAppointment()));

		List<WorkAppointment> listOfFilteredWorkAppointments = new ArrayList<>();

		System.out.println("\nSortierte Liste nach Datum mit den bereits bestehendenen Terminen:");
		for (WorkAppointment workAppointment : listOfWorkAppointments) {

			LocalDate getCurrentDateOfAppointment = workAppointment.getDateOfAppointment().toLocalDate();

			DayOfWeek getDays = DayOfWeek.of(getCurrentDateOfAppointment.get(ChronoField.DAY_OF_WEEK));

			if (getDays != DayOfWeek.SUNDAY) {

				// Überprüft, ob der Termin zwischen 8:00 Uhr und 16:00 Uhr liegt
				if ((startTimeOfDay.getHour() <= workAppointment.getDateOfAppointment().toLocalTime().getHour())
						&& (workAppointment.getDateOfAppointment().toLocalTime().getHour() < endTimeOfDay.getHour())) {

					// Überprüft ob endOfAppointment <= 16:00 Uhr ist
					if (workAppointment.getEndOfAppointment().toLocalTime().getHour() <= endTimeOfDay.getHour()) {

						// Überprüft ob es 16:00 Uhr ist
						if (workAppointment.getEndOfAppointment().toLocalTime().getHour() == endTimeOfDay.getHour()) {

							// Wenn es 16:00 Uhr ist, überprüft er die Minuten, ob es > :01 ist
							if (workAppointment.getEndOfAppointment().toLocalTime().getMinute() >= 1) {

								// Der Termin kann nicht länger als 16:00 Uhr gehen. Abbruch von der
								// for-Schleife.
								break;

							}
						}
					}
					// Speichere die gefilterten Termine, die zwischen 8:00 Uhr - 16:00 Uhr gehen,
					// in der Liste
					listOfFilteredWorkAppointments.add(workAppointment);
				}

			}

		}

		for (WorkAppointment workAppointment : listOfFilteredWorkAppointments) {
			System.out.println(workAppointment);
		}

		// Nun überprüfen wir, ob ein Termin besetzt ist, falls ja, geben wir die
		// nächsten verfügbaren Termine aus

		int amount = 3;
		for (WorkAppointment workAppointment : listOfFilteredWorkAppointments) {
			// Gibt mir nur die nächsten drei Termin-Vorschläge aus
			if (amount <= 0)
				break;
			amount--;
			int getWorkOfDurationInMin = workAppointment.getWork().getDuration();
			// Termin-Element mit dem Beginn des Termins
			LocalTime getStartTimeOfAppointment = workAppointment.getDateOfAppointment().toLocalTime();
			// Termin-Element mit dem Ende des Termins
			LocalTime getEndTimeOfAppointment = workAppointment.getDateOfAppointment().toLocalTime()
					.plusMinutes(getWorkOfDurationInMin);
			// Gibt mir die Differenz in Minuten von dem Beginn des Arbeitstages und dem
			// letzten verfügbaren Termin
			long getLastPossibleAppointment = Duration.between(startTimeOfDay, getEndTimeOfAppointment).toMinutes();

			if ((getStartTimeOfAppointment.isBefore(getEndTimeOfAppointment)
					&& getEndTimeOfAppointment.isAfter(getStartTimeOfAppointment))) {
				LocalTime availableStart = workAppointment.getDateOfAppointment().toLocalTime()
						.plusMinutes(getWorkOfDurationInMin);
				LocalTime availableEnd = workAppointment.getEndOfAppointment().toLocalTime()
						.plusMinutes(getWorkOfDurationInMin);

				if (availableStart.getHour() >= 8 && availableStart.getHour() <= getLastPossibleAppointment) {
					if (availableEnd.getHour() <= 16) {
						System.out.println("Termin-Vorschlag: " + workAppointment.getDateOfAppointment().toLocalDate()
								+ ", von " + availableStart + " Uhr bis " + availableEnd + " Uhr");
					} else {
						LocalTime availableNextDayStart = startTimeOfDay;
						LocalTime availableNextDayEnd = startTimeOfDay.plusMinutes(getWorkOfDurationInMin);
						System.out.println("Termin-Vorschlag: "
								+ workAppointment.getDateOfAppointment().toLocalDate().plusDays(1) + ", von "
								+ availableNextDayStart + " Uhr bis " + availableNextDayEnd + " Uhr");
					}
				}
			}
		}
	}
}
