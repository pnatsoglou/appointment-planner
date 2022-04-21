package de.hs_kl.staab.planner.user;

import java.util.ArrayList;
import java.util.List;

public class UserService implements UserCRUD {

	private List<User> userList = new ArrayList<>();
	private List<CarMechanic> carMechanicList = new ArrayList<>();
	private List<Dispatcher> dispatcherList = new ArrayList<>();
	private List<Consultant> consultantList = new ArrayList<>();

	@Override
	public void createNewUser(User user) {
		// Allgemeines hinzufügen aller Typen an User
		userList.add(user);
		// Bestimmte Typen separat in einer eigenen Liste adden (Filter)
		if (user instanceof CarMechanic) {
			carMechanicList.add((CarMechanic) user);
		}
		if (user instanceof Dispatcher) {
			dispatcherList.add((Dispatcher) user);
		}
		if (user instanceof Consultant) {
			consultantList.add((Consultant) user);
		}
	}

	@Override
	public void updateUser(int userID, String username, String firstName, String lastName) {
		// Updated die User über Streams
		userList.stream().filter(user -> user.getNumberUserID() == userID).forEach(user -> {
			user.setUsername(username);
			user.setFirstName(firstName);
			user.setLastname(lastName);
		});
	}

	@Override
	public User getUserByID(int userID) {
		if (userID <= 0) {
			throw new IndexOutOfBoundsException(
					"Es gibt keine Benutzer-ID die mit 0 beginnt. Bitte geben Sie eine Zahl > 0 ein.");
		}
		if (userID > userList.size()) {
			throw new IndexOutOfBoundsException(
					"Es gibt keine Benutzer-ID mit dieser Nummer. Es gibt insgesamt " + userList.size() + " Benutzer.");
		}
		return userList.get(userID - 1);
	}

	@Override
	public CarMechanic getCarMechanicByID(int carMechanicID) {
		if (carMechanicID <= 0) {
			throw new IndexOutOfBoundsException(
					"Es gibt keine Automechaniker-ID die mit 0 beginnt. Bitte geben Sie eine Zahl > 0 ein.");
		}
		if (carMechanicID > carMechanicList.size()) {
			throw new IndexOutOfBoundsException("Es gibt keine Automechaniker-ID mit dieser Nummer. Es gibt insgesamt "
					+ carMechanicList.size() + " Automechaniker.");
		}
		return carMechanicList.get(carMechanicID - 1);
	}

	@Override
	public Dispatcher getDispatcherByID(int dispatcherID) {
		if (dispatcherID <= 0) {
			throw new IndexOutOfBoundsException(
					"Es gibt keine Disponenten-ID die mit 0 beginnt. Bitte geben Sie eine Zahl > 0 ein.");
		}
		if (dispatcherID > dispatcherList.size()) {
			throw new IndexOutOfBoundsException("Es gibt keine Disponenten-ID mit dieser Nummer. Es gibt insgesamt "
					+ dispatcherList.size() + " Disponenten.");
		}
		return dispatcherList.get(dispatcherID - 1);
	}

	@Override
	public Consultant getConsultantByID(int consultantID) {
		if (consultantID <= 0) {
			throw new IndexOutOfBoundsException(
					"Es gibt keine Kundenberater-ID die mit 0 beginnt. Bitte geben Sie eine Zahl > 0 ein.");
		}
		if (consultantID > consultantList.size()) {
			throw new IndexOutOfBoundsException("Es gibt keine Kundenberater-ID mit dieser Nummer. Es gibt insgesamt "
					+ consultantList.size() + " Kundenberater.");
		}
		return consultantList.get(consultantID - 1);
	}

	@Override
	public List<User> getUsers() {
		if (userList.isEmpty()) {
			System.err.println("Die Liste für User ist leer.");
		}
		return userList;
	}

	@Override
	public List<CarMechanic> getCarMechanics() {
		if (carMechanicList.isEmpty()) {
			System.err.println("Die Liste für AutoMechaniker ist leer.");
		}
		return carMechanicList;
	}

	@Override
	public List<Dispatcher> getDispatchers() {
		if (dispatcherList.isEmpty()) {
			System.err.println("Die Liste für Disponenten ist leer.");
		}
		return dispatcherList;
	}

	@Override
	public List<Consultant> getConsultants() {
		if (consultantList.isEmpty()) {
			System.err.println("Die Liste für Kundenberater ist leer.");
		}
		return consultantList;
	}

}
