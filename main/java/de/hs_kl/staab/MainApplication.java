package de.hs_kl.staab;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.hs_kl.staab.planner.Appointment;
import de.hs_kl.staab.planner.CleaningAppointment;
import de.hs_kl.staab.planner.CleaningTypes;
import de.hs_kl.staab.planner.ConsultingAppointment;
import de.hs_kl.staab.planner.PlanningCalendar;
import de.hs_kl.staab.planner.WorkAppointment;
import de.hs_kl.staab.planner.customer.Customer;
import de.hs_kl.staab.planner.customer.CustomerCRUD;
import de.hs_kl.staab.planner.customer.CustomerService;
import de.hs_kl.staab.planner.user.CarMechanic;
import de.hs_kl.staab.planner.user.Consultant;
import de.hs_kl.staab.planner.user.Dispatcher;
import de.hs_kl.staab.planner.user.UserCRUD;
import de.hs_kl.staab.planner.user.UserService;
import de.hs_kl.staab.planner.vehicles.Vehicles;
import de.hs_kl.staab.planner.vehicles.VehiclesCRUD;
import de.hs_kl.staab.planner.vehicles.VehiclesService;
import de.hs_kl.staab.planner.work.Work;
import de.hs_kl.staab.planner.work.WorkCRUD;
import de.hs_kl.staab.planner.work.WorkService;
import de.hs_kl.staab.planner.workstation.WorkStation;
import de.hs_kl.staab.planner.workstation.WorkStationCRUD;
import de.hs_kl.staab.planner.workstation.WorkStationService;

// Kleiner test ob wir in den Master pushen können
/**
 * <p>
 * This class contains the main function and is called upon start. It starts
 * Spring Boot, which starts an application server and runs your application in
 * this application server so that it can be reached in the Network.
 * </p>
 * 
 * <p>
 * If you now enter the following URL in the browser, you can access the
 * Swagger-Interface of the application: <a href=
 * "http://localhost:8080/swagger-ui/">http://localhost:8080/swagger-ui/</a>.
 * </p>
 *
 * @author Staab
 */
@SpringBootApplication

public class MainApplication {

