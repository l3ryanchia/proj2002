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

import models.Student;

import java.util.HashMap;
import java.util.Map;

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

    public boolean checkPassword(String userID, String password) {
        Student student = students.get(userID);

        if (student != null) {
            return student.getPassword().equals(password);
        }

        return false;
    }

    // Other methods related to students can be added here
}
