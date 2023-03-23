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
    private String password;

    public Student(String userID, String name, String email) {
        super(userID, name, email);
        this.password = "password"; // Set the default password
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}
