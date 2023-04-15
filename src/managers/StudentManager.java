
package managers;

import java.util.HashMap;
import java.util.Map;
import models.Student;

public class StudentManager {
    private Map<String, Student> students;

    public StudentManager() {
        students = new HashMap<>();
    }

    public Student getStudent(String userID) {
        return students.get(userID);
    }

    public void addStudent(String userID, String name) {
    	Student student = new Student(userID, name);
        students.put(student.getUserID(), student);
    }
    
}
