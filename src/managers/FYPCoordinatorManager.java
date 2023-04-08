//package managers;
//
//import models.FYPCoordinator;
//
//public class FYPCoordinatorManager {
//    private FYPCoordinator coordinator;
//
//    public FYPCoordinatorManager() {
//        coordinator = null;
//    }
//
//    public FYPCoordinator getCoordinator() {
//        return coordinator;
//    }
//
//    public void setCoordinator(FYPCoordinator coordinator) {
//        this.coordinator = coordinator;
//    }
//}
//

package managers;

import models.FYPCoordinator;

public class FYPCoordinatorManager {
    private FYPCoordinator coordinator;

    public FYPCoordinatorManager() {
        coordinator = null;
    }

    public FYPCoordinator getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(FYPCoordinator coordinator, SupervisorManager supervisorManager) {
    	if(supervisorManager.getSupervisor(coordinator.getUserID()) == null) {
    		System.out.println("FYP Coordinator must be registered as a Supervisor!");
    		return;
    	}
        this.coordinator = coordinator;
        supervisorManager.removeSupervisor(coordinator);
        supervisorManager.addSupervisor(coordinator);
    }

    public boolean checkPassword(String userID, String password) {
        if (coordinator != null && coordinator.getUserID().equals(userID)) {
            return coordinator.getPassword().equals(password);
        }

        return false;
    }
}
