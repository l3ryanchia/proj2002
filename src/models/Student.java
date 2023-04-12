//package models;
//
//public class Student extends User {
//    private String userID;
//
//    public Student(String userID, String name, String email) {
//        super(name, email);
//        this.userID = userID;
//    }
//
//    // Getter and setter
//    public String getUserID() {
//        return userID;
//    }
//
//    public void setUserID(String userID) {
//        this.userID = userID;
//    }
//}


//package models;
//
//public class Student extends User {
//    public Student(String userID, String name, String email) {
//        super(userID, name, email);
//    }
//}
//

package models;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
	public enum StudentStatus {REGISTERED, RESERVED, UNREGISTERED};
	
    private StudentStatus status;
    private String projectID;
    private List<String> projectBlacklist;

    public Student(String userID, String name, String email) {
        super(userID, name, email);
        //this.password = "password"; // Set the default password
        this.status = StudentStatus.UNREGISTERED;
        this.projectID = null;
        this.projectBlacklist = new ArrayList<>();
    }
    
    public StudentStatus getStatus() {
    	return this.status;
    }
    
    public void setStatus(StudentStatus state) {
    	this.status = state;
    }
    
    public String getProject() {
        return projectID;
    }

    public void setProject(String projectID) {
        this.projectID = projectID;
    }
    
    public List<String> getProjectBlacklist(){
    	return this.projectBlacklist;
    }
    
    public void allocateProject(String projectID) {
    	this.setProject(projectID);
    	this.setStatus(StudentStatus.REGISTERED);
    }
    
    public void deallocateProject(String projectID) {
    	this.setProject("NIL");
    	this.setStatus(StudentStatus.UNREGISTERED);
    	this.projectBlacklist.add(projectID);
    }
    
    public void reserveProject(String projectID) {
    	this.setProject(projectID);
    	this.setStatus(StudentStatus.RESERVED);
    }
    
    public void unreserveProject() {
    	this.setProject("NIL");
    	this.setStatus(StudentStatus.UNREGISTERED);
    }
    
}
