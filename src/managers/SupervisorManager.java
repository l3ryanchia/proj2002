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
import models.Project;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class SupervisorManager {
	
    private Map<String, Supervisor> supervisors;

    public SupervisorManager() {
        supervisors = new HashMap<>();
    }

    public Supervisor getSupervisor(String userID) {
        return supervisors.get(userID);
    }
    
    public String getSupervisorID(String name) {
    	String output = "Not found!";
    	for(Map.Entry<String, Supervisor> set:supervisors.entrySet()) {
    		Supervisor supervisor = set.getValue();
    		if(supervisor.getName().equals(name)) {
    			output = supervisor.getUserID();
    			break;
    		}
    	}
    	return output;
    }

    public void addSupervisor(String userID, String name) {
    	Supervisor supervisor = new Supervisor(userID, name);
        supervisors.put(supervisor.getUserID(), supervisor);
    }

}
