package serializers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import managers.StudentManager;

public class StudentSerializer {
    public static StudentManager readStudentsFromFile(String filepath) {
        StudentManager studentManager = new StudentManager();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            br.readLine(); // Skipping header
            while ((line = br.readLine()) != null) {
                String[] studentData = line.split(",");
                String name = studentData[0].trim();
                String email = studentData[1].trim();
                String userID = email.split("@")[0]; 
                
                studentManager.addStudent(userID, name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentManager;
    }
}
