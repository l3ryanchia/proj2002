package serializers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.Student;

public class StudentSerializer {
    public static List<Student> readStudentsFromFile(String filepath) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            br.readLine(); // Skipping header
            while ((line = br.readLine()) != null) {
                String[] studentData = line.split(",");
                String name = studentData[0].trim();
                String email = studentData[1].trim();
                students.add(new Student(email.split("@")[0], name, email));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
}
