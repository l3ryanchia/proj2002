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

import programs.FYPMSApp;

public class Supervisor extends User {
    private String password;
    private ArrayList<String> projectIDs;
    private int numOfProjs;

    public Supervisor(String userID, String name, String email) {
        super(userID, name, email);
        this.password = "password"; // Set the default password
        projectIDs = new ArrayList<String>();
        numOfProjs = 0;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    
    public void addProj(String projID) {
    	this.projectIDs.add(projID);
    	this.numOfProjs++;
    }
    
    public int getNumOfProjs() {
    	return this.numOfProjs;
    }
    
    public ArrayList<String> getProjIDs() {
    	return this.projectIDs;
    }
    
    public Project createProject() {
    	Scanner scanner = new Scanner(System.in);

        System.out.print("Enter project title: ");
        String title = scanner.nextLine();

        Project project = new Project(title, this.getName());
        this.addProj(project.getProjectID());
        System.out.println("Project created successfully!");
        return project;

    }
    
    public void allocateProject() {
    	//counter++
    	//if counter>=2, iterate through project list
    	if(not ALLOCATED)
    		if(RESERVED) reject request
    		change to UNAVAILABLE

    }
    
    //deallocate project()
    
}
