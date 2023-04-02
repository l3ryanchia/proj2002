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

public class Student extends User {
	public enum StudentStatus {REGISTERED, RESERVED, UNREGISTERED};
	
    private String password;
    private StudentStatus status;
    private String projectID;

    public Student(String userID, String name, String email) {
        super(userID, name, email);
        this.password = "password"; // Set the default password
        this.status = StudentStatus.UNREGISTERED;
        this.projectID = "NIL";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
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
}
