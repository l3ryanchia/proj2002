//package models;
//
//public class Project {
//    private String projectID;
//    private Supervisor supervisor;
//    private Student student;
//    private String title;
//    private String status;
//
//    public Project(String projectID, Supervisor supervisor, String title) {
//        this.projectID = projectID;
//        this.supervisor = supervisor;
//        this.student = null;
//        this.title = title;
//        this.status = "available";
//    }
//
//    // Getters and Setters
//    // ...
//}

//
//package models;
//
//public class Project {
//    private String title;
//    private String supervisor;
//
//    public Project(String title, String supervisor) {
//        this.title = title;
//        this.supervisor = supervisor;
//    }
//
//    // Getters and setters for title and supervisor
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getSupervisor() {
//        return supervisor;
//    }
//
//    public void setSupervisor(String supervisor) {
//        this.supervisor = supervisor;
//    }
//}

package models;

import java.util.Map;

import models.Project.Status;

public class Project {
	public enum Status {AVAILABLE, ALLOCATED, RESERVED, UNAVAILABLE};
	public static int projectIDCounter = 1;

    private String projectID;
    private String projectTitle;
    private String supervisor; //put the whole supervisor
    private String student;//put the whole student
    //private String emailAddress;
    private Status status;
    
    public Project(String title, String supervisor) {		//supervisor object
        this.projectID = "P" + projectIDCounter;
        this.projectTitle = title;
        this.supervisor = supervisor;
        this.student = null;
        this.status = Status.AVAILABLE;
        projectIDCounter++;
        //this.emailAddress = supervisorID + "@e.ntu.edu.sg"; //i think supervisor is the ID eh cos its the key for the hashmap?
    }

    // Getters and setters for projectID, title, and supervisor

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getTitle() {
        return projectTitle;
    }

    public void setTitle(String title) {
        this.projectTitle = title;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }
    
    public Status getStatus() {
    	return this.status;
    }
    
    public void setStatus(Status state) {
    	this.status = state;
    }
    
    /*public String getEmail() {
    	return this.emailAddress;
    }*/

    public void setStudent(String student) {
    	this.student = student;
    }
    
    public String getStudent() {
    	return this.student;
    }
    
    public void displayProject() {
    	System.out.printf("%10s %85s %25s \n", "PROJECT ID", "PROJECT TITLE", "PROJECT SUPERVISOR");
    	System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
    	System.out.printf("%10s %85s %25s \n", this.getProjectID(), this.getTitle(), this.getSupervisor());
    	System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
    }
    
    public void reserveProject(String studentID) {
    	this.setStudent(studentID);
    	this.setStatus(Status.RESERVED);
    }

    public void unreserveProject() {
    	this.setStudent(null);
    	this.setStatus(Status.AVAILABLE);
    }

    public void allocateStudent(String studentID) {
    	//this.setStudent(studentID);
    	this.setStatus(Status.ALLOCATED);
    }
    
    public void deallocateStudent() {
    	this.setStudent(null);
    	this.setStatus(Status.AVAILABLE);
    }
    
    public void reallocateSupervisor(String supervisor) {
    	this.setSupervisor(supervisor);
    }
    
    public void allocateSupervisor(String supervisor) {
    	this.setSupervisor(supervisor);
    	this.setStatus(Status.ALLOCATED);
    }
    
    public void deallocateSupervisor() {
    	this.setSupervisor(null);
    	this.setStatus(Status.AVAILABLE); //why set to available?
    }
}
