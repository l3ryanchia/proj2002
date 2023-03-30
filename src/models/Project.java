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

public class Project {
	public enum Status {Available, Allocated, Reserved, Unavailable}

    private String projectID;
    private String projectTitle;
    private String supervisor;
    private String emailAddress;
    private Status status;
    
    public Project(String projectID, String title, String supervisor, String supervisorID) {
        this.projectID = projectID;
        this.projectTitle = title;
        this.supervisor = supervisor;
        this.status = Status.Allocated;
        this.emailAddress = supervisorID + "@e.ntu.edu.sg";
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
    
    public String getEmail() {
    	return this.emailAddress;
    }
}