	public static void main(String[] args) {

		System.out.println("Now the application is started!");
		// Wenn Sie OHNE REST arbeiten wollen, können Sie die folgende Zeile
		// und die Klasse PlannerController löschen!
		SpringApplication.run(MainApplication.class, args);

		// Wenn Sie MIT REST arbeiten wollen, können Sie die folgende Zeile
		// und die entsprechende Methode löschen!
		runApplication();

		// Appointment Service

		PlanningCalendar planningCalendar = PlanningCalendar.getInstance();

		// Arbeit

		WorkCRUD serviceWork = new WorkService();
		serviceWork.createNewWork(new Work("Motor reparieren", 60));
		serviceWork.createNewWork(new Work("Krafstoffpumpe ausbauen", 120));
		serviceWork.createNewWork(new Work("Zündkerzen wechseln", 120));
		// Gebe die Liste aus von der Arbeit
		System.out.println(serviceWork.getWork());
		// Gebe die Arbeit an der zweiten ID aus (Der Index beginnt bei 1, nicht bei 0)
		System.out.println(serviceWork.getWorkByID(2));
		// Update die Arbeit an der zweiten ID
		serviceWork.updateWork(2, "Kraftstoffpumpe einbauen", 20);
		// Gebe die Liste nach dem Update aus
		System.out.println(serviceWork.getWork());

		// Arbeitsbühne
		WorkStationCRUD serviceWorkStation = new WorkStationService();
		// erstelle 3 Objekten und füge sie in der Liste hinzu
		serviceWorkStation.createNewWorkStation(new WorkStation("Bühne 1"));
		serviceWorkStation.createNewWorkStation(new WorkStation("Bühne 3"));
		serviceWorkStation.createNewWorkStation(new WorkStation("Bühne 5"));
		// Gebe mir die gesamte Liste aus
		System.out.println(serviceWorkStation.getWorkStation());
		// Gebe mir die dritte ID aus
		System.out.println(serviceWorkStation.getWorkStationByID(3));
		// Update
		serviceWorkStation.updateWorkStation(3, "Bühne 1");
		System.out.println(serviceWorkStation.getWorkStationByID(3));

		// Fahrzeuge
		VehiclesCRUD vehiclesService = new VehiclesService();
		vehiclesService.createNewVehicles(new Vehicles("KL-PN22", "BMW", "318i", LocalDateTime.of(2002, 3, 14, 0, 0),
				LocalDateTime.of(2004, 2, 16, 0, 0)));

		vehiclesService.createNewVehicles(new Vehicles("KL-QQ717", "Audi", "Q7", LocalDateTime.of(2012, 6, 8, 0, 0),
				LocalDateTime.of(2013, 3, 5, 0, 0)));

		vehiclesService.createNewVehicles(new Vehicles("KL-PA419", "Mercedes", "E250d",
				LocalDateTime.of(2008, 9, 12, 0, 0), LocalDateTime.of(2009, 7, 15, 0, 0)));

		System.out.println(vehiclesService.getVehicles());

		System.out.println(vehiclesService.getVehiclesByID(2));

		vehiclesService.updateVehicles(2, "KL-NP099");

		System.out.println(vehiclesService.getVehiclesByID(2));

		// Kunden mit Fahrzeuge verwalten

		CustomerCRUD serviceCustomer = new CustomerService();

		serviceCustomer.createNewCustomer(new Customer("Panagiotis Natsoglou", "Schulstraße 14, 66849 Landstuhl",
				"pnat@tutanota.com", vehiclesService.getVehiclesByID(1)));
		serviceCustomer.createNewCustomer(new Customer("Caner Ogan", "Petersburgstraße 4, 67665 Kaiserslautern",
				"caner@hotmail.com", vehiclesService.getVehiclesByID(1)));
		serviceCustomer.createNewCustomer(new Customer("Neshaanth Poopalasingham",
				"Sickingerstraße 9, 67665 Kaiserslautern", "n@hotmail.com", vehiclesService.getVehiclesByID(1)));

		System.out.println(serviceCustomer.getCustomers());
		serviceCustomer.updateCustomer(2, "Caner Dogan", "Virginiastraße 2, 67665 Kaiserslautern", "caner@tutanota.de");
		System.out.println(serviceCustomer.getCustomers());

		// MVP060 Disponent, Mechaniker, Berater

		UserCRUD userService = new UserService();
		userService.createNewUser(new Dispatcher("pana", "Panagiotis", "Natsoglou"));
		userService.createNewUser(new Consultant("oeay", "Ömer", "Ay"));
		userService.createNewUser(new CarMechanic("caog", "Caner", "Ogan"));

		System.out.println(userService.getUsers());

		System.out.println(userService.getCarMechanics());
		System.out.println(userService.getDispatchers());
		System.out.println(userService.getConsultants());

		System.out.println(userService.getCarMechanicByID(1));
		System.out.println(userService.getDispatcherByID(1));
		System.out.println(userService.getConsultantByID(1));

		// Test CleaningAppointment "FAST"
		Appointment cleaningAppFast = new CleaningAppointment(LocalDateTime.of(2022, 3, 14, 15, 30), "Luxemburg",
				serviceWorkStation.getWorkStationByID(1), CleaningTypes.FAST, (userService.getDispatcherByID(1)));

		// userService.getUserByID(1)

		planningCalendar.createNewAppointment(cleaningAppFast);

		// Test MVP050 WorkAppointment
		LocalDateTime appointment = LocalDateTime.of(2022, 2, 16, 12, 00);
		Appointment wAppointment = new WorkAppointment(appointment, "Hamburg", serviceWork.getWorkByID(1),
				serviceWorkStation.getWorkStationByID(1), vehiclesService.getVehiclesByID(1),
				userService.getCarMechanicByID(1));

		planningCalendar.createNewAppointment(wAppointment);

		LocalDateTime appointment2 = LocalDateTime.of(2022, 1, 6, 7, 00);
		Appointment wAppointment2 = new WorkAppointment(appointment2, "Luxemburg", serviceWork.getWorkByID(2),
				serviceWorkStation.getWorkStationByID(2), vehiclesService.getVehiclesByID(2),
				userService.getCarMechanicByID(1));

		planningCalendar.createNewAppointment(wAppointment2);

		// Test MVP050 ConsultingAppointment
		Appointment cAppointment = new ConsultingAppointment(appointment, "Dortmund",
				serviceCustomer.getCustomerByID(2), userService.getConsultantByID(1));

		planningCalendar.createNewAppointment(cAppointment);

		// Test MVP060

		System.out.println(planningCalendar.getAppointments());
		System.out.println(planningCalendar.getAppointmentByID(1).getCalendarWeekOfAppointment());
		System.out.println(planningCalendar.getAppointmentByID(2).getCalendarWeekOfAppointment());
		System.out.println(planningCalendar.getAppointmentByID(3).getCalendarWeekOfAppointment());

		planningCalendar.getWeeklyOverview(2023, 54); // Fehler produzieren
		planningCalendar.getWeeklyOverview(0, 5); // Fehler produzieren
		planningCalendar.getWeeklyOverview(2023, 1);

		planningCalendar.getAllWorkAppointmentsFromYesterday();

		Appointment wAppointment3 = new WorkAppointment(LocalDateTime.of(2022, 2, 16, 8, 00), "Luxemburg",
				serviceWork.getWorkByID(3), serviceWorkStation.getWorkStationByID(3),
				vehiclesService.getVehiclesByID(3), (CarMechanic) userService.getUserByID(3));

		((WorkAppointment) wAppointment3).setMarkAsCompleted(true);
		((WorkAppointment) wAppointment3).checkIfWorkAppointmentIsMarkedAsCompleted();
		planningCalendar.createNewAppointment(wAppointment3);

		planningCalendar.getAllWorkAppointmentsFromYesterday();

		planningCalendar.getHistoryOfDoneWorkForCertainVehicle(vehiclesService.getVehiclesByID(3));

		((WorkAppointment) wAppointment).setMarkAsCompleted(true); // Arbeitsbühne der ersten ID ist hier drin
		((WorkAppointment) wAppointment).checkIfWorkAppointmentIsMarkedAsCompleted();

		// Funktioniert nicht, da die Arbeit als "abgeschlossen" vermerkt wurde
		planningCalendar.getOverviewOfWorkForCertainWorkStation(serviceWorkStation.getWorkStationByID(1));

		planningCalendar.getOverviewOfWorkForCertainWorkStation(serviceWorkStation.getWorkStationByID(2));

		// MLP020 Test

		Work work = new Work("Scheibenwischer wechseln", 120);
		Vehicles vehicles = new Vehicles("KL-PN22", "BMW", "318i", LocalDateTime.of(2002, 3, 14, 0, 0),
				LocalDateTime.of(2004, 3, 14, 0, 0));
		Dispatcher dispatcher = new Dispatcher("dispatcher1", "Dispa", "Tcher");
		CarMechanic carMechanic = new CarMechanic("mechanic1", "Mecha", "Nic");

		WorkStation workStation = new WorkStation("Bühne 3");
		WorkStation workStation2 = new WorkStation("Bühne 2");

		Appointment wApp = new WorkAppointment(LocalDateTime.of(2022, 2, 17, 8, 00), "Hamburg", work, workStation2,
				vehicles, carMechanic);
		Appointment wApp2 = new WorkAppointment(LocalDateTime.of(2022, 2, 16, 14, 00), "Frankfurt", work, workStation,
				vehicles, carMechanic);
		Appointment clApp = new CleaningAppointment(LocalDateTime.of(2024, 6, 9, 12, 00), "Luwdigshafen", workStation2,
				CleaningTypes.FAST, dispatcher);
		Appointment clApp2 = new CleaningAppointment(LocalDateTime.of(2022, 3, 9, 12, 50), "Luxemburg", workStation,
				CleaningTypes.INTENSE, dispatcher);

		System.out.println(planningCalendar.doesCollide(clApp, clApp2));
		System.out.println(planningCalendar.doesCollide(wApp, clApp));
		System.out.println(planningCalendar.doesCollide(wApp, wApp2));
		System.out.println(planningCalendar.doesCollide(clApp2, wApp2));

		planningCalendar.createNewAppointment(wApp);
		planningCalendar.createNewAppointment(wApp2);
		planningCalendar.createNewAppointment(clApp);
		planningCalendar.createNewAppointment(clApp2);
		// Ist ein Sonntag - Überprüfung für MLP040 obs hinzugefügt wird.
		planningCalendar.createNewAppointment(new WorkAppointment(LocalDateTime.of(2022, 1, 2, 13, 00), "Hamburg", work,
				workStation2, vehicles, carMechanic));

		// => Termin-ID 11.. kollidiert mit Termin-ID 9.Termin muss geändert werden.
		planningCalendar.createNewAppointment(new CleaningAppointment(LocalDateTime.of(2022, 3, 9, 12, 50), "Luxemburg",
				workStation, CleaningTypes.INTENSE, dispatcher));

		System.out.println(planningCalendar.getAppointments());

		// korrigiert den Kollidierer
		planningCalendar.createAutomaticallyNewCleaningAppointment(new CleaningAppointment(
				LocalDateTime.of(2022, 3, 9, 12, 50), "Luxemburg", workStation, CleaningTypes.FAST, dispatcher));

		// korrigiert den Kollidierer
		planningCalendar.createAutomaticallyNewCleaningAppointment(new CleaningAppointment(
				LocalDateTime.of(2024, 6, 9, 11, 50), "Luxemburg", workStation2, CleaningTypes.FAST, dispatcher));

		// fügt einen Termin ein, da er nicht kollidiert
		planningCalendar.createAutomaticallyNewCleaningAppointment(new CleaningAppointment(
				LocalDateTime.of(2028, 6, 9, 11, 50), "Hannover", workStation2, CleaningTypes.INTENSE, dispatcher));

		System.out.println(planningCalendar.getAppointments());

		planningCalendar.getNextThreePossibleWorkAppointments();

	}

	private static void runApplication() {
		// Hier können Sie Ihren PlannerService aufrufen (nur wenn Sie ohne Rest
		// arbeiten!)
		System.out.println("Holla Push");
	}
}