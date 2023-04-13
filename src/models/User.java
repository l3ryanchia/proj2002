//package models;
//
//public abstract class User {
//    private String name;
//    private String email;
//
//    public User(String name, String email) {
//        this.name = name;
//        this.email = email;
//    }
//
//    // Getters and setters
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//}
package models;

public abstract class User {
    private String userID;
    private String name;
    private String email; //remove
    private String password;

    public User(String userID, String name, String email) { //remove email
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = "password";
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email; //get ID plus @e.ntu.edu.sg
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    
    public boolean authenticate(String password) {
    	if(this.getPassword().equals(password)) return true;
    	else return false;
    }
}

