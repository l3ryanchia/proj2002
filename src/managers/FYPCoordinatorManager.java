
package managers;

import models.Supervisor;

public class FYPCoordinatorManager {
    private Supervisor coordinator;

    public FYPCoordinatorManager() {
        coordinator = null;
    }

    public Supervisor getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Supervisor coordinator, SupervisorManager supervisorManager) {
        coordinator.setCoordinator(true);
    	this.coordinator = coordinator;
    }

}
