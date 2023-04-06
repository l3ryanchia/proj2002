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
	Scanner input = new Scanner(System.in);
	
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
    
    public boolean viewProjects(String userID, ProjectManager projectManager) {			//need to include projectManager because of the HashMap it has
    	String projID;
    	Supervisor supervisor = supervisors.get(userID);
    	if (supervisor != null)
    	{
    		int count = supervisor.getNumOfProjs();
    		ArrayList<String> list = supervisor.getProjIDs();
    		if (count == 0) {
    			System.out.println("Supervisor has no projects to his name.");
    		}
    		else {
    			for (int i=0; i<count; i++) {
    				System.out.println("Project " + i + " information:");
    				projID = list.get(i);
    				Project project = projectManager.getProject(projID);
    				System.out.println("Project ID:\t" + project.getProjectID());
    				System.out.println("Project Title:\t" + project.getTitle());
    				System.out.println("Status:\t" + project.getStatus());
    			}
    		}
    		return true;																//returns true false - check if userID given matches??
    	}
    	else return false;
    }

    // Other methods related to supervisors can be added here okie
}
