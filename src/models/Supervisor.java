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
    private ArrayList<String> projectIDs;
    private int numOfProjs;

    public Supervisor(String userID, String name, String email) {
        super(userID, name, email);
        this.password = "password"; // Set the default password
        projectIDs = new ArrayList<String>();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    
    public void addProjID(String projID) {
    	this.projectIDs.add(projID);
    }
}