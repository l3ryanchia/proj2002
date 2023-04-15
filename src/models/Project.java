package models;

public class Project {
	public enum Status {AVAILABLE, ALLOCATED, RESERVED, UNAVAILABLE};
	public static int projectIDCounter = 1;

    private String projectID;
    private String projectTitle;
    private Supervisor supervisor; 
    private Student student;

    private Status status;
    
    public Project(String title, Supervisor supervisor) {		
        this.projectID = "P" + projectIDCounter;
        this.projectTitle = title;
        this.supervisor = supervisor;
        this.student = null;
        this.status = Status.AVAILABLE;
        projectIDCounter++;
    }

    // Getters and setters

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
    
    public Supervisor getSupervisor() {
    	return this.supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }
    
    public Status getStatus() {
    	return this.status;
    }
    
    public void setStatus(Status state) {
    	this.status = state;
    }

    public void setStudent(Student student) {
    	this.student = student;
    }
    
    public Student getStudent() {
    	return this.student;
    }
  
    public void reserveProject(Student student) {
    	this.setStudent(student);
    	this.setStatus(Status.RESERVED);
    }

    public void unreserveProject() {
    	this.setStudent(null);
    	this.setStatus(Status.AVAILABLE);
    }

    public void allocateStudent() {
    	this.setStatus(Status.ALLOCATED);
    }
    
    public void deallocateStudent() {
    	this.setStudent(null);
    	this.setStatus(Status.AVAILABLE);
    }
}
