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

public class SupervisorManager {
	Scanner input = new Scanner(System.in);
	
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
    
    public Project createNewProject(String userID) {
    	Supervisor supervisor = supervisors.get(userID);
	    if (supervisor != null) {
	    	String title;
	    	System.out.print("Input project title: ");		title = input.next();
	    	Project newProj = new Project(projID, title, supervisor.getName(), supervisor.getEmail());
	    	return newProj;		//in main app need have projManager to addProject()
	   	}
	    
	    return null;
    }
    
    public void viewProjects() {
    	
    }
    
    public void viewRequestHistory() {
    	
    }
    
    public void pendingRequests() {
    	
    }

    // Other methods related to supervisors can be added here
}
