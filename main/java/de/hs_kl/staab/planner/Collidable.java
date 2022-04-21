package de.hs_kl.staab.planner;

import java.time.LocalDateTime;

import de.hs_kl.staab.planner.workstation.WorkStation;

public interface Collidable {

	WorkStation getWorkStation();

	LocalDateTime getDateOfAppointment();

}
