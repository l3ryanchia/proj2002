//package managers;
//
//import models.Supervisor;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class SupervisorManager {
//    private Map<String, Supervisor> supervisors;
//
//    public SupervisorManager() {
//        supervisors = new HashMap<>();
//    }
//
//    public Supervisor getSupervisor(String supervisorID) {
//        return supervisors.get(supervisorID);
//    }
//
//    public void addSupervisor(Supervisor supervisor) {
//        supervisors.put(supervisor.getUserID(), supervisor);
//    }
//
//    // Other methods related to supervisors can be added here
//}
//

package managers;

import models.Supervisor;

import java.util.HashMap;
import java.util.Map;

public class SupervisorManager {
    private Map<String, Supervisor> supervisors;

    public SupervisorManager() {
        supervisors = new HashMap<>();
    }

    public Supervisor getSupervisor(String userID) {
        return supervisors.get(userID);
    }

    public void addSupervisor(Supervisor supervisor) {
        supervisors.put(supervisor.getUserID(), supervisor);
    }

    public boolean checkPassword(String userID, String password) {
        Supervisor supervisor = supervisors.get(userID);

        if (supervisor != null) {
            return supervisor.getPassword().equals(password);
        }

        return false;
    }

    // Other methods related to supervisors can be added here
}
