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
enum Status {AVAILABLE, ALLOCATED, RESERVED, UNAVAILABLE}
//test
public class Project {
    private String projectID;
    private String title;
    private String supervisor;
    private Status status;
    private String student;

    public Project(String projectID, String title, String supervisor) {
        this.projectID = projectID;
        this.title = title;
        this.supervisor = supervisor;
        this.status = Status.AVAILABLE;
        this.student = "NIL";
    }

    // Getters and setters for projectID, title, and supervisor, status and student

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }
    
    public void reserveProject() {
    	this.status = Status.RESERVED;
    }
    
    public void unreserveProject() {
    	this.status = Status.AVAILABLE;
    }
    
    public void allocateStudent(String student) {
    	this.student = student;
    	this.status = Status.ALLOCATED;
    }
    
    public String getStudent() {
    	return this.student;
    }
    
    public Status getStatus() {
    	return this.status;
    }
}
