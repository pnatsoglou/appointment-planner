package de.hs_kl.staab.planner.user;

import java.util.List;

public interface UserCRUD {

	void createNewUser(User user);

	void updateUser(int ID, String username, String firstName, String lastName);

	User getUserByID(int userID);

	CarMechanic getCarMechanicByID(int carMechanicID);

	Dispatcher getDispatcherByID(int dispatcherID);

	Consultant getConsultantByID(int consultantID);

	List<User> getUsers();

	List<CarMechanic> getCarMechanics();

	List<Dispatcher> getDispatchers();

	List<Consultant> getConsultants();

}