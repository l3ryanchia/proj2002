//package models;
//
//import java.util.*;
//
//public class Supervisor extends User {
//    private List<Project> submittedProjects;
//    private List<Request> requests;
//
//    public Supervisor(String userID, String name, String email) {
//        super(userID, name, email);
//        this.submittedProjects = new ArrayList<>();
//        this.requests = new ArrayList<>();
//    }
//
//    // Implement supervisor-specific methods
//    // ...
//}


//package models;
//
//public class Supervisor extends User {
//    public Supervisor(String userID, String name, String email) {
//        super(userID, name, email);
//    }
//}
//
package models;

import java.util.*;

public class Supervisor extends User {
    private String password;
    private ArrayList<String> projectIDs; //this is not initialised by serialiser so its empty, propose to remove
    private int numOfProjs;
    private int numOfAllocated;

    public Supervisor(String userID, String name, String email) {
        super(userID, name, email);
        this.password = "password"; // Set the default password
        projectIDs = new ArrayList<String>();
        numOfProjs = 0;
        numOfAllocated = 0;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    
    /*public void addProj(String projID) {
    	this.projectIDs.add(projID);
    	this.numOfProjs++;
    }*/
    
    public int getNumOfProjs() {
    	return this.numOfProjs;
    }
    
    public int getNumOfAllocated() {
    	return this.numOfAllocated;
    }
    
    public ArrayList<String> getProjIDs() {
    	return this.projectIDs;
    }
    
    public Project createProject() {
    	Scanner scanner = new Scanner(System.in);

        System.out.print("Enter project title: ");
        String title = scanner.nextLine();

        Project project = new Project(title, this.getName());
        this.projectIDs.add(project.getProjectID());
    	this.numOfProjs++;
        System.out.println("Project created successfully!");
        return project;
    }
    
    public boolean allocateProject(String projID) {
    	if(this.numOfAllocated >= 2) {
    		System.out.println("Supervisor has reached maximum number of allocated projects!");
    		return false;
    	}
    	//this.projectIDs.remove(projID);
    	this.projectIDs.add(projID);
    	this.numOfAllocated++;
    	return true;
    }
    
    public void deallocateProject(String projID) {
    	this.numOfAllocated--;
    	this.projectIDs.remove(projID);
    }
    
    public boolean numAtLimit() {
    	if (this.numOfAllocated >= 2) {
    		return true;
    	}
    	return false;
    }
    
}
