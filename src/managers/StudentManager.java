//package managers;
//
//import models.Student;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class StudentManager {
//    private Map<String, Student> students;
//
//    public StudentManager() {
//        students = new HashMap<>();
//    }
//
//    public Student getStudent(String studentID) {
//        return students.get(studentID);
//    }
//
//    public void addStudent(Student student) {
//        students.put(student.getUserID(), student);
//    }
//
//    // Other methods related to students can be added here
//}
//

package managers;

import models.Project;
import models.Student;
import models.Project.Status;
import models.Student.StudentStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentManager {
    private Map<String, Student> students;

    public StudentManager() {
        students = new HashMap<>();
    }

    public Student getStudent(String userID) {
        return students.get(userID);
    }

    public void addStudent(Student student) {
        students.put(student.getUserID(), student);
    }

    public boolean checkPassword(String userID, String password) { //where is this being used??
        Student student = students.get(userID);

        if (student != null) {
            return student.getPassword().equals(password);
        }

        return false;
    }

    // Other methods related to students can be added here
    /*
    public void allocateProject(String projectID, String studentID) {
    	Student student = getStudent(studentID);
    	student.setProject(projectID);
    	student.setStatus(StudentStatus.REGISTERED);
    }
    
    public void reserveProject(String projectID, String studentID) {
    	Student student = getStudent(studentID);
    	student.setProject(projectID);
    	student.setStatus(StudentStatus.RESERVED);
    }
    */
}
